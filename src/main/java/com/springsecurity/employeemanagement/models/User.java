package com.springsecurity.employeemanagement.models;

import com.springsecurity.employeemanagement.models.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Document(collection = "user")
@AllArgsConstructor
@Builder
public class User implements UserDetails {

    @Id
    private String id;

    private String userName;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        if(this.role.equals(Role.USER)) {
            grantedAuthorityList.add(new SimpleGrantedAuthority(Role.USER.toString()));
        } else if(this.role.equals(Role.ADMIN)) {
            grantedAuthorityList.add(new SimpleGrantedAuthority(Role.ADMIN.toString()));
        } else if(this.role.equals(Role.FLEET_COMPANY)){
            grantedAuthorityList.add(new SimpleGrantedAuthority(Role.FLEET_COMPANY.toString()));
        } else if(this.role.equals(Role.SERVICE_CENTER)) {
            grantedAuthorityList.add(new SimpleGrantedAuthority(Role.SERVICE_CENTER.toString()));
        }
        return grantedAuthorityList;
    }

    @Override
    public String getUsername() {
        return userName;
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
        return true;
    }
}
