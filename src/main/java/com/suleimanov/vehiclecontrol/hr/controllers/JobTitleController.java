package com.suleimanov.vehiclecontrol.hr.controllers;

import com.suleimanov.vehiclecontrol.hr.models.JobTitle;
import com.suleimanov.vehiclecontrol.hr.services.JobTitleService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/hr/job_titles")
public class JobTitleController {

  @Autowired
  private JobTitleService jobTitleService;

  @GetMapping()
  public String getAll(Model model, String keyword) {
    List<JobTitle> jobTitles = (keyword == null)
            ? jobTitleService.getJobsTitles()
            : jobTitleService.getByKeyword(keyword);

    model.addAttribute("titles", jobTitles);
    return "/hr/jobTitles";
  }

  @GetMapping("/page/{field}")
  public String getAllWithSort(@PathVariable String field,
                               @PathParam("sortDir") String sortDir, Model model) {
    model.addAttribute("titles", jobTitleService.getJobTitleWithSort(field, sortDir));
    model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
    return "/hr/jobTitles";
  }

  @GetMapping("/{id}")
  @ResponseBody
  public JobTitle getById(@PathVariable Long id) {
    return jobTitleService.getById(id);
  }

  @PostMapping()
  public String add(JobTitle jobTitle) {
    jobTitleService.save(jobTitle);
    return "redirect:/hr/job_titles";
  }

  @GetMapping("/{op}/{id}")
  public String getDetails(@PathVariable String op,
                           @PathVariable Long id, Model model) {
    model.addAttribute("title", jobTitleService.getById(id));
    return "/hr/jobTitle" + op;
  }

  @RequestMapping(value = "/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
  public String delete(@PathVariable Long id) {
    jobTitleService.delete(id);
    return "redirect:/hr/job_titles";
  }
}
