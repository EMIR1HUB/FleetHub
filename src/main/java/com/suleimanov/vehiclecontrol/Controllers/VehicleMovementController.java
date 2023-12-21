package com.suleimanov.vehiclecontrol.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VehicleMovementController {

  @GetMapping("/vehicles_movements")
  public String getVehicleMovement() {
    return "vehicle_movement";
  }
}
