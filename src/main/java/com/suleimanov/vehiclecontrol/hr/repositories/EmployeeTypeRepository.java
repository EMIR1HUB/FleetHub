package com.suleimanov.vehiclecontrol.hr.repositories;

import com.suleimanov.vehiclecontrol.hr.models.EmployeeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeTypeRepository extends JpaRepository<EmployeeType, Long> {

  @Query(value = "SELECT e FROM EmployeeType e WHERE " +
          "concat(e.description, e.details) LIKE %?1%")
  List<EmployeeType> findByKeyword(String keyword);
}
