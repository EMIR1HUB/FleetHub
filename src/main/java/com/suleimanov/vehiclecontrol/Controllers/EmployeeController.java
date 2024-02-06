package com.suleimanov.vehiclecontrol.Controllers;

import com.suleimanov.vehiclecontrol.Models.Employee;
import com.suleimanov.vehiclecontrol.Services.*;
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
@RequestMapping("/employees")
public class EmployeeController {

  @Autowired private EmployeeService employeeService;
  @Autowired private JobTitleService jobTitleService;
  @Autowired private EmployeeTypeService employeeTypeService;
  @Autowired private CountryService countryService;
  @Autowired private RegionService regionService;

  @Value("${upload.path}")
  private String uploadPhotoPath;

  @GetMapping()
  public String getEmployees(Model model) {
    model.addAttribute("employees", employeeService.getEmployees());
    model.addAttribute("jobTitles", jobTitleService.getJobsTitles());
    model.addAttribute("employeeTypes", employeeTypeService.getEmployeesTypes());
    model.addAttribute("countries", countryService.getCountries());
    model.addAttribute("regions", regionService.getRegions());
    return "employee";
  }

  @GetMapping("/findById")
  @ResponseBody
  public Employee findById(Integer id) {
    return employeeService.findById(id).orElse(null);
  }

  @PostMapping("/addNew")
  public String addNew(Employee employee) {
    employee.setInitials(employee.getLastname() + " " +
            employee.getFirstname().substring(0, 1) + "." +
            employee.getOthername().substring(0, 1) + ".");
    employee.setPhoto("avatar_default.jpg");
    employeeService.save(employee);
    return "redirect:/employees";
  }

  @RequestMapping(value = "/update", method = {RequestMethod.PUT, RequestMethod.GET})
  public String update(Employee employee) {
    employeeService.save(employee);
//    @RequestParam("file") MultipartFile file
    return "redirect:/employees";
  }

  @RequestMapping(value = "/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
  public String delete(Integer id) {
    employeeService.delete(id);
    return "redirect:/employees";
  }

  @PostMapping("/uploadPhoto3")
  public String uploadFile3(@RequestParam("file") MultipartFile file, Principal principal) throws IOException {
    file.transferTo(new File(uploadPhotoPath + principal.getName() + ".jpg"));
    return "redirect:/employees";
  }

  // назначить сотруднику Username
  @GetMapping("/assignUsername")
  public String assignUsername(Integer id){
    employeeService.assignUsername(id);
    return "redirect:/employees";
  }
}
