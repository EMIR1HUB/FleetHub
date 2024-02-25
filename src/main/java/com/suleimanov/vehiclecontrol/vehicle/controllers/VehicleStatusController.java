package com.suleimanov.vehiclecontrol.vehicle.controllers;

import com.suleimanov.vehiclecontrol.vehicle.models.VehicleStatus;
import com.suleimanov.vehiclecontrol.vehicle.services.VehicleStatusService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/vehicles/vehicle-statuses")
public class VehicleStatusController {

  @Autowired
  private VehicleStatusService vehicleStatusService;

  @GetMapping()
  public String getAll(Model model, String keyword) {
    List<VehicleStatus> vehicleStatuses = (keyword == null)
            ? vehicleStatusService.getVehiclesStatus()
            : vehicleStatusService.getByKeyword(keyword);
    model.addAttribute("statuses", vehicleStatuses);
    return "/vehicles/statuses";
  }

  @GetMapping("/page/{field}")
  public String getAllWithSort(@PathVariable String field,
                               @PathParam("sortDir") String sortDir, Model model) {
    model.addAttribute("statuses", vehicleStatusService.getVehiclesStatusWithSort(field, sortDir));
    model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
    return "/vehicles/statuses";
  }

  @GetMapping("/{id}")
  @ResponseBody
  public VehicleStatus getVehicleStatus(@PathVariable Integer id) {
    return vehicleStatusService.getById(id);
  }

  @PostMapping()
  public String add(VehicleStatus vehicleStatus) {
    vehicleStatusService.save(vehicleStatus);
    return "redirect:/vehicles/vehicle-statuses";
  }

  @GetMapping("/{op}/{id}")
  public String getDetails(@PathVariable String op, @PathVariable Integer id, Model model){
    model.addAttribute("status", vehicleStatusService.getById(id));
    return "/vehicles/status"+op;
  }


  @RequestMapping(value = "/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
  public String delete(@PathVariable Integer id) {
    vehicleStatusService.delete(id);
    return "redirect:/vehicles/vehicle-statuses";
  }
}
