package com.suleimanov.vehiclecontrol.vehicle.controllers;

import com.suleimanov.vehiclecontrol.vehicle.models.VehicleMake;
import com.suleimanov.vehiclecontrol.vehicle.models.VehicleModel;
import com.suleimanov.vehiclecontrol.vehicle.services.VehicleMakeService;
import com.suleimanov.vehiclecontrol.vehicle.services.VehicleModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/vehicles_models")
public class VehicleModelController {

  @Autowired
  private VehicleModelService vehicleModelService;

  @Autowired
  private VehicleMakeService vehicleMakeService;

  @GetMapping()
  public String getVehicleModel(Model model) {
    List<VehicleModel> vehicleModelsList = vehicleModelService.getVehiclesModels();
    List<VehicleMake> vehicleMakeList = vehicleMakeService.getVehiclesMakes();

    model.addAttribute("vehicleModels", vehicleModelsList);
    model.addAttribute("vehicleMakes", vehicleMakeList);
    return "vehicle_model";
  }

  @GetMapping("/findById")
  @ResponseBody
  public Optional<VehicleModel> findById(Integer id) {
    return vehicleModelService.findById(id);
  }

  @PostMapping("/addNew")
  public String addNew(VehicleModel vehicleModel) {
    vehicleModelService.save(vehicleModel);
    return "redirect:/vehicles_models";
  }

  @RequestMapping(value = "/update", method = {RequestMethod.PUT, RequestMethod.GET})
  public String update(VehicleModel vehicleModel) {
    vehicleModelService.save(vehicleModel);
    return "redirect:/vehicles_models";
  }

  @RequestMapping(value = "/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
  public String delete(Integer id) {
    vehicleModelService.delete(id);
    return "redirect:/vehicles_models";
  }
}
