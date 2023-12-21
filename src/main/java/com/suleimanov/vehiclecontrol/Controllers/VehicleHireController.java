package com.suleimanov.vehiclecontrol.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VehicleHireController {

  @GetMapping("/vehicles_hires")
  public String getVehicleHire() {
    return "vehicle_hire";
  }
}
