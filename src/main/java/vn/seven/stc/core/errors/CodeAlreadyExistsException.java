package vn.seven.stc.core.errors;

public class CodeAlreadyExistsException extends BadRequestAlertException {
    public CodeAlreadyExistsException() {
        super("Code already exists", "userAndPermission", "duplicateCode");
    }
}
