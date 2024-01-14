package com.suleimanov.vehiclecontrol.Repositories;

import com.suleimanov.vehiclecontrol.Models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
