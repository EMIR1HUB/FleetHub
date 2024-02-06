package com.suleimanov.vehiclecontrol.Security.services;

import com.suleimanov.vehiclecontrol.Security.models.User;
import com.suleimanov.vehiclecontrol.Models.UserPrincipal;
import com.suleimanov.vehiclecontrol.Security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> user = userRepository.findByUsername(username);
    return user.map(UserPrincipal::new).orElseThrow(
            () -> new UsernameNotFoundException(username + " не найден!"));
  }
}
