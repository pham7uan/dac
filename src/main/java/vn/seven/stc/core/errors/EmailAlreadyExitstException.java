package vn.seven.stc.core.errors;

public class EmailAlreadyExitstException extends BadRequestAlertException {
    public EmailAlreadyExitstException() {
        super("Email already exists", "userAndPermission", "duplicateEmail");
    }
}
