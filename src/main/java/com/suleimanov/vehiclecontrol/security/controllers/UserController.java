package com.suleimanov.vehiclecontrol.security.controllers;

import com.suleimanov.vehiclecontrol.hr.models.Employee;
import com.suleimanov.vehiclecontrol.hr.services.EmployeeService;
import com.suleimanov.vehiclecontrol.hr.services.EmployeeTypeService;
import com.suleimanov.vehiclecontrol.hr.services.JobTitleService;
import com.suleimanov.vehiclecontrol.parameters.services.CountryService;
import com.suleimanov.vehiclecontrol.parameters.services.RegionService;
import com.suleimanov.vehiclecontrol.security.models.User;
import com.suleimanov.vehiclecontrol.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/security/users")
public class UserController {

  @Autowired private UserService userService;

  @Autowired private EmployeeService employeeService;

  @Autowired private CountryService countryService;

  @Autowired private RegionService regionService;

  @Autowired private JobTitleService jobTitleService;

  @Autowired private EmployeeTypeService employeeTypeService;

  @GetMapping()
  public String getUser(Model model, String keyword) {
    List<User> users = (keyword == null)
            ? userService.getUsers()
            : userService.getByKeyword(keyword);

    model.addAttribute("users", users);
    return "/security/users";
  }

  @RequestMapping("/{id}")
  @ResponseBody
  public User getById(@PathVariable Long id) {
    return userService.getById(id);
  }

  @GetMapping("/addNew")
  public String getAddNew(){
    return "/security/userAdd";
  }

  @PostMapping()
  public String addUser(User user) {
    userService.save(user);
    return "redirect:/security/users";
  }

  @PostMapping("/addData")
  public String addEmployee(Employee employee) {
    employeeService.save(employee);
    return "redirect:/security/users";
  }

  @GetMapping("/{op}/{id}")
  public String getEditAndDetails(@PathVariable String op,
                           @PathVariable Long id, Model model) {
    model.addAttribute("user", userService.getById(id));
    model.addAttribute("employee", employeeService.getByUserId(id).orElse(null));
    model.addAttribute("countries", countryService.getCountries());
    model.addAttribute("regions", regionService.getRegions());
    model.addAttribute("jobTitles", jobTitleService.getJobsTitles());
    model.addAttribute("employeeTypes", employeeTypeService.getEmployeesTypes());
    return "/security/user" + op;
  }

  @RequestMapping(value = "/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
  public String delete(@PathVariable Long id) {
    userService.delete(id);
    return "redirect:/security/users";
  }
}
