package com.suleimanov.vehiclecontrol;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {


  @GetMapping("/index")
  public String getHome() {
    logAuthenticationDetails();
    return "index";
  }
  @GetMapping("/hr")
  public String hr(){
    return "hr/index";
  }
  @GetMapping("/vehicles")
  public String vehicle(){
    return "vehicles/index";
  }
  @GetMapping("/payroll")
  public String payroll(){
    return "payroll/index";
  }
  @GetMapping("/parameters")
  public String parameters(){
    return "parameters/index";
  }
  @GetMapping("/helpdesk")
  public String helpdesk(){
    return "helpdesk/index";
  }
  @GetMapping("/accounts")
  public String accounts(){
    return "accounts/index";
  }

  @GetMapping("/security")
  public String security(){
    return "security/index";
  }


  @GetMapping("/login")
  public String login(){
    return "login";
  }

  @GetMapping("/logout")
  public String logout(){
    return "login";
  }

  @GetMapping("/accessDenied")
  public String accessDenied(){
    return "accessDenied";
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
}
