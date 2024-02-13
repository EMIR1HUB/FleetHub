package com.suleimanov.vehiclecontrol.security.services;

import com.suleimanov.vehiclecontrol.security.models.User;
import com.suleimanov.vehiclecontrol.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
  @Autowired
  private BCryptPasswordEncoder passwordEncoder;
  @Autowired
  private UserRepository userRepository;

  public List<User> getUsers(){
    return userRepository.findAll();
  }

  public Optional<User> findById(Long id){
    return userRepository.findById(id);
  }

  public Optional<User> findByUsername(String username){
    return userRepository.findByUsername(username);
  }

  public void delete(Long id) {
    userRepository.deleteById(id);
  }

  public void save(User user){
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.save(user);
  }

}
