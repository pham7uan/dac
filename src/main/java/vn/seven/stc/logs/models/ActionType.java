package vn.seven.stc.logs.models;

import java.util.Arrays;

public class ActionType {
    public static final String MENTIONED_IN_NOTE = "mentioned_in_note";
    public static final String ADD_NOTE = "add_note";
    public static final String UPDATE_TRANSFER = "update_transfer";
    public static final String CREATE_TRANSFER = "create_transfer";
    public static final String CREATE_BACK_ORDER = "create_back_order";
    public static final String CREATE_TRANSFER_RETURN = "create_transfer_return";
    public static final String CHECK_AVAILABILITY = "check_availability";
    public static final String UN_RESERVED = "un_reserved";
    public static final String DELETE_TRANSFER = "delete_transfer";
    public static final String UPDATE_TRANSFER_ITEM = "update_transfer_item";
    public static final String CREATE_TRANSFER_ITEM = "create_transfer_item";
    public static final String DELETE_TRANSFER_ITEM = "delete_transfer_item";
    public static final String CREATE_SCRAP = "create_scrap";
    public static final String CREATE_INVENTORY = "create_inventory";
    public static final String UPDATE_INVENTORY = "update_inventory";
    public static final String DELETE_INVENTORY = "delete_inventory";
    public static final String CREATE_ADJUSTMENT_DETAIL = "create_adjustment_detail";
    public static final String UPDATE_ADJUSTMENT_DETAIL = "update_adjustment_detail";
    public static final String DELETE_ADJUSTMENT_DETAIL = "delete_adjustment_detail";
    public static final String SET_ZERO_ADJUSTMENT_DETAIL = "set_zero_adjustment_detail";

    private static final String[] DELETE_ACTIONS = {
            DELETE_TRANSFER,
            DELETE_TRANSFER_ITEM,
            DELETE_INVENTORY,
            DELETE_ADJUSTMENT_DETAIL
    };

    public static boolean checkActionDelete(String actionType) {
        return Arrays.asList(DELETE_ACTIONS).contains(actionType);
    }

}
