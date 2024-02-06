package com.suleimanov.vehiclecontrol;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {


  @GetMapping("/index")
  public String getHome() {
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

  @GetMapping("/register")
  public String register(){
    return "register";
  }

  @GetMapping("/accessDenied")
  public String accessDenied(){
    return "accessDenied";
  }

  // Temp
//  private void logAuthenticationDetails() {
//    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//    if (authentication != null && authentication.isAuthenticated()) {
//      // Вывод информации в лог
//      UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//      System.out.println("Authentication class: " + authentication.getClass().getName());
//      System.out.println("Principal " + userDetails);
//      System.out.println("UserName " + userDetails.getUsername());
//      System.out.println("Principal Name: " + authentication.getName());
//      System.out.println("Authorities: " + authentication.getAuthorities());
//      // ... Другая информация о пользователе
//    }
//  }
}
