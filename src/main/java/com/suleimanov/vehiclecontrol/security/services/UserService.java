package com.suleimanov.vehiclecontrol.security.services;

import com.suleimanov.vehiclecontrol.hr.models.Employee;
import com.suleimanov.vehiclecontrol.hr.services.EmployeeService;
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
  @Autowired
  private EmployeeService employeeService;

  public List<User> getUsers() {
    return userRepository.findAll();
  }

  public Optional<User> findById(Long id) {
    return userRepository.findById(id);
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
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.save(user);
    setDataByEmployeeEmail(user);
  }

  public void saveForEmployee(User user, Long employeeId) {
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
    if (employee != null) {
      employee.setUserid(user.getId());
      employeeService.save(employee);
    }
  }
}
