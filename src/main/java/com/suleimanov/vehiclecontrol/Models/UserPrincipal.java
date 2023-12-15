package com.suleimanov.vehiclecontrol.Models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {

  private UserAccount userAccount;

  public UserPrincipal(UserAccount user) {
    this.userAccount = userAccount;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
  //	return Collections.singleton(new SimpleGrantedAuthority("USER"));
    List<GrantedAuthority> authorities = new ArrayList<>();
    for (UserAccount role : userAccount.getRoles()) {
      authorities.add(new SimpleGrantedAuthority(role.getFirstname()));
    }
    return authorities;
  }

  @Override
  public String getPassword() {
    return userAccount.getPassword();
  }

  @Override
  public String getUsername() {
    return userAccount.getUsername();
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
