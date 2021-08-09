package vn.seven.stc.logs.converters;

public class CustomValueChange {
    public String propertyName;
    public Object oldValue;
    public Object newValue;

    public CustomValueChange(String propertyName, Object oldValue, Object newValue) {
        this.propertyName = propertyName;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public CustomValueChange() {
    }
}