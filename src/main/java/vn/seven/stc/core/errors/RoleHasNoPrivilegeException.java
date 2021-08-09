package vn.seven.stc.core.errors;

public class RoleHasNoPrivilegeException extends BadRequestAlertException {

    public RoleHasNoPrivilegeException() {
        super("Role has no privilege", "userAndPermission", "roleHasNoPrivilege");
    }
}
