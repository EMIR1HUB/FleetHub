package com.suleimanov.vehiclecontrol.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VehicleMaintenanceController {

  @GetMapping("/vehicles_maintenances")
  public String getVehicleMaintenance() {
    return "vehicle_maintenance";
  }
}
