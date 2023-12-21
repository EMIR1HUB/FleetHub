package com.suleimanov.vehiclecontrol.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeTypeController {
  @GetMapping("/employees_types")
  public String getEmployeeTypes() {
    return "employee_types";
  }
}
