package vn.seven.stc.core.errors;

public class RoleHasPrivilegeCanNotDeleteException extends BadRequestAlertException {

    public RoleHasPrivilegeCanNotDeleteException() {
        super("Can not delete Role", "userAndPermission", "roleHasPrivilege");
    }
}
