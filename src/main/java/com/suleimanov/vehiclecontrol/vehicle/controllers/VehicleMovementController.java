package com.suleimanov.vehiclecontrol.vehicle.controllers;

import com.suleimanov.vehiclecontrol.parameters.services.LocationService;
import com.suleimanov.vehiclecontrol.vehicle.models.VehicleMovement;
import com.suleimanov.vehiclecontrol.vehicle.services.VehicleMovementService;
import com.suleimanov.vehiclecontrol.vehicle.services.VehicleService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/vehicles/vehicle-movements")
public class VehicleMovementController {

  @Autowired private VehicleMovementService vehicleMovementService;
  @Autowired private VehicleService vehicleService;
  @Autowired private LocationService locationService;

  @GetMapping()
  public String getAll(Model model, String keyword) {
    List<VehicleMovement> vehicleMovements = keyword == null
            ? vehicleMovementService.getVehicleMovements()
            : vehicleMovementService.getByKeyword(keyword);

    model.addAttribute("movements", vehicleMovements);
    return "/vehicles/movements";
  }

  @GetMapping("/page/{field}")
  public String getAllWithSort(@PathVariable("field") String field,
                               @PathParam("sortDir") String sortDir, Model model){
    model.addAttribute("movements", vehicleMovementService.getVehicleWithSort(field, sortDir));
    model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
    return "/vehicles/movements";
  }

  @GetMapping("/{id}")
  @ResponseBody
  public VehicleMovement getVehicleMovement(@PathVariable Long id) {
    return vehicleMovementService.getById(id).orElse(null);
  }

  @GetMapping("/addNew")
  public String getAddNew(Model model){
    model.addAttribute("vehicles", vehicleService.getVehiclesByNoMovement());
    model.addAttribute("locations", locationService.getLocations());
    return "/vehicles/movementAdd";
  }

  @PostMapping()
  public String addNew(VehicleMovement vehicleMovement) {
    vehicleMovementService.save(vehicleMovement);
    return "redirect:/vehicles/vehicle-movements";
  }

  @GetMapping("/{op}/{id}")
  public String getEditAndDetails(@PathVariable String op,
                                  @PathVariable Long id, Model model){
    model.addAttribute("movement", vehicleMovementService.getById(id).orElse(null));
    model.addAttribute("vehicles", vehicleService.getVehicles());
    model.addAttribute("locations", locationService.getLocations());
    return "/vehicles/movement" + op;
  }

  @RequestMapping(value = "/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
  public String delete(@PathVariable Long id) {
    vehicleMovementService.delete(id);
    return "redirect:/vehicles/vehicle-movements";
  }
}
