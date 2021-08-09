package vn.seven.stc.umgr.endpoints;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.seven.stc.config.ApplicationProperties;
import vn.seven.stc.core.CrudApiEndpoint;
import vn.seven.stc.core.utils.PaginationUtil;
import vn.seven.stc.umgr.endpoints.vm.ResetPasswordVM;
import vn.seven.stc.umgr.models.User;
import vn.seven.stc.umgr.services.UserService;
import vn.seven.stc.umgr.utils.SecurityUtils;

import java.util.List;

/**
 * Created by quocvi3t on 11/14/17.
 */
@RestController
@RequestMapping("/api/users")
@EnableConfigurationProperties(ApplicationProperties.class)
public class UserApiEndpoint extends CrudApiEndpoint<User,Long> {
    private static Logger logger = LoggerFactory.getLogger(UserApiEndpoint.class);
    private UserService userService;


    @Autowired
    public UserApiEndpoint(UserService service) {
        super(service);
        this.userService = service;
        this.baseUrl = "/api/users";
    }

    @Override
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<User>> list(Pageable pageable) {
        Page<User> page = userService.findAll(pageable);
        if(page !=null){
            HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page,baseUrl);
            return new ResponseEntity<>(page.getContent(),headers, HttpStatus.OK);
        }
        return null;
    }


    @RequestMapping(path="/change-password", method = RequestMethod.POST)
    public void changePassword(@RequestBody User user) {
        userService.changePassword(user);
    }
    @RequestMapping(path = "/current-user",method = RequestMethod.GET)
    public User user(@RequestParam("currentPassword") String currentPassword) {
        String account = SecurityUtils.getCurrentUserLogin();
        return userService.authenticate(account,currentPassword);
    }

    @RequestMapping(path = "/current", method = RequestMethod.GET)
    public User currentUser() {
        String account = SecurityUtils.getCurrentUserLogin();
        return userService.findUserWithAuthorities(account);
    }

    @RequestMapping(path = "/forgot-password/init", method = RequestMethod.POST)
    public void requestForgotPassword(@RequestParam("email") String email) {
        userService.requestForgotPassword(email);
    }

    @RequestMapping(path = "/forgot-password/finish", method = RequestMethod.POST)
    public void changeForgotPassword(@RequestBody ResetPasswordVM resetPasswordVM) {
        userService.changeForgotPassword(resetPasswordVM);
    }

    @RequestMapping(path = "/active-new-user", method = RequestMethod.POST)
    public void activeNewUser(@RequestBody ResetPasswordVM resetPasswordVM) {
        userService.activeNewUser(resetPasswordVM);
    }

    @RequestMapping(path = "{userId}/activate", method = RequestMethod.POST)
    public void activateUser(@PathVariable("userId") Long userId, @RequestParam("token") String activationToken) {
        userService.activeUser(userId, activationToken);
    }

    @RequestMapping(path = "{userId}/deleteAvatar", method = RequestMethod.POST)
    public void deleteAvatar(@PathVariable("userId") Long userId) {
        userService.deleteAvatar(userId);
    }

}
