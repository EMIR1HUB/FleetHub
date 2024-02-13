package com.suleimanov.vehiclecontrol.hr.services;

import com.suleimanov.vehiclecontrol.hr.models.JobTitle;
import com.suleimanov.vehiclecontrol.hr.repositories.JobTitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobTitleService {

  @Autowired
  private JobTitleRepository jobTitleRepository;

  public List<JobTitle> getJobsTitles(){
    return jobTitleRepository.findAll();
  }

  public void save(JobTitle jobTitle){
    jobTitleRepository.save(jobTitle);
  }

  public Optional<JobTitle> findById(Integer id){
    return jobTitleRepository.findById(id);
  }

  public void delete(Integer id) {
    jobTitleRepository.deleteById(id);
  }
}
