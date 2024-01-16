package com.suleimanov.vehiclecontrol.Services;

import com.suleimanov.vehiclecontrol.Models.UserAccount;
import com.suleimanov.vehiclecontrol.Models.UserPrincipal;
import com.suleimanov.vehiclecontrol.Repositories.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserAccountDetailsService implements UserDetailsService {

  @Autowired UserAccountRepository userAccountRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserAccount userAccount = userAccountRepository.findByUsername(username);
    if(userAccount == null){
      throw new UsernameNotFoundException("User not found!");
    }
    return new UserPrincipal(userAccount);
  }
}
