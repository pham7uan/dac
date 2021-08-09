package vn.seven.stc.core.errors;

public class ImageTooLargeException extends BadRequestAlertException {

    public ImageTooLargeException() {
        super("Image is too large!", "userAndPermission", "imageIsTooLarge");
    }
}
