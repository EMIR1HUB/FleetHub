package com.suleimanov.vehiclecontrol.Controllers;

import com.suleimanov.vehiclecontrol.Models.VehicleHire;
import com.suleimanov.vehiclecontrol.Services.ClientService;
import com.suleimanov.vehiclecontrol.Services.LocationService;
import com.suleimanov.vehiclecontrol.Services.VehicleHireService;
import com.suleimanov.vehiclecontrol.Services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/vehicles_hires")
public class VehicleHireController {

  @Autowired private VehicleHireService vehicleHireService;
  @Autowired private VehicleService vehicleService;
  @Autowired private ClientService clientService;
  @Autowired private LocationService locationService;

  @GetMapping()
  public String getVehicleHire(Model model) {
    model.addAttribute("vehicleHires", vehicleHireService.getVehicleHires());
    model.addAttribute("vehicles", vehicleService.getVehicles());
    model.addAttribute("clients", clientService.getClients());
    model.addAttribute("locations", locationService.getLocations());
    return "vehicle_hire";
  }

  @GetMapping("/findById")
  @ResponseBody
  public Optional<VehicleHire> findById(Integer id) {
    return vehicleHireService.findById(id);
  }

  @PostMapping("/addNew")
  public String addNew(VehicleHire vehicleHire) {
    vehicleHireService.save(vehicleHire);
    return "redirect:/vehicles_hires";
  }

  @RequestMapping(value = "/update", method = {RequestMethod.PUT, RequestMethod.GET})
  public String update(VehicleHire vehicleHire) {
    vehicleHireService.save(vehicleHire);
    return "redirect:/vehicles_hires";
  }

  @RequestMapping(value = "/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
  public String delete(Integer id) {
    vehicleHireService.delete(id);
    return "redirect:/vehicles_hires";
  }
}
