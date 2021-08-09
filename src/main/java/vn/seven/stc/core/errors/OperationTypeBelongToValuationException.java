package vn.seven.stc.core.errors;

public class OperationTypeBelongToValuationException extends BadRequestAlertException {

    public OperationTypeBelongToValuationException() {
        super("Operation belong to valuation report", "operation-type", "valuation");
    }
}
