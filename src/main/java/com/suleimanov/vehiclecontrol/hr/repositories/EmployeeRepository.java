package com.suleimanov.vehiclecontrol.hr.repositories;

import com.suleimanov.vehiclecontrol.hr.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

  @Query(value = "SELECT e FROM Employee e WHERE " +
          "concat(e.fullname, e.email, e.country.description, e.city) LIKE %?1%") // e.jobTitle.description
  List<Employee> findByKeyword(String keyword);

//  Employee findByUsername(String username);
}
