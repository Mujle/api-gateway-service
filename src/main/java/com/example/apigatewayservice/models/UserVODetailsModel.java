package com.example.apigatewayservice.models;

import com.example.apigatewayservice.security.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserVODetailsModel extends UserVO implements UserDetails {

    public UserVODetailsModel(UserVO userVO) {
        super(userVO);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();

        List<UserRole> enumRoles = getRoles()
                .stream()
                .map(role -> Enum.valueOf(UserRole.class, role.getRole()))
                .collect(Collectors.toList());

        enumRoles.forEach(userRole -> grantedAuthorities.addAll(userRole.getGrantedAuthority()));

        return grantedAuthorities;
    }

    @Override
    public String getUsername() {
        return super.getUserName();
    }
    @Override
    public String getPassword() {
        return super.getPassword();
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
}
