package vn.seven.stc.core.errors;

public class NoPermissionException extends BadRequestAlertException {

    public NoPermissionException() {
        super(ErrorConstants.NO_PERMISSION, "No Permission", "permission", "noPermission");
    }
}
