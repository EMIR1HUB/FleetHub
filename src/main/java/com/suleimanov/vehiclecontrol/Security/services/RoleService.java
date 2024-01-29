package com.suleimanov.vehiclecontrol.Security.services;

import com.suleimanov.vehiclecontrol.Models.User;
import com.suleimanov.vehiclecontrol.Repositories.UserRepository;
import com.suleimanov.vehiclecontrol.Security.exceptions.UserNotFoundException;
import com.suleimanov.vehiclecontrol.Security.models.Role;
import com.suleimanov.vehiclecontrol.Security.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RoleService {
  @Autowired
  private RoleRepository roleRepository;
  @Autowired
  private UserRepository userRepository;

  public List<Role> getRoles() {
    return roleRepository.findAll();
  }

  public Role findById(Long id) {
    return roleRepository.findById(id).orElse(null);
  }

  public void save(Role role) {
    roleRepository.save(role);
  }

  public void delete(Long id) {
    roleRepository.deleteById(id);
  }

  public void assignUserRole(Long userId, Long roleId) {
    userRepository.findById(userId).ifPresentOrElse(user -> {
      roleRepository.findById(roleId).ifPresent(role -> {
        user.getRoles().add(role);
        userRepository.save(user);
      });
    }, () -> {
      throw new UserNotFoundException("Пользователь с id " + userId + " не найден");
    });
  }

  public void unassignUserRole(Long userId, Long roleId) {
    userRepository.findById(userId).ifPresentOrElse(user -> {
      user.getRoles().removeIf(role -> role.getId().equals(roleId));
      userRepository.save(user);
    }, () -> {
      throw new UserNotFoundException("Пользователь с id " + userId + " не найден");
    });
  }

  public Set<Role> getUserRoles(User user) {
    return user.getRoles();
  }

  public Set<Role> getUserNotRoles(User user) {
    return roleRepository.getUserNotRoles(user.getId());
  }
}
