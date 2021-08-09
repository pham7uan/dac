package vn.seven.stc.umgr.services;

import vn.seven.stc.umgr.models.User;

public interface AuthenticationProvider {
    User authenticate(String email, String password);
}
