package vn.seven.stc.core;

import java.util.HashMap;
import java.util.Map;

public class ApiCode {
    public static final Integer SUCCESS = 1;
    public static final Integer FAIL = 0;
    public static final String SUCCESS_MSG ="Success";
    public static final String FAIL_MSG ="Failed";
    public static final String MSG_500 ="Internal Server Error";
    public static final Map<Integer, String> MAP = new HashMap<Integer, String>(){{
        put(SUCCESS, SUCCESS_MSG);
        put(FAIL, FAIL_MSG);
    }};

}
