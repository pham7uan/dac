package vn.seven.stc.umgr.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import vn.seven.stc.core.filters.UserPrincipal;
import vn.seven.stc.core.Constants;
import vn.seven.stc.core.errors.NoPermissionException;

/**
 * Utility class for Spring Security.
 */
public final class SecurityUtils {

    private SecurityUtils() {
    }

    /**
     * Get the login of the current user.
     *
     * @return the login of the current user
     */
    public static String getCurrentUserLogin() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String userName = null;
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof UserDetails) {
                UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
                userName = springSecurityUser.getUsername();
            } else if (authentication.getPrincipal() instanceof String) {
                userName = (String) authentication.getPrincipal();
            }
        }
        return userName;
    }

    /**
     * Get the JWT of the current user.
     *
     * @return the JWT of the current user
     */
    public static String getCurrentUserJWT() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null && authentication.getCredentials() instanceof String) {
            return (String) authentication.getCredentials();
        }
        return null;
    }

    /**
     * Check if a user is authenticated.
     *
     * @return true if the user is authenticated, false otherwise
     */
    public static boolean isAuthenticated() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null) {
            return authentication.getAuthorities().stream()
                .noneMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(Constants.ROLE_ANONYMOUS));
        }
        return false;
    }

    /**
     * If the current user has a specific authority (security role).
     * <p>
     * The name of this method comes from the isUserInRole() method in the Servlet API
     *
     * @param authority the authority to check
     * @return true if the current user has the authority, false otherwise
     */
    public static boolean isCurrentUserInRole(String authority) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null) {
            return authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(authority));
        }
        return false;
    }

    public static Long getTenantId(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        Long tenantId = null;

        if(authentication != null){
            if(authentication.getPrincipal() instanceof UserPrincipal){
                tenantId = ((UserPrincipal) authentication.getPrincipal()).getTenantId();
            }
        }

        return  tenantId;
    }

    public static Long getUserId(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        Long userId = null;

        if(authentication != null){
            if(authentication.getPrincipal() instanceof UserPrincipal){
                userId = ((UserPrincipal) authentication.getPrincipal()).getUserId();
            }
        }
        return  userId;
    }

    public static Long getCurrentUserId(){
        return getUserId();
    }

    // remove root organization
//    public static Set<Long> getUserOrganizationIds(){
//        Set<Long> organizationIds = getListOrganizationIds();
//        if(!SecurityUtils.isCurrentUserInRole(Constants.ROLE_SYSTEM_ADMIN)){
//            organizationIds.remove(Constants.ROOT_ORGANIZATION);
//        }
//        return organizationIds;
//    }

//    public static Long getTenantId(){
//        Set<Long> organizationIds = getListOrganizationIds();
//        if(organizationIds !=null){
//            for(Long id: organizationIds){
//                return id;
//            }
//        }
//        return null;
//    }

//    public static boolean hasRoleWithOrganization(Long organizationId){
//        Set<Long> organizationIds = getListOrganizationIds();
//        return organizationIds != null && organizationIds.contains(organizationId);
//    }

    public static void validatePermission(String permission){
        if(permission == null) return;
        if(!isCurrentUserInRole(Constants.ROLE_SYSTEM_ADMIN) &&  !isCurrentUserInRole(permission)){
            throw new NoPermissionException();
        }
    }

//    public static Long getDefaultOrganizationId(){
//        Long idDefault = Constants.ROOT_ORGANIZATION;
//        Set<Long> organizationIds = SecurityUtils.getUserOrganizationIds();
//        if(organizationIds.size() > 0){
//            for(Long id : organizationIds){
//                idDefault = id;
//                break;
//            }
//        }
//
//        return idDefault;
//    }

    public static boolean hasAnyAuthority(String[] authorities) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        Boolean hasAuthority = false;
        if(authentication == null) return hasAuthority;

        for (String auth : authorities){
            if(authentication.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(auth))){
                hasAuthority = true;
                break;
            }
        }

        return hasAuthority;
    }

    public static boolean hasAnyAuthority(String auth) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        Boolean hasAuthority = false;
        if(authentication == null) return hasAuthority;

        if(authentication.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(auth))){
            hasAuthority = true;
        }
        return hasAuthority;
    }
}
