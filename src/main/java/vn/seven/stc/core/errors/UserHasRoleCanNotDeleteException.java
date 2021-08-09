package vn.seven.stc.core.errors;

public class UserHasRoleCanNotDeleteException extends BadRequestAlertException {

    public UserHasRoleCanNotDeleteException() {
        super("Can not delete User", "userAndPermission", "userHasRole");
    }
}
