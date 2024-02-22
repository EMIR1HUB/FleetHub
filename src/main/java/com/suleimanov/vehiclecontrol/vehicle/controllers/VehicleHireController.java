package com.suleimanov.vehiclecontrol.vehicle.controllers;

import com.suleimanov.vehiclecontrol.parameters.services.ClientService;
import com.suleimanov.vehiclecontrol.parameters.services.LocationService;
import com.suleimanov.vehiclecontrol.vehicle.models.VehicleHire;
import com.suleimanov.vehiclecontrol.vehicle.services.VehicleHireService;
import com.suleimanov.vehiclecontrol.vehicle.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/vehicles/vehicle-hires")
public class VehicleHireController {

  @Autowired private VehicleHireService vehicleHireService;
  @Autowired private VehicleService vehicleService;
  @Autowired private LocationService locationService;
  @Autowired private ClientService clientService;

  private Model addModelAttributes(Model model){
    model.addAttribute("clients", clientService.getClients());
    model.addAttribute("vehicles", vehicleService.getVehicles());
    model.addAttribute("locations", locationService.getLocations());
    return model;
  }

  @GetMapping()
  public String getVehicleHire(Model model) {
    return getAllWithSort(model, null);
  }

  @GetMapping("/page")
  public String getAllWithSort(Model model, String keyword) {
    List<VehicleHire> hires = (keyword == null)
            ? vehicleHireService.getVehicleHires()
            : vehicleHireService.getByKeyword(keyword);

    model.addAttribute("hires", hires);
    addModelAttributes(model);
    return "/vehicles/hires";
  }

  @GetMapping("/addNew")
  public String getAddNew(Model model){
    addModelAttributes(model);
    return "/vehicles/hireAdd";
  }

  @GetMapping("/{id}")
  @ResponseBody
  public VehicleHire getById(@PathVariable Integer id) {
    return vehicleHireService.getById(id);
  }

  @PostMapping()
  public String addNew(VehicleHire vehicleHire) {
    vehicleHireService.save(vehicleHire);
    return "redirect:/vehicles/vehicle-hires";
  }

  @GetMapping("/{op}/{id}")
  public String getEdit(Model model, @PathVariable String op, @PathVariable Integer id) {
    model.addAttribute("hire", vehicleHireService.getById(id));
    addModelAttributes(model);
    return "/vehicles/hire" + op;
  }

  @RequestMapping(value = "/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
  public String delete(@PathVariable Integer id) {
    vehicleHireService.delete(id);
    return "redirect:/vehicles/vehicles-hires";
  }
}
