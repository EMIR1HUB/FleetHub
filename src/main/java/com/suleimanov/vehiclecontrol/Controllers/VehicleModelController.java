package com.suleimanov.vehiclecontrol.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VehicleModelController {

  @GetMapping("/vehicles_models")
  public String getVehicleModel() {
    return "vehicle_model";
  }
}
