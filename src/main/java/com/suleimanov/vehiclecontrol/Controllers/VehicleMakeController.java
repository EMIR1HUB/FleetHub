package com.suleimanov.vehiclecontrol.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VehicleMakeController {

  @GetMapping("/vehicles_makes")
  public String getVehicleMake() {
    return "vehicle_make";
  }
}
