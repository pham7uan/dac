package vn.seven.stc.core.filters;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by huyvv
 * Date: 16/07/2019
 * Time: 11:49 AM
 * for all issues, contact me:
 **/
public class UserPrincipal extends org.springframework.security.core.userdetails.User{
    //add new property for principal
    private Long tenantId;
    private Long userId;

    public UserPrincipal(String username, String password, Collection<? extends GrantedAuthority> authorities, Long tenantId, Long userId) {
        super(username, password, authorities);
        this.tenantId = tenantId;
        this.userId = userId;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
