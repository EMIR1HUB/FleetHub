package com.suleimanov.vehiclecontrol.Services;

import com.suleimanov.vehiclecontrol.Models.Employee;
import com.suleimanov.vehiclecontrol.Repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
  @Autowired
  private EmployeeRepository employeeRepository;

  public List<Employee> getEmployees(){
    return employeeRepository.findAll();
  }

  public void save(Employee employee){
    employeeRepository.save(employee);
  }

  public Optional<Employee> findById(Integer id){
    return employeeRepository.findById(id);
  }

  public void delete(Integer id) {
    employeeRepository.deleteById(id);
  }
}
