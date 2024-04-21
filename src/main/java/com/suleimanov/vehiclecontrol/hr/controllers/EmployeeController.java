package com.suleimanov.vehiclecontrol.hr.controllers;

import com.suleimanov.vehiclecontrol.hr.models.Employee;
import com.suleimanov.vehiclecontrol.hr.services.EmployeeService;
import com.suleimanov.vehiclecontrol.hr.services.EmployeeTypeService;
import com.suleimanov.vehiclecontrol.hr.services.JobTitleService;
import com.suleimanov.vehiclecontrol.parameters.services.CountryService;
import com.suleimanov.vehiclecontrol.parameters.services.RegionService;
import com.suleimanov.vehiclecontrol.security.models.User;
import com.suleimanov.vehiclecontrol.security.services.UserService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/hr/employees")
public class EmployeeController {
  @Autowired private EmployeeService employeeService;
  @Autowired private JobTitleService jobTitleService;
  @Autowired private EmployeeTypeService employeeTypeService;
  @Autowired private CountryService countryService;
  @Autowired private RegionService regionService;
  @Autowired private UserService userService;

//
//  @Value("${upload.path}")
//  private String uploadPhotoPath;

  private void addModelAttributes(Model model) {
    model.addAttribute("jobTitles", jobTitleService.getJobsTitles());
    model.addAttribute("employeeTypes", employeeTypeService.getEmployeesTypes());
    model.addAttribute("countries", countryService.getCountries());
    model.addAttribute("regions", regionService.getRegions());
  }

  @GetMapping()
  public String getEmployees(Model model, String keyword) {
    List<Employee> employees = (keyword == null)
            ? employeeService.getEmployees()
            : employeeService.getByKeyword(keyword);

    model.addAttribute("employees", employees);
    return "/hr/employees";
  }

  @GetMapping("/page/{field}")
  public String getAllWithSort(@PathVariable("field") String field,
                               @PathParam("sortDir") String sortDir, Model model) {
    model.addAttribute("employees", employeeService.getEmployeeWithSort(field, sortDir));
    model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
    return "/hr/employees";
  }

  @GetMapping("/{id}")
  @ResponseBody
  public Employee findById(@PathVariable Long id) {
    return employeeService.getById(id);
  }

  @GetMapping("/addNew")
  public String getAddNew(Model model) {
    addModelAttributes(model);
    return "/hr/employeeAdd";
  }

  @PostMapping()
  public String addNew(Employee employee, @RequestParam(value = "file", required = false) MultipartFile file) {
//    employee.setPhoto("avatar_default.jpg");
    employeeService.save(employee);
    return "redirect:/hr/employees";
  }

  @GetMapping("/{op}/{id}")
  public String getEditAndDetails(@PathVariable String op,
                                  @PathVariable Long id, Model model) {
    addModelAttributes(model);
    model.addAttribute("employee", employeeService.getById(id));
    return "/hr/employee" + op;
  }

  @RequestMapping(value = "/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
  public String delete(@PathVariable Long id) {
    employeeService.delete(id);
    return "redirect:/hr/employees";
  }

  @PostMapping("/registration")
  public String addAccount(User user, @RequestParam("employeeid") Long employeeId) {
    userService.saveForEmployee(user, employeeId);
    return "redirect:/hr/employees";
  }

//  @RequestMapping(value = "/update", method = {RequestMethod.PUT, RequestMethod.GET})
//  public String update(Employee employee) {
//    employeeService.save(employee);
////    @RequestParam("file") MultipartFile file
//    return "redirect:/employees";
//  }
//
//  @RequestMapping(value = "/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
//  public String delete(Integer id) {
//    employeeService.delete(id);
//    return "redirect:/employees";
//  }
//
//  @PostMapping("/uploadPhoto3")
//  public String uploadFile3(@RequestParam("file") MultipartFile file, Principal principal) throws IOException {
//    file.transferTo(new File(uploadPhotoPath + principal.getName() + ".jpg"));
//    return "redirect:/employees";
//  }
//
//  // назначить сотруднику Username
//  @GetMapping("/assignUsername")
//  public String assignUsername(Integer id){
//    employeeService.assignUsername(id);
//    return "redirect:/employees";
//  }
}
