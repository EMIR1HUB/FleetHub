package com.suleimanov.vehiclecontrol.Controllers;

import com.suleimanov.vehiclecontrol.Models.EmployeeType;
import com.suleimanov.vehiclecontrol.Services.EmployeeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employees_types")
public class EmployeeTypeController {

  @Autowired
  private EmployeeTypeService employeeTypeService;

  @GetMapping()
  public String getEmployeeTypes(Model model) {
    List<EmployeeType> employeeTypeList = employeeTypeService.getEmployeesTypes();

    model.addAttribute("employeeTypes", employeeTypeList);
    return "employee_type";
  }

  @GetMapping("/findById")
  @ResponseBody
  public Optional<EmployeeType> findById(Integer id) {
    return employeeTypeService.findById(id);
  }

  @PostMapping("/addNew")
  public String addNew(EmployeeType employeeType) {
    employeeTypeService.save(employeeType);
    return "redirect:/employees_types";
  }

  @RequestMapping(value = "/update", method = {RequestMethod.PUT, RequestMethod.GET})
  public String update(EmployeeType employeeType) {
    employeeTypeService.save(employeeType);
    return "redirect:/employees_types";
  }

  @RequestMapping(value = "/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
  public String delete(Integer id) {
    employeeTypeService.delete(id);
    return "redirect:/employees_types";
  }
}
