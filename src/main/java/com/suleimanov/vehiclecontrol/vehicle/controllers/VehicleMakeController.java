package com.suleimanov.vehiclecontrol.vehicle.controllers;

import com.suleimanov.vehiclecontrol.vehicle.models.VehicleMake;
import com.suleimanov.vehiclecontrol.vehicle.services.VehicleMakeService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/vehicles/vehicle-makes")
public class VehicleMakeController {

  @Autowired
  private VehicleMakeService vehicleMakeService;

  @GetMapping()
  public String getAll(Model model, String keyword) {
    List<VehicleMake> vehicleMakes = (keyword == null)
            ? vehicleMakeService.getVehiclesMakes()
            : vehicleMakeService.getByKeyword(keyword);

    model.addAttribute("makes", vehicleMakes);
    return "/vehicles/makes";
  }

  @GetMapping("/page/{field}")
  public String getAllWithSort(@PathVariable("field") String field,
                               @PathParam("sortDir") String sortDir, Model model){
    model.addAttribute("makes", vehicleMakeService.getVehicleMakesWithSort(field, sortDir));
    model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
    return "/vehicles/makes";
  }

  @GetMapping("/{id}")
  @ResponseBody
  public VehicleMake getVehicleMake(@PathVariable Integer id) {
    return vehicleMakeService.getById(id);
  }

  @PostMapping()
  public String add(VehicleMake vehicleMake) {
    vehicleMakeService.save(vehicleMake);
    return "redirect:/vehicles/vehicle-makes";
  }

  @GetMapping("/{op}/{id}")
  public String edit(@PathVariable String op, @PathVariable Integer id, Model model) {
    model.addAttribute("vehicleMake", vehicleMakeService.getById(id));
    return "/vehicles/make" + op;
  }

  @RequestMapping(value = "/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
  public String delete(@PathVariable Integer id) {
    vehicleMakeService.delete(id);
    return "redirect:/vehicles/vehicle-makes";
  }
}
