package vn.seven.stc.umgr.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import vn.seven.stc.core.IdEntity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by quocvi3t on 11/13/17.
 */
@Entity
@Table(name = "base_users")
public class User extends IdEntity {
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private String firstName;
    private String lastName;
    private String department;
    private Long activated; //timestamp activated
    @JsonIgnore
    private String activationToken;
    @JsonIgnore
    private Long activationTokenCreated; //timestamp
    @JsonIgnore
    private String forgotPasswordToken;
    @JsonIgnore
    private Long forgotPasswordTokenCreated; //timestamp
    private String langKey;

    @Size(min = 5, max = 255)
    @Column(length = 255, unique = true)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String confirmPassword;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String newPassword;
    @ManyToMany
    @JoinTable(
            name = "base_user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;

    @Transient
    @JsonProperty
    private Set<String> authorities;
    private String fullName;

    private String address;
    private String phone;

    @Lob
    private String userAvatar;

    //@Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String jwtToken;

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getActivated() {
        return activated;
    }

    public void setActivated(Long activated) {
        this.activated = activated;
    }

    public String getActivationToken() {
        return activationToken;
    }

    public void setActivationToken(String activationToken) {
        this.activationToken = activationToken;
    }

    public Long getActivationTokenCreated() {
        return activationTokenCreated;
    }

    public void setActivationTokenCreated(Long activationTokenCreated) {
        this.activationTokenCreated = activationTokenCreated;
    }

    public String getForgotPasswordToken() {
        return forgotPasswordToken;
    }

    public void setForgotPasswordToken(String forgotPasswordToken) {
        this.forgotPasswordToken = forgotPasswordToken;
    }

    public Long getForgotPasswordTokenCreated() {
        return forgotPasswordTokenCreated;
    }

    public void setForgotPasswordTokenCreated(Long forgotPasswordTokenCreated) {
        this.forgotPasswordTokenCreated = forgotPasswordTokenCreated;
    }

    public String getLangKey() {
        return langKey;
    }

    public void setLangKey(String langKey) {
        this.langKey = langKey;
    }



    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public User() {
        this.roles = new HashSet<Role>();
        this.authorities = new HashSet<>();
    }

    public User(Long id,String fullName){
        this.setId(id);
        this.setFullName(fullName);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    public void setEncryptedPassword(String password) {
        this.password = passwordEncoder.encode(password);
    }


    public Boolean authenticate(String password) {
        return passwordEncoder.matches(password,this.password);
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
