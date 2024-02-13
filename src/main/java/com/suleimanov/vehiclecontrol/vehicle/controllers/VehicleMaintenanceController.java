package com.suleimanov.vehiclecontrol.vehicle.controllers;

import com.suleimanov.vehiclecontrol.vehicle.models.VehicleMaintenance;
import com.suleimanov.vehiclecontrol.parameters.services.SupplierService;
import com.suleimanov.vehiclecontrol.vehicle.services.VehicleMaintenanceService;
import com.suleimanov.vehiclecontrol.vehicle.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/vehicles_maintenances")
public class VehicleMaintenanceController {

  @Autowired private VehicleMaintenanceService vehicleMaintenanceService;
  @Autowired private VehicleService vehicleService;
  @Autowired private SupplierService supplierService;

  @GetMapping()
  public String getVehicleMaintenance(Model model) {
    model.addAttribute("vehicleMaintenances", vehicleMaintenanceService.getVehicleMaintenances());
    model.addAttribute("vehicles", vehicleService.getVehicles());
    model.addAttribute("suppliers", supplierService.getSuppliers());
    return "vehicle_maintenance";
  }

  @GetMapping("/findById")
  @ResponseBody
  public Optional<VehicleMaintenance> findById(Integer id) {
    return vehicleMaintenanceService.findById(id);
  }

  @PostMapping("/addNew")
  public String addNew(VehicleMaintenance vehicleMaintenance) {
    vehicleMaintenanceService.save(vehicleMaintenance);
    return "redirect:/vehicles_maintenances";
  }

  @RequestMapping(value = "/update", method = {RequestMethod.PUT, RequestMethod.GET})
  public String update(VehicleMaintenance vehicleMaintenance) {
    vehicleMaintenanceService.save(vehicleMaintenance);
    return "redirect:/vehicles_maintenances";
  }

  @RequestMapping(value = "/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
  public String delete(Integer id) {
    vehicleMaintenanceService.delete(id);
    return "redirect:/vehicles_maintenances";
  }
}
