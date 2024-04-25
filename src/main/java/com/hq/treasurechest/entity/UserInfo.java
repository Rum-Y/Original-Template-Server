package com.hq.treasurechest.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@TableName("userinfo")
public class UserInfo implements UserDetails {

    private String id;
    private String username;
    private String password;

    private String schoolCode;

    private Character isLocked;

    private Timestamp firstLoginTime;

    private Timestamp lastLoginTime;

    private Set<Role> roles;

    public UserInfo() {}

    public UserInfo(String username) {
        this.username = username;
    }

    public UserInfo(String id, String username, String password, String schoolCode, Character isLocked, Timestamp firstLoginTime, Timestamp lastLoginTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.schoolCode = schoolCode;
        this.isLocked = isLocked;
        this.firstLoginTime = firstLoginTime;
        this.lastLoginTime = lastLoginTime;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 将角色信息封装到GrantedAuthority中
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public Character getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(Character isLocked) {
        this.isLocked = isLocked;
    }

    public Timestamp getFirstLoginTime() {
        return firstLoginTime;
    }

    public void setFirstLoginTime(Timestamp firstLoginTime) {
        this.firstLoginTime = firstLoginTime;
    }

    public Timestamp getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Timestamp lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", schoolCode='" + schoolCode + '\'' +
                ", isLocked=" + isLocked +
                ", firstLoginTime=" + firstLoginTime +
                ", lastLoginTime=" + lastLoginTime +
                ", roles=" + roles +
                '}';
    }
}
