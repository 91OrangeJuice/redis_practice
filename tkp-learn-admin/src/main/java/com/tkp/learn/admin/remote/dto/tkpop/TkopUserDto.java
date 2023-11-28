package com.tkp.learn.admin.remote.dto.tkpop;

import com.tkp.learn.admin.model.enums.IdentityEnum;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/7/28
 * @version: 1.0
 */
@Data
public class TkopUserDto implements UserDetails {

    private static final long serialVersionUID = 5291872633250446520L;
    private String loginId;
    private String name;
    private String org;
    private String branchCode;
    private String upOrg;
    private String orgName;
    private String userType;
    private IdentityEnum identityEnum;
    private String dept;
    private String deptName;
    private List<SsoRoleBo> authorites;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
