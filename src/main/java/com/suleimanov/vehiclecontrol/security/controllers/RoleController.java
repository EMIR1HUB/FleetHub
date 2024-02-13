package com.suleimanov.vehiclecontrol.security.controllers;

import com.suleimanov.vehiclecontrol.security.models.User;
import com.suleimanov.vehiclecontrol.security.models.Role;
import com.suleimanov.vehiclecontrol.security.services.RoleService;
import com.suleimanov.vehiclecontrol.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/roles")
public class RoleController {

  @Autowired
  private RoleService roleService;
  @Autowired
  private UserService userService;

  @GetMapping()
  public String getRoles(Model model) {
    model.addAttribute("roles", roleService.getRoles());
    return "role";
  }

  @GetMapping("/findById")
  @ResponseBody
  public Role findById(Long id) {
    return roleService.findById(id);
  }

  @PostMapping("/addNew")
  public String addNew(Role role) {
    roleService.save(role);
    return "redirect:/roles";
  }

  @RequestMapping(value = "/update", method = {RequestMethod.PUT, RequestMethod.GET})
  public String update(Role role) {
    roleService.save(role);
    return "redirect:/roles";
  }

  @RequestMapping(value = "/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
  public String delete(Long id) {
    roleService.delete(id);
    return "redirect:/roles";
  }

  @GetMapping("/user/edit/{id}")
  public String editUser(@PathVariable Long id, Model model) {
    User user = userService.findById(id).orElse(null);
    model.addAttribute("user", user);
    model.addAttribute("userRoles", roleService.getUserRoles(user));
    model.addAttribute("userNotRoles", roleService.getUserNotRoles(user));
    return "user_edit";
  }

  @GetMapping("/user/assign/{userId}/{roleId}")
  public String assignRole(@PathVariable Long userId, @PathVariable Long roleId) {
    roleService.assignUserRole(userId, roleId);
    return "redirect:/roles/user/edit/" + userId;
  }

  @GetMapping("/user/unassign/{userId}/{roleId}")
  public String unassignRole(@PathVariable Long userId, @PathVariable Long roleId){
    roleService.unassignUserRole(userId, roleId);
    return "redirect:/roles/user/edit/" + userId;
  }
}
