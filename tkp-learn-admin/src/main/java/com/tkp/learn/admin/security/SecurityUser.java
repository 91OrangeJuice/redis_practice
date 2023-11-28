package com.tkp.learn.admin.security;

import com.tkp.learn.admin.model.common.LoginUser;
import com.tkp.learn.admin.model.enums.IdentityEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class SecurityUser extends LoginUser implements UserDetails {

    private static final long serialVersionUID = 7002885873265527882L;

    public SecurityUser(final LoginUser loginUser, IdentityEnum identityEnum){
        if (loginUser != null) {
            this.setWorkNo(loginUser.getWorkNo());
            this.setName(loginUser.getName());
            this.setIdentity(identityEnum);
            this.setOrgCode(loginUser.getOrgCode());
            this.setOrgName(loginUser.getOrgName());
            this.setOrgShortName(loginUser.getOrgShortName());
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

}
