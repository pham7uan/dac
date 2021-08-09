package vn.seven.stc.umgr.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import vn.seven.stc.ErpCache;
import vn.seven.stc.config.ApplicationProperties;
import vn.seven.stc.core.CacheService;
import vn.seven.stc.core.Constants;
import vn.seven.stc.core.CrudService;
import vn.seven.stc.core.errors.*;
import vn.seven.stc.core.utils.Common;
import vn.seven.stc.umgr.endpoints.vm.ResetPasswordVM;
import vn.seven.stc.umgr.models.Privilege;
import vn.seven.stc.umgr.models.Role;
import vn.seven.stc.umgr.models.User;
import vn.seven.stc.umgr.repositories.UserRepository;
import vn.seven.stc.umgr.utils.SecurityUtils;
import vn.seven.stc.umgr.utils.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created by quocvi3t on 11/14/17.
 */
@Service
@Transactional
@EnableConfigurationProperties(ApplicationProperties.class)
public class UserService extends CrudService<User,Long> {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);
    private UserRepository userRepository;
    private MailService mailService;
    private final ApplicationProperties applicationProperties;

    @PersistenceContext
    private EntityManager entityManager;
    protected CacheService cacheService;

    @Autowired
    public void setCacheService(CacheService cacheService) {
        this.cacheService = cacheService;
    }


    public UserService(UserRepository userRepository, MailService mailService, ApplicationProperties applicationProperties) {
        this.repository = this.userRepository = userRepository;
        this.mailService = mailService;
        this.applicationProperties = applicationProperties;
    }

    public Boolean changePassword(User user) {
        logger.debug("Resetting password for user #{}",user.getId());
        // get userId login
        Long currentUserId = SecurityUtils.getCurrentUserId();

        User oldPasswordUser;
        // nếu là sửa chính mình
        if(user.getId().equals(currentUserId)){
            oldPasswordUser = simpleGet(user.getId());
        } else{
            oldPasswordUser = get(user.getId());
        }

        String[] authorities = {Constants.ROLE_SYSTEM_ADMIN, "ROLE_ORGANIZATION"};
        Boolean isAdmin = SecurityUtils.hasAnyAuthority(authorities);

        if(!isAdmin){
            if(!oldPasswordUser.authenticate(user.getPassword())){// validate old password
                throw new InvalidCredentialsException();
            }
        }
        if(user.getNewPassword() == null || !user.getNewPassword().equals(user.getConfirmPassword())) { //validate new password and confirm password
            throw new InvalidPasswordException();
        } else {
            oldPasswordUser.setEncryptedPassword(user.getNewPassword());
            this.repository.save(oldPasswordUser);
            logger.info("Successfully reset password for user #{}",oldPasswordUser.getId());
            return true;
        }

    }

    public User authenticate(String account, String password) {
        User user;
        try {
            user = userRepository.findFirstByEmailIgnoreCase(account);
            if(user !=null && user.getActive() !=null && user.getActive() && user.authenticate(password)) {
                return user;
            } else if(user.getActive() == null || !user.getActive()){
                throw new BadRequestAlertException("Account inactive", "userAndPermission", "inactiveAccount");
            } else {
                user = null;
            }
        } catch(EmailNotFoundException ex) {
            user = null;
        }

        //check other authentication provider, ex: Ldap
//        if (user == null) {
//            for (AuthenticationProvider authenticationProvider : authenticationProviders) {
//                user = authenticationProvider.authenticate(email, password);
//                if (user != null) {
//                    break;
//                }
//            }
//        }
        //if user still null, return invalid credentials exception
        if(user == null) {
            throw new InvalidCredentialsException();
        }

        return user;

    }

    public User findByEmail(String account) {
        User user = this.userRepository.findFirstByEmailIgnoreCase(account);
        if(user == null) {
            throw new EmailNotFoundException();
        }
        return user;
    }


    public User findUserWithAuthorities(String account) {
        User user = this.userRepository.findOneWithRolesByEmail(account);
        if(user == null) {
            throw new EmailNotFoundException();
        }
        for(Role role : user.getRoles()) {
            user.getAuthorities().add(role.getName());
            for(Privilege privilege : role.getPrivileges()) {
                user.getAuthorities().add(privilege.getName());
            }
        }
        return user;
    }

    public User findOneWithRoles(Long id) {
        return this.userRepository.findOneWithRolesById(id);
    }

    public void activeUser(Long userId, String activationToken) {
        User user = userRepository.findOne(userId);
        if(user == null) {
            throw new EmailNotFoundException();
        }

        if(user.getActivationToken() != null && activationToken.equals(user.getActivationToken()) && StringUtils.checkExpireTime(user.getActivationTokenCreated(), applicationProperties.getActivation().getExpirePeriod())) {
            user.setActive(true);
            user.setActivationToken(null);
            user.setActivated(System.currentTimeMillis());
            update(userId, user);
            logger.debug("Activated user: #{}", userId);
        } else throw new TokenExpireTimeException();
    }

    public void requestForgotPassword(String account) {
        User user = this.userRepository.findFirstByEmailIgnoreCase(account);
        if(user == null) {
            throw new EmailNotFoundException();
        }

        user.setForgotPasswordToken(UUID.randomUUID().toString());
        user.setForgotPasswordTokenCreated(System.currentTimeMillis());
        userRepository.save(user);
        if(applicationProperties.getActivation().isEnableMail())
        {
            mailService.sendPasswordResetMail(user);
            logger.debug("Request forgot password user: #{}", user.getId());
        }

    }

    public void changeForgotPassword(ResetPasswordVM resetPasswordVM) {
        User user = userRepository.findFirstByEmailIgnoreCase(resetPasswordVM.getAccount());
        if(user == null) throw new EmailNotFoundException();
        if(!resetPasswordVM.getNewPassword().equals(resetPasswordVM.getConfirmPassword())) throw new ConfirmPasswordException();

        if(user.getForgotPasswordToken() != null && resetPasswordVM.getForgotPasswordToken().equals(user.getForgotPasswordToken()) && StringUtils.checkExpireTime(user.getForgotPasswordTokenCreated(), applicationProperties.getActivation().getExpirePeriod())) {
            user.setPassword(resetPasswordVM.getNewPassword());
            user.setForgotPasswordToken(null);
            user.setForgotPasswordTokenCreated(null);
            user.setEncryptedPassword(resetPasswordVM.getNewPassword());
            userRepository.save(user);
            logger.debug("Change forgot password user: #{}", user.getId());
        } else throw new TokenExpireTimeException();
    }

    public void activeNewUser(ResetPasswordVM resetPasswordVM) {
        User user = userRepository.findFirstByEmailIgnoreCase(resetPasswordVM.getAccount());
        if(user == null) throw new EmailNotFoundException();
        if(!resetPasswordVM.getNewPassword().equals(resetPasswordVM.getConfirmPassword())) throw new ConfirmPasswordException();

        if(user.getActivationToken() != null && resetPasswordVM.getForgotPasswordToken().equals(user.getActivationToken()) && StringUtils.checkExpireTime(user.getActivationTokenCreated(), applicationProperties.getActivation().getExpirePeriod())) {
            user.setEncryptedPassword(resetPasswordVM.getNewPassword());
            user.setActivationToken(null);
            user.setActivationTokenCreated(null);
            user.setActive(true);
            userRepository.save(user);
            logger.debug("Change forgot password user: #{}", user.getId());
        } else throw new TokenExpireTimeException();
    }

    @Override
    protected void beforeCreate(User entity) {
        entity.setTenantId(SecurityUtils.getTenantId());
        super.beforeCreate(entity);
        //hash password before saving
        String password = Common.randomString(8);
        entity.setEncryptedPassword(password);
        entity.setNewPassword(password);
        entity.setActive(true);


        // check email is exist
        User user = this.userRepository.findFirstByEmailIgnoreCase(entity.getEmail());
        if(user != null) {
            throw new BadRequestAlertException("Account existed", "userAndPermission", "existedAccount");
        }

    }

    @Override
    protected void afterCreate(User entity) {
        mailService.sendCreationEmail(entity);
    }

    @Override
    protected void beforeUpdate(User entity) {
        super.beforeUpdate(entity);
        User old = simpleGet(entity.getId());

        if(entity.getNewPassword() !=null && entity.getConfirmPassword() !=null){
            changePassword(entity);
        }
        //password is not allowed to update here
        entity.setJwtToken(old.getJwtToken());
        entity.setPassword(old.getPassword());
    }

    @Override
    protected void afterUpdate(User old, User updated) {
        super.afterUpdate(old, updated);
        // clear cache
        cacheService.clear(ErpCache.ONE_WAY);
        cacheService.clear(ErpCache.TWO_WAY);
    }

    @Override
    protected void beforeDelete(User entity) {
        super.beforeDelete(entity);
        if(entity.getRoles() != null && !entity.getRoles().isEmpty()){
            deleteRoleRelationByUserId(entity.getId());
        }
    }

    @Override
    public User get(Long id) {
        User user =  userRepository.findOneWithRolesById(id);
        return user;
    }
    @Override
    public void deactivate(Long aLong) {
        super.deactivate(aLong);
        userRepository.clearJwtToken(aLong);
    }

    @Override
    protected void afterDelete(User entity) {
        super.afterDelete(entity);
    }

    public void deleteAvatar(Long userId) {
        User user = get(userId);
        if(user != null) {
            user.setUserAvatar(null);
            update(userId, user);
        }
    }

    public void deleteRoleRelationByUserId(Long userId){
        String query = "DELETE FROM base_user_role where user_id = " + userId;
        Query q = entityManager.createNativeQuery(query);
        q.executeUpdate();
    }

    public List<User> findAllByIdIn(Set<Long> ids){
        return userRepository.findAllByIdIn(ids);
    }

    public List<User> findAllByIdInAndActive(Set<Long> ids, Boolean active){
        return userRepository.findAllByIdInAndActive(ids, active);
    }

    public void activateMany(Set<Long> userIds){
        String query = "UPDATE base_users SET active = true WHERE id in :userIds";
        Query q = entityManager.createNativeQuery(query);
        q.setParameter("userIds", userIds);
        q.executeUpdate();
    }

    public Boolean isAdmin(){
        User user = findOneWithRoles(SecurityUtils.getUserId());
        Boolean isAdmin = false;
        if(user.getRoles() !=null){
            for(Role role:user.getRoles()){
                if(role.getName().equals(Constants.ROLE_SYSTEM_ADMIN) || role.getName().equals(Constants.ROLE_OWNER)){
                    isAdmin = true;
                }
            }
        }
        return isAdmin;
    }
}
