package com.suleimanov.vehiclecontrol.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegionController {

  @GetMapping("/regions")
  public String getRegion() {
    return "region";
  }
}
