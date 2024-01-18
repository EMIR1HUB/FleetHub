package com.suleimanov.vehiclecontrol.Services;

import com.suleimanov.vehiclecontrol.Models.User;
import com.suleimanov.vehiclecontrol.Models.UserPrincipal;
import com.suleimanov.vehiclecontrol.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> user = userRepository.findByUsername(username);
    return user.map(UserPrincipal::new)
            .orElseThrow(() -> new UsernameNotFoundException(username + " not found!"));
  }
}
