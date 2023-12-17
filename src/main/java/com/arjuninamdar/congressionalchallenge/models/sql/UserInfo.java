package com.arjuninamdar.congressionalchallenge.models.sql;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USER_INFO")
public class UserInfo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="USER_ID")
    private int userId; 

    @Column(name="USERNAME")
    private String username; 

    @Column(name="PASSWORD")
    private String password; 

    @Column(name="ROLES")
    private String roles; 


    @Column(name="IS_ENABLED")
    private boolean isEnabled; 


    public UserInfo(String username, String password, String roles,  boolean isEnabled) {
        this.username = username;
        this.password = password;
        this.roles = roles; 
        this.isEnabled = isEnabled;
    }

    public UserInfo() {
    }

    public String getRoles() {
        return this.roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }


    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isIsEnabled() {
        return this.isEnabled;
    }

    public boolean getIsEnabled() {
        return this.isEnabled;
    }

    public void setIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

}
