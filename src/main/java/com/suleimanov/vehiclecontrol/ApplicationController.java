package com.suleimanov.vehiclecontrol;

import com.suleimanov.vehiclecontrol.Models.User;
import com.suleimanov.vehiclecontrol.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ApplicationController {

  @Autowired
  private UserService userService;

  @GetMapping("/index")
  public String getHome() {
    logAuthenticationDetails();
    return "index";
  }

  @GetMapping("/login")
  public String login(){
    return "login";
  }

  @GetMapping("/logout")
  public String logout(){
    return "login";
  }

  // Temp
  private void logAuthenticationDetails() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null && authentication.isAuthenticated()) {
      // Вывод информации в лог
      UserDetails userDetails = (UserDetails) authentication.getPrincipal();
      System.out.println("Authentication class: " + authentication.getClass().getName());
      System.out.println("Principal " + userDetails);
      System.out.println("UserName " + userDetails.getUsername());
      System.out.println("Principal Name: " + authentication.getName());
      System.out.println("Authorities: " + authentication.getAuthorities());
      // ... Другая информация о пользователе
    }
  }

  // Temp
  @PostMapping("/new-user")
  public String addUser(@RequestBody User user){
      userService.addUser(user);
      return "User is saved";
  }

}
