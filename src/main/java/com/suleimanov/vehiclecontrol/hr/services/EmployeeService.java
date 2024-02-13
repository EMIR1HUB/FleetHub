package com.suleimanov.vehiclecontrol.hr.services;

import com.suleimanov.vehiclecontrol.Exceptions.EmployeeNotFoundException;
import com.suleimanov.vehiclecontrol.hr.models.Employee;
import com.suleimanov.vehiclecontrol.hr.repositories.EmployeeRepository;
import com.suleimanov.vehiclecontrol.security.models.User;
import com.suleimanov.vehiclecontrol.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
  @Autowired
  private EmployeeRepository employeeRepository;
  @Autowired private UserRepository userRepository;

  public List<Employee> getEmployees(){
    return employeeRepository.findAll();
  }

  public void save(Employee employee){
    employeeRepository.save(employee);
  }

  public Optional<Employee> findById(Integer id){
    return employeeRepository.findById(id);
  }

  public Optional<Employee> findByUsername(String username){
    return Optional.ofNullable(employeeRepository.findByUsername(username));
  }

  public void delete(Integer id) {
    employeeRepository.deleteById(id);
  }

  // Установить username сотрудника, firstname and lastname которого совпадают
//  public void assignUsername(Integer id){
//    Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Сотрудник с id=" + id + "не найден"));
//    Optional<User> user = userRepository.findByFirstnameAndLastname(employee.getFirstname(), employee.getLastname());
//    employee.setUsername(user.map(User::getUsername).orElse(null));
//    employeeRepository.save(employee);
//  }

  public void assignUsername(Integer id){
    Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Сотрудник с id=" + id + "не найден"));
    Optional<User> user = userRepository.findByEmail(employee.getEmail());
    employee.setUsername(user.map(User::getUsername).orElse(null));
    employeeRepository.save(employee);
  }
}
