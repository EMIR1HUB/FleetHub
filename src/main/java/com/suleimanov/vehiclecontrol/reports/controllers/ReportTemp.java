package com.suleimanov.vehiclecontrol.reports.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reports/vehicles")
public class ReportTemp {

  @GetMapping()
  public String getReports(Model model, String keyword) {

    return "/reports/vehicles";
  }


}