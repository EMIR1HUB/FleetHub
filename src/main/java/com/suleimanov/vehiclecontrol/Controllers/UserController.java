package com.suleimanov.vehiclecontrol.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

  @GetMapping("/users")
  public String getUser() {
    return "user";
  }
}
