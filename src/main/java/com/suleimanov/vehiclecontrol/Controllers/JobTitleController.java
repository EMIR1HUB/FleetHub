package com.suleimanov.vehiclecontrol.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JobTitleController {

  @GetMapping("/job_titles")
  public String getJobTitle() {
    return "job_title";
  }
}
