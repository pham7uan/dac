package vn.seven.stc.umgr.services;

import io.github.jhipster.config.JHipsterProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.seven.stc.umgr.models.Privilege;
import vn.seven.stc.umgr.models.Role;
import vn.seven.stc.umgr.models.User;
import vn.seven.stc.core.errors.InvalidCredentialsException;
import vn.seven.stc.core.Constants;

import javax.transaction.Transactional;
import java.util.*;

/**
 * Created by quocvi3t on 11/15/17.
 */
@Service
@Transactional
public class AuthService {


    private static Logger logger = LoggerFactory.getLogger(AuthService.class);

    private UserService userService;
    private JHipsterProperties jHipsterProperties;
    private long tokenValidityInMilliseconds;
    private long tokenValidityInMillisecondsForRememberMe;

    @Autowired
    public AuthService(UserService userService, JHipsterProperties jHipsterProperties) {
        this.userService = userService;
        this.jHipsterProperties = jHipsterProperties;
        this.tokenValidityInMilliseconds =
                1000 * jHipsterProperties.getSecurity().getAuthentication().getJwt().getTokenValidityInSeconds();
        this.tokenValidityInMillisecondsForRememberMe =
                1000 * jHipsterProperties.getSecurity().getAuthentication().getJwt().getTokenValidityInSecondsForRememberMe();
    }


    public String token(String account, String password, Boolean rememberMe) {
        logger.info("Generate token for user: {}", account);
        User user = userService.authenticate(account, password);
        logger.info("Validate user", account);
        if(user == null) {
            throw new InvalidCredentialsException();
        }

        Date validity;
        long now = (new Date()).getTime();
        if (rememberMe) {
            validity = new Date(now + this.tokenValidityInMillisecondsForRememberMe);
        } else {
            validity = new Date(now + this.tokenValidityInMilliseconds);
        }

        //TODO: find organizationId from username
//        Set<Long> organizationIds = userService.getOrganizationIdsByUserId(user.getId());

        String token = Jwts.builder()
                .setSubject(user.getEmail())
                .setExpiration(validity)
                .claim(Constants.JWT_SCOPE,getAuthorities(user))
                .claim(Constants.JWT_TENANT_ID, user.getTenantId())
                .claim(Constants.JWT_USER_ID, user.getId())
                .signWith(SignatureAlgorithm.HS512, Constants.JWT_SECRET)
                .compact();
        logger.info("Token generated for user {}, token: {}", account, token);

        //update jwtToken for user in database
//        user.setJwtToken(token);
//        userRepository.save(user);

//        UserLogin userLogin = userLoginRepository.findOneByEmailIgnoreCase(user.getEmail());
//        if(userLogin !=null) {
//            userLoginRepository.updateJwtToken(token,userLogin.getId());
//        } else {
//            userLoginRepository.save(user.toUserLogin());
//        }
        return token;
    }

    public List<String> getAuthorities(User user) {
        List<String> authorities = new ArrayList<>();
        for(Role role : user.getRoles()) {
            authorities.add(role.getName());
            for(Privilege privilege : role.getPrivileges()) {
                authorities.add(privilege.getName());
            }
        }
        return authorities;
    }
}
