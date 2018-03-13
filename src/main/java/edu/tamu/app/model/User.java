/* 
 * AppUser.java 
 * 
 * Version: 
 *     $Id$ 
 * 
 * Revisions: 
 *     $Log$ 
 */
package edu.tamu.app.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import edu.tamu.app.enums.AppRole;
import edu.tamu.app.model.User;
import edu.tamu.weaver.auth.model.AbstractWeaverUserDetails;
import edu.tamu.weaver.user.model.IRole;

/**
 * Application User entity.
 * 
 */
@Entity
public class User extends AbstractWeaverUserDetails {
    
    private static final long serialVersionUID = -4974106399870286015L;
    
    @Column(name = "role")
    private AppRole role;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    /**
     * Constructor for the application user
     * 
     */
    public User() {
        super();
    }

    /**
     * Constructor for application user with uin passed.
     * 
     * @param uin
     *            String
     * 
     */
    public User(String uin) {
        setUsername(uin);
    }

    /**
     * Constructor for application user with uin passed.
     * 
     * @param uin
     *            Long
     * 
     */
    public User(String uin, String firstName, String lastName, String role) {
        this(uin);
        setFirstName(firstName);
        setLastName(lastName);
        setRole(AppRole.valueOf(role));
    }
    
    public User(User user) {
        this(user.getUsername());
        setFirstName(user.getFirstName());
        setLastName(user.getLastName());
        setRole(user.getRole());
    }
    
    /**
     * @return the role
     */
    @JsonDeserialize(as = AppRole.class)
    public IRole getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    @JsonSerialize(as = AppRole.class)
    public void setRole(IRole role) {
        this.role = (AppRole) role;
    }

    /**
     * 
     * @return firstName
     * 
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName
     *            String
     * 
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return lastName
     * 
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName
     *            String
     * 
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(this.getRole().toString());
        authorities.add(authority);
        return authorities;
    }
    
    @Override
    @JsonIgnore
    public String getPassword() {
        return null;
    }
}
