package com.suleimanov.vehiclecontrol.vehicle.controllers;

import com.suleimanov.vehiclecontrol.vehicle.models.VehicleType;
import com.suleimanov.vehiclecontrol.vehicle.services.VehicleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vehicles/vehicle-types")
public class VehicleTypeController {

  @Autowired
  private VehicleTypeService vehicleTypeService;

  @GetMapping()
  public String getVehicleTypes(Model model) {
    model.addAttribute("vehicleTypes", vehicleTypeService.getVehiclesTypes());
    model.addAttribute("count", vehicleTypeService.getCount());
    return "/vehicles/vehicle-types";
  }

  @GetMapping("/{id}")
  @ResponseBody
  public VehicleType getVehicleType(@PathVariable Integer id) {
    return vehicleTypeService.getById(id);
  }

  @PostMapping()
  public String add(VehicleType vehicleType) {
    vehicleTypeService.save(vehicleType);
    return "redirect:/vehicles/vehicle-types";
  }

  @GetMapping("/{op}/{id}")
  public String edit(@PathVariable String op, @PathVariable Integer id, Model model) {
    model.addAttribute("vehicleType", vehicleTypeService.getById(id));
    return "/vehicles/vehicle-type-" + op;
  }

  @RequestMapping(value = "/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
  public String delete(@PathVariable Integer id) {
    vehicleTypeService.delete(id);
    return "redirect:/vehicles/vehicle-types";
  }
}
