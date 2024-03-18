package com.suleimanov.vehiclecontrol.hr.services;

import com.suleimanov.vehiclecontrol.hr.models.Employee;
import com.suleimanov.vehiclecontrol.hr.repositories.EmployeeRepository;
import com.suleimanov.vehiclecontrol.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
  @Autowired private EmployeeRepository employeeRepository;
  @Autowired private UserRepository userRepository;

  public List<Employee> getEmployees(){
    return employeeRepository.findAll();
  }

  public List<Employee> getByKeyword(String keyword){
    return employeeRepository.findByKeyword(keyword);
  }

  public void save(Employee employee){
    employeeRepository.save(employee);
  }

  public Employee getById(Long id){
    return employeeRepository.findById(id).orElse(null);
  }

  public void delete(Long id) {
    employeeRepository.deleteById(id);
  }

  public List<Employee> getEmployeeWithSort(String field, String direction) {
    // asc or desc
    Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())
            ? Sort.by(field).ascending() : Sort.by(field).descending();
    return employeeRepository.findAll(sort);
  }


//  public Optional<Employee> findByUsername(String username){
//    return Optional.ofNullable(employeeRepository.findByUsername(username));
//  }

  // Установить username сотрудника, firstname and lastname которого совпадают
//  public void assignUsername(Integer id){
//    Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Сотрудник с id=" + id + "не найден"));
//    Optional<User> user = userRepository.findByFirstnameAndLastname(employee.getFirstname(), employee.getLastname());
//    employee.setUsername(user.map(User::getUsername).orElse(null));
//    employeeRepository.save(employee);
//  }

//  public void assignUsername(Integer id){
//    Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Сотрудник с id=" + id + "не найден"));
//    Optional<User> user = userRepository.findByEmail(employee.getEmail());
//    employee.setUsername(user.map(User::getUsername).orElse(null));
//    employeeRepository.save(employee);
//  }
}
