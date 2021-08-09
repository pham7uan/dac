package vn.seven.stc.umgr.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import vn.seven.stc.core.Constants;
import vn.seven.stc.core.IdEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by quocvi3t on 11/16/17.
 */
@Entity
@Table(name="base_roles")
public class Role extends IdEntity {

    private Long organizationId = Constants.ROOT_ORGANIZATION;
    private String name;
    private String displayName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String description;
    private Boolean type = false;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @ManyToMany
    @JoinTable(
            name = "base_role_privilege",
            joinColumns = @JoinColumn(name="role_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="privilege_id",referencedColumnName = "id")
    )
    private Set<Privilege> privileges = new HashSet<>();


    @ManyToMany(mappedBy = "roles")
    @JsonIgnoreProperties({"email","active","firstName","lastName","created","createdBy","updated","updatedBy","roles","authorities"})
    private Set<User> users;

    public Role() {

    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Set<Privilege> privileges) {
        this.privileges = privileges;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }
}
