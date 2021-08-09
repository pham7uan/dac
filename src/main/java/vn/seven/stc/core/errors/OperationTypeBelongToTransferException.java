package vn.seven.stc.core.errors;

public class OperationTypeBelongToTransferException extends BadRequestAlertException {

    public OperationTypeBelongToTransferException() {
        super("Operation belong to transfer", "operation-type", "transfer");
    }
}
