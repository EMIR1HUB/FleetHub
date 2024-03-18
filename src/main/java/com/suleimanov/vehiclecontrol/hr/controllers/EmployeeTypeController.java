package com.suleimanov.vehiclecontrol.hr.controllers;

import com.suleimanov.vehiclecontrol.hr.models.EmployeeType;
import com.suleimanov.vehiclecontrol.hr.services.EmployeeTypeService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/hr/employees_types")
public class EmployeeTypeController {

  @Autowired
  private EmployeeTypeService employeeTypeService;

  @GetMapping()
  public String getAll(Model model, String keyword) {
    List<EmployeeType> employeeTypeList = (keyword == null)
            ? employeeTypeService.getEmployeesTypes()
            : employeeTypeService.getByKeyword(keyword);

    model.addAttribute("types", employeeTypeList);
    return "/hr/employeeTypes";
  }

  @GetMapping("/page/{field}")
  public String getAllWithSort(@PathVariable String field,
                               @PathParam("sortDir") String sortDir, Model model) {
    model.addAttribute("types", employeeTypeService.getEmployeesTypeStatusWithSort(field, sortDir));
    model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
    return "/hr/employeeTypes";
  }

  @GetMapping("/{id}")
  @ResponseBody
  public EmployeeType getById(@PathVariable Long id) {
    return employeeTypeService.getById(id);
  }

  @PostMapping()
  public String add(EmployeeType employeeType) {
    employeeTypeService.save(employeeType);
    return "redirect:/hr/employees_types";
  }

  @GetMapping("/{op}/{id}")
  public String getDetails(@PathVariable String op,
                           @PathVariable Long id, Model model){
    model.addAttribute("type", employeeTypeService.getById(id));
    return "/hr/employeeType"+op;
  }

  @RequestMapping(value = "/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
  public String delete(@PathVariable Long id) {
    employeeTypeService.delete(id);
    return "redirect:/hr/employees_types";
  }
}
