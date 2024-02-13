package com.suleimanov.vehiclecontrol.hr.repositories;

import com.suleimanov.vehiclecontrol.hr.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

  Employee findByUsername(String username);
}
