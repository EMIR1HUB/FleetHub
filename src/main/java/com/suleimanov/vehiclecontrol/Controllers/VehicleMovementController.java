package com.suleimanov.vehiclecontrol.Controllers;

import com.suleimanov.vehiclecontrol.Models.VehicleMovement;
import com.suleimanov.vehiclecontrol.Services.LocationService;
import com.suleimanov.vehiclecontrol.Services.VehicleMovementService;
import com.suleimanov.vehiclecontrol.Services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/vehicles_movements")
public class VehicleMovementController {

  @Autowired private VehicleMovementService vehicleMovementService;
  @Autowired private VehicleService vehicleService;
  @Autowired private LocationService locationService;

  @GetMapping()
  public String getVehicleMovement(Model model) {
    model.addAttribute("vehicleMovements", vehicleMovementService.getVehicleMovements());
    model.addAttribute("vehicles", vehicleService.getVehicles());
    model.addAttribute("locations", locationService.getLocations());
    return "vehicle_movement";
  }

  @GetMapping("/findById")
  @ResponseBody
  public Optional<VehicleMovement> findById(Integer id) {
    return vehicleMovementService.findById(id);
  }

  @PostMapping("/addNew")
  public String addNew(VehicleMovement vehicleMovement) {
    vehicleMovementService.save(vehicleMovement);
    return "redirect:/vehicles_maintenances";
  }

  @RequestMapping(value = "/update", method = {RequestMethod.PUT, RequestMethod.GET})
  public String update(VehicleMovement vehicleMovement) {
    vehicleMovementService.save(vehicleMovement);
    return "redirect:/vehicles_maintenances";
  }

  @RequestMapping(value = "/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
  public String delete(Integer id) {
    vehicleMovementService.delete(id);
    return "redirect:/vehicles_maintenances";
  }
}
