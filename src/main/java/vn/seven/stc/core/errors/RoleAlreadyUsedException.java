package vn.seven.stc.core.errors;

public class RoleAlreadyUsedException extends BadRequestAlertException {

    public RoleAlreadyUsedException() {
        super("Role name is in used", "userAndPermission", "roleExist");
    }
}
