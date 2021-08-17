package vn.seven.stc.core;

/**
 * Created by quocvi3t on 11/15/17.
 */
public class Constants {
    public static final Long ROOT_ORGANIZATION = 1L;
    public static final String JWT_SECRET = "secret";
    public static final String AUTH_TOKEN_PREFIX = "Bearer ";
    public static final String AUTH_HEADER_STRING = "Authorization";
    public static final String JWT_SCOPE = "scope";
    public static final String JWT_ORGANIZATION_IDS = "organization_ids";
    public static final String JWT_USER_ID = "user_id";
    public static final String JWT_TENANT_ID = "tenant_id";
    public static final Integer IS_DEFAULT = 1;
    public static final Integer BYTE = 1024;

    public static final String ROLE_OWNER = "ROLE_OWNER";
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ANONYMOUS = "ROLE_ANONYMOUS";

    public static final String DEFAULT_PASSWORD = "123456";

    public static final String ROLE_SYSTEM_ADMIN = "ROLE_SYSTEM_ADMIN";
    public static final String ROLE_SYSTEM_USER = "ROLE_SYSTEM_USER";
    public static final String ROLE_COMPANY_ADMIN = "ROLE_COMPANY_ADMIN";
    public static final String ROLE_LOCATION_COMPANY_ADMIN = "ROLE_LOCATION_COMPANY_ADMIN";
    public static final Long ROLE_SYSTEM_ADMIN_ID = 1L;
    public static final Long ROLE_ORGANIZATION_ID = 2L;
    public static final Long ROLE_MANAGER_ID = 3L;

    public static final String TEMPLATE_DEVICE_EXPORT = "/template_device_export.xlsx";
}
