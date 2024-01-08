package com.suleimanov.vehiclecontrol.Controllers;

import com.suleimanov.vehiclecontrol.Models.VehicleMake;
import com.suleimanov.vehiclecontrol.Services.VehicleMakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/vehicles_makes")
public class VehicleMakeController {

  @Autowired
  private VehicleMakeService vehicleMakeService;

  @GetMapping()
  public String getVehicleMake(Model model) {
    List<VehicleMake> vehicleMakeList = vehicleMakeService.getVehiclesMakes();

    model.addAttribute("vehicleMakes", vehicleMakeList);
    return "vehicle_make";
  }

  @GetMapping("/findById")
  @ResponseBody
  public Optional<VehicleMake> findById(Integer id) {
    return vehicleMakeService.findById(id);
  }

  @PostMapping("/addNew")
  public String addNew(VehicleMake vehicleMake) {
    vehicleMakeService.save(vehicleMake);
    return "redirect:/vehicles_makes";
  }

  @RequestMapping(value = "/update", method = {RequestMethod.PUT, RequestMethod.GET})
  public String update(VehicleMake vehicleMake) {
    vehicleMakeService.save(vehicleMake);
    return "redirect:/vehicles_makes";
  }

  @RequestMapping(value = "/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
  public String delete(Integer id) {
    vehicleMakeService.delete(id);
    return "redirect:/vehicles_makes";
  }
}
