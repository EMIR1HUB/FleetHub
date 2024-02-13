package com.suleimanov.vehiclecontrol.hr.repositories;

import com.suleimanov.vehiclecontrol.hr.models.JobTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobTitleRepository extends JpaRepository<JobTitle, Integer> {

}
