package com.suleimanov.vehiclecontrol.Controllers;

import com.suleimanov.vehiclecontrol.Models.JobTitle;
import com.suleimanov.vehiclecontrol.Services.JobTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/job_titles")
public class JobTitleController {

  @Autowired
  private JobTitleService jobTitleService;

  @GetMapping()
  public String getJobTitle(Model model) {
    List<JobTitle> jobTitles = jobTitleService.getJobsTitles();

    model.addAttribute("jobTitles", jobTitles);
    return "job_title";
  }

  @GetMapping("/findById")
  @ResponseBody
  public Optional<JobTitle> findById(Integer id) {
    return jobTitleService.findById(id);
  }

  @PostMapping("/addNew")
  public String addNew(JobTitle jobTitle) {
    jobTitleService.save(jobTitle);
    return "redirect:/job_titles";
  }

  @RequestMapping(value = "/update", method = {RequestMethod.PUT, RequestMethod.GET})
  public String update(JobTitle jobTitle) {
    jobTitleService.save(jobTitle);
    return "redirect:/job_titles";
  }

  @RequestMapping(value = "/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
  public String delete(Integer id) {
    jobTitleService.delete(id);
    return "redirect:/job_titles";
  }
}
