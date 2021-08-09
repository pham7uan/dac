package vn.seven.stc.core.errors;

public class SequenceHasOperationTypeActiveException extends BadRequestAlertException {

    public SequenceHasOperationTypeActiveException() {
        super("Sequence have operation type active", "sequence", "hasOperationTypeActive");
    }
}
