package com.suleimanov.vehiclecontrol.security.services;

import com.suleimanov.vehiclecontrol.hr.models.Employee;
import com.suleimanov.vehiclecontrol.hr.services.EmployeeService;
import com.suleimanov.vehiclecontrol.security.models.Role;
import com.suleimanov.vehiclecontrol.security.models.User;
import com.suleimanov.vehiclecontrol.security.repositories.RoleRepository;
import com.suleimanov.vehiclecontrol.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
  @Autowired
  private BCryptPasswordEncoder passwordEncoder;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private EmployeeService employeeService;

  @Autowired
  private RoleRepository roleRepository;

  public List<User> getUsers() {
    return userRepository.findAll();
  }

  public List<User> getByKeyword(String keyword) {
    return userRepository.findByKeyword(keyword);
  }

  public User getById(Long id) {
    return userRepository.findById(id).orElse(null);
  }

  public Optional<User> findByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  public Optional<User> getByEmail(String email){
    return userRepository.findByEmail(email);
  }

  public void delete(Long id) {
    userRepository.deleteById(id);
  }

  public void save(User user) {
    if (user.getPassword() == null){  // У пользователя не нужно обновлять пароль
      userRepository.update(user.getId(), user.getUsername(), user.getEmail());
    }
    else {
      Set<Role> roles;
      if (!(roles = roleRepository.findAllByUserId(user.getId())).isEmpty()){
        user.setRoles(roles);
      }
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      userRepository.save(user);
    }
    setDataByEmployeeEmail(user);
  }

  public void saveAccountForEmployee(User user, Long employeeId) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.save(user);

    Employee employee = employeeService.getById(employeeId);
    if (employee.getUserid() == null){
      employee.setUserid(user.getId());
    }
    if (!employee.getEmail().equals(user.getEmail())){
      employee.setEmail(user.getEmail());
    }
    employeeService.save(employee);
  }

  private void setDataByEmployeeEmail(User user) {
    Employee employee = employeeService.getByEmail(user.getEmail()).orElse(null);
    if (employee != null && !employee.getUserid().equals(user.getId())) {   // TODO check
      employee.setUserid(user.getId());
      employeeService.save(employee);
    }
  }
}
