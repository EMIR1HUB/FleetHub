package com.suleimanov.vehiclecontrol.Controllers;

import com.suleimanov.vehiclecontrol.Models.Vehicle;
import com.suleimanov.vehiclecontrol.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/vehicles")
public class VehicleController {

  // Vehicles
  // Location
  // Employee
  // Vehicle Make
  // Vehicle Status
  // Vehicle Type
  // Vehicle Model

  @Autowired private VehicleService vehicleService;
  @Autowired private LocationService locationService;
  @Autowired private EmployeeService employeeService;
  @Autowired private VehicleMakeService vehicleMakeService;
  @Autowired private VehicleStatusService vehicleStatusService;
  @Autowired private VehicleTypeService vehicleTypeService;
  @Autowired private VehicleModelService vehicleModelService;

  @GetMapping()
  public String getVehicle(Model model) {
    model.addAttribute("vehicles", vehicleService.getVehicles());
    model.addAttribute("locations", locationService.getLocations());
    model.addAttribute("employees", employeeService.getEmployees());
    model.addAttribute("vehicleMakes", vehicleMakeService.getVehiclesMakes());
    model.addAttribute("vehicleStatuses", vehicleStatusService.getVehiclesStatus());
    model.addAttribute("vehicleTypes", vehicleTypeService.getVehiclesTypes());
    model.addAttribute("vehicleModels", vehicleModelService.getVehiclesModels());
    return "vehicle";
  }

  @GetMapping("/findById")
  @ResponseBody
  public Optional<Vehicle> findById(Integer id) {
    return vehicleService.findById(id);
  }

  @PostMapping("/addNew")
  public String addNew(Vehicle vehicle) {
    vehicle.setName(vehicle.getVehicleMake() + " " + vehicle.getVehicleModel());
    vehicleService.save(vehicle);
    return "redirect:/vehicles";
  }

  @RequestMapping(value = "/update", method = {RequestMethod.PUT, RequestMethod.GET})
  public String update(Vehicle vehicle) {
    vehicleService.save(vehicle);
    return "redirect:/vehicles";
  }

  @RequestMapping(value = "/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
  public String delete(Integer id) {
    vehicleService.delete(id);
    return "redirect:/vehicles";
  }
}
