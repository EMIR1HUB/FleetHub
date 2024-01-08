package com.suleimanov.vehiclecontrol.Services;

import com.suleimanov.vehiclecontrol.Models.EmployeeType;
import com.suleimanov.vehiclecontrol.Repositories.EmployeeTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeTypeService {

  @Autowired
  private EmployeeTypeRepository employeeTypeRepository;

  public List<EmployeeType> getEmployeesTypes(){
    return employeeTypeRepository.findAll();
  }

  public void save(EmployeeType employeeType){
    employeeTypeRepository.save(employeeType);
  }

  public Optional<EmployeeType> findById(Integer id){
    return employeeTypeRepository.findById(id);
  }

  public void delete(Integer id) {
    employeeTypeRepository.deleteById(id);
  }
}
