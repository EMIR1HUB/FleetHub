package com.suleimanov.vehiclecontrol;

import com.suleimanov.vehiclecontrol.Models.User;
import com.suleimanov.vehiclecontrol.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
  @PostMapping("/new-user")
  public String addUser(@RequestBody User user){
      userService.addUser(user);
      return "User is saved";
  }

}
