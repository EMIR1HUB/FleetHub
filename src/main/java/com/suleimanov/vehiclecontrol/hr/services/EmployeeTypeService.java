package com.suleimanov.vehiclecontrol.hr.services;

import com.suleimanov.vehiclecontrol.hr.models.EmployeeType;
import com.suleimanov.vehiclecontrol.hr.repositories.EmployeeTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeTypeService {

  @Autowired
  private EmployeeTypeRepository employeeTypeRepository;

  public List<EmployeeType> getEmployeesTypes(){
    return employeeTypeRepository.findAll();
  }

  public List<EmployeeType> getByKeyword(String keyword){return employeeTypeRepository.findByKeyword(keyword);}

  public void save(EmployeeType employeeType){
    employeeTypeRepository.save(employeeType);
  }

  public EmployeeType getById(Long id){
    return employeeTypeRepository.findById(id).orElse(null);
  }

  public void delete(Long id) {
    employeeTypeRepository.deleteById(id);
  }

  public List<EmployeeType> getEmployeesTypeStatusWithSort(String field, String direction) {
    Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())
            ? Sort.by(field).ascending() : Sort.by(field).descending();
    return employeeTypeRepository.findAll(sort);
  }
}
