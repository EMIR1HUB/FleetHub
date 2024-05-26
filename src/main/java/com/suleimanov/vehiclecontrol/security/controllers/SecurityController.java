package com.suleimanov.vehiclecontrol.security.controllers;

import com.suleimanov.vehiclecontrol.security.models.User;
import com.suleimanov.vehiclecontrol.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @author luck
 */

@Controller
@RequestMapping("/register")
public class SecurityController {
  @Autowired
  private UserService userService;

  @GetMapping()
  public String register(){
    return "registration";
  }

  @PostMapping()
  public RedirectView addNew(User user, RedirectAttributes redir) {
    userService.save(user);
    // после регистрации перенаправляет user на страницу /login
    redir.addFlashAttribute("username", user.getUsername());
    redir.addFlashAttribute("message", "Регистрация успешно совершена!");
    return new RedirectView("/login", true);
  }
}
