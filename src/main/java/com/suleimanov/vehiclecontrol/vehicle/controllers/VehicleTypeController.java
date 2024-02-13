package com.suleimanov.vehiclecontrol.vehicle.controllers;

import com.suleimanov.vehiclecontrol.vehicle.models.VehicleType;
import com.suleimanov.vehiclecontrol.vehicle.services.VehicleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/vehicles_types")
public class VehicleTypeController {

  @Autowired
  private VehicleTypeService vehicleTypeService;

  @GetMapping()
  public String getVehicleType(Model model) {
    List<VehicleType> vehicleTypeList = vehicleTypeService.getVehiclesTypes();

    model.addAttribute("vehicleTypes", vehicleTypeList);
    return "vehicle_type";
  }

  @GetMapping("/findById")
  @ResponseBody
  public Optional<VehicleType> findById(Integer id) {
    return vehicleTypeService.findById(id);
  }

  @PostMapping("/addNew")
  public String addNew(VehicleType vehicleType) {
    vehicleTypeService.save(vehicleType);
    return "redirect:/vehicles_types";
  }

  @RequestMapping(value = "/update", method = {RequestMethod.PUT, RequestMethod.GET})
  public String update(VehicleType vehicleType) {
    vehicleTypeService.save(vehicleType);
    return "redirect:/vehicles_types";
  }

  @RequestMapping(value = "/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
  public String delete(Integer id) {
    vehicleTypeService.delete(id);
    return "redirect:/vehicles_types";
  }
}
