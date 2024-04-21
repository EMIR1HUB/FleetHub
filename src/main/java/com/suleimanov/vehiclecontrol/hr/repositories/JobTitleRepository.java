package com.suleimanov.vehiclecontrol.hr.repositories;

import com.suleimanov.vehiclecontrol.hr.models.JobTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobTitleRepository extends JpaRepository<JobTitle, Long> {

  @Query(value = "SELECT jt FROM JobTitle jt WHERE " +
          "concat(jt.description, jt.details) LIKE %?1%")
  List<JobTitle> findByKeyword(String keyword);
}
