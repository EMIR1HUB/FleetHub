package com.suleimanov.vehiclecontrol.hr.controllers;

import com.suleimanov.vehiclecontrol.hr.models.Employee;
import com.suleimanov.vehiclecontrol.hr.services.EmployeeService;
import com.suleimanov.vehiclecontrol.hr.services.EmployeeTypeService;
import com.suleimanov.vehiclecontrol.hr.services.JobTitleService;
import com.suleimanov.vehiclecontrol.parameters.services.CountryService;
import com.suleimanov.vehiclecontrol.parameters.services.RegionService;
import com.suleimanov.vehiclecontrol.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.Principal;

@Controller
@RequestMapping("/profile")
public class ProfileController {
  @Autowired
  private EmployeeService employeeService;
  @Autowired
  private JobTitleService jobTitleService;
  @Autowired
  private EmployeeTypeService employeeTypeService;
  @Autowired
  private CountryService countryService;
  @Autowired
  private RegionService regionService;
  @Autowired
  private UserService userService;
  @Value("${upload.path}")
  private String uploadPhotoPath;

  @GetMapping()
  public String profile(Model model, Principal principal) {
    String userName = principal.getName();
    model.addAttribute("user", userService.findByUsername(userName).orElse(null));
    model.addAttribute("employee", employeeService.findByUsername(userName).orElse(null));
    model.addAttribute("jobTitles", jobTitleService.getJobsTitles());
    model.addAttribute("employeeTypes", employeeTypeService.getEmployeesTypes());
    model.addAttribute("countries", countryService.getCountries());
    model.addAttribute("regions", regionService.getRegions());
    return "/assets/profile";
  }

  @PostMapping("/uploadPhoto3")
  public String uploadFile3(@RequestParam("file") MultipartFile file, Principal principal) throws IOException {
    // Сохраняем файл
    file.transferTo(new File(uploadPhotoPath + principal.getName() + ".jpg"));

//    String userName = principal.getName();
//    employeeService.findByUsername(userName).ifPresent(employee -> {
//      employee.setPhoto(principal.getName() + ".jpg");
//      employeeService.save(employee);
//    });
    return "redirect:/profile";
  }

//  @RequestMapping(value = "/update", method = {RequestMethod.PUT, RequestMethod.GET})
  @PostMapping("/edit")
  public String edit(Employee employee, Principal principal) {

    employeeService.save(employee);
    return "redirect:/profile";
  }
}
