package com.suleimanov.vehiclecontrol.Controllers;

import com.suleimanov.vehiclecontrol.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/profile")
public class ProfileController {
  @Autowired private EmployeeService employeeService;

  @GetMapping()
  public String profile(Model model, Principal principal){
    String userName = principal.getName();
    model.addAttribute("employee", employeeService.findByUsername(userName));
    return "profile";
  }
}
