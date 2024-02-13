package com.suleimanov.vehiclecontrol.vehicle.controllers;

import com.suleimanov.vehiclecontrol.vehicle.models.VehicleStatus;
import com.suleimanov.vehiclecontrol.vehicle.services.VehicleStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/vehicles_statuses")
public class VehicleStatusController {

  @Autowired
  private VehicleStatusService vehicleStatusService;

  @GetMapping()
  public String getVehicleStatuses(Model model) {
    List<VehicleStatus> vehicleStatuses = vehicleStatusService.getVehiclesStatus();

    model.addAttribute("vehicleStatuses", vehicleStatuses);
    return "vehicle_status";
  }

  @GetMapping("/findById")
  @ResponseBody
  public Optional<VehicleStatus> findById(Integer id) {
    return vehicleStatusService.findById(id);
  }

  @PostMapping("/addNew")
  public String addNew(VehicleStatus vehicleStatus) {
    vehicleStatusService.save(vehicleStatus);
    return "redirect:/vehicles_statuses";
  }

  @RequestMapping(value = "/update", method = {RequestMethod.PUT, RequestMethod.GET})
  public String update(VehicleStatus vehicleStatus) {
    vehicleStatusService.save(vehicleStatus);
    return "redirect:/vehicles_statuses";
  }

  @RequestMapping(value = "/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
  public String delete(Integer id) {
    vehicleStatusService.delete(id);
    return "redirect:/vehicles_statuses";
  }
}
