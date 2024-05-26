package com.suleimanov.vehiclecontrol.security.controllers;

import com.suleimanov.vehiclecontrol.hr.services.EmployeeService;
import com.suleimanov.vehiclecontrol.security.models.User;
import com.suleimanov.vehiclecontrol.security.models.Role;
import com.suleimanov.vehiclecontrol.security.services.RoleService;
import com.suleimanov.vehiclecontrol.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/security/roles")
public class RoleController {

  @Autowired
  private RoleService roleService;
  @Autowired
  private UserService userService;

  @Autowired
  private EmployeeService employeeService;

  @GetMapping()
  public String getRoles(Model model, String keyword) {
    List<Role> roles = keyword == null
            ? roleService.getRoles()
            : roleService.getByKeyword(keyword);

    model.addAttribute("roles", roles);
    return "/security/roles";
  }

  @RequestMapping("/{id}")
  @ResponseBody
  public Role getById(@PathVariable Long id) {
    return roleService.getById(id);
  }

  @GetMapping("/addNew")
  public String getAddNew(){
    return "/security/roleAdd";
  }

  @PostMapping()
  public String addNew(Role role) {
    roleService.save(role);
    return "redirect:/security/roles";
  }

  @GetMapping("/{op}/{id}")
  public String edit(@PathVariable String op, @PathVariable Long id, Model model) {
    model.addAttribute("role", roleService.getById(id));
    return "/security/role" + op;
  }

  @RequestMapping(value = "/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
  public String delete(@PathVariable Long id) {
    roleService.delete(id);
    return "redirect:/security/roles";
  }

  @GetMapping("/user/edit/{id}")
  public String editUser(@PathVariable Long id, Model model) {
    User user = userService.getById(id);
    model.addAttribute("user", user);
    model.addAttribute("userRoles", roleService.getUserRoles(user));
    model.addAttribute("userNotRoles", roleService.getRolesNotUser(user));
    model.addAttribute("employee", employeeService.getByUserId(user.getId()).orElse(null));
    return "/security/userRolesEdit";
  }

  @GetMapping("/user/assign/{userId}/{roleId}")
  public String assignRole(@PathVariable Long userId, @PathVariable Long roleId) {
    roleService.assignUserRole(userId, roleId);
    return "redirect:/security/roles/user/edit/" + userId;
  }

  @GetMapping("/user/unassign/{userId}/{roleId}")
  public String unassignRole(@PathVariable Long userId, @PathVariable Long roleId){
    roleService.unassignUserRole(userId, roleId);
    return "redirect:/security/roles/user/edit/" + userId;
  }
}
