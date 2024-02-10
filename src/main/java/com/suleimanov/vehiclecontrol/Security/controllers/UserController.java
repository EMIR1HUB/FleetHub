package com.suleimanov.vehiclecontrol.Security.controllers;

import com.suleimanov.vehiclecontrol.Security.models.User;
import com.suleimanov.vehiclecontrol.Security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;

  private User userById;

  @GetMapping()
  public String getUser(Model model) {
    model.addAttribute("users", userService.getUsers());
    return "user";
  }

  @RequestMapping("/findById")
  @ResponseBody
  public User findById(Long id) {
    userById = userService.findById(id).orElse(null);
    return userById;
  }

  @PostMapping("/registration")
  public RedirectView addNew(User user, RedirectAttributes redir) {
    userService.save(user);
    // после регистрации перенаправляет user на страницу /login
    redir.addFlashAttribute("username", user.getUsername());
    redir.addFlashAttribute("message", "Регистрация успешно совершена!");
    return new RedirectView("/login", true);
  }

  @PostMapping("/addNew")
  public String addNew(User user) {
    userService.save(user);
    return "redirect:/users";
  }

  @RequestMapping(value = "/update", method = {RequestMethod.PUT, RequestMethod.GET})
  public String update(User user) {
    user.setRoles(userById.getRoles());
    userService.save(user);
    return "redirect:/users";
  }

  @RequestMapping(value = "/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
  public String delete(Long id) {
    userService.delete(id);
    return "redirect:/users";
  }
}
