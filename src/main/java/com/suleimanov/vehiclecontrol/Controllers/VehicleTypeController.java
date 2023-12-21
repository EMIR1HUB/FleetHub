package com.suleimanov.vehiclecontrol.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VehicleTypeController {

  @GetMapping("/vehicles_types")
  public String getVehicleType() {
    return "vehicle_type";
  }
}
