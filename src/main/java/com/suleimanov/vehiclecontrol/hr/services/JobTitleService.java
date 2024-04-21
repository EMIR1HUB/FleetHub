package com.suleimanov.vehiclecontrol.hr.services;

import com.suleimanov.vehiclecontrol.hr.models.JobTitle;
import com.suleimanov.vehiclecontrol.hr.repositories.JobTitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobTitleService {

  @Autowired
  private JobTitleRepository jobTitleRepository;

  public List<JobTitle> getJobsTitles(){
    return jobTitleRepository.findAll();
  }

  public List<JobTitle> getByKeyword(String keyword) {
    return jobTitleRepository.findByKeyword(keyword);
  }

  public void save(JobTitle jobTitle){
    jobTitleRepository.save(jobTitle);
  }

  public JobTitle getById(Long id){
    return jobTitleRepository.findById(id).orElse(null);
  }

  public void delete(Long id) {
    jobTitleRepository.deleteById(id);
  }

  public List<JobTitle> getJobTitleWithSort(String field, String direction) {
    Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())
            ? Sort.by(field).ascending() : Sort.by(field).descending();
    return jobTitleRepository.findAll(sort);
  }
}
