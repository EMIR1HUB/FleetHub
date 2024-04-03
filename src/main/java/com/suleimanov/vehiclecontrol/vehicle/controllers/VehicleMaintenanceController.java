package com.suleimanov.vehiclecontrol.vehicle.controllers;

import com.suleimanov.vehiclecontrol.parameters.services.SupplierService;
import com.suleimanov.vehiclecontrol.vehicle.models.VehicleMaintenance;
import com.suleimanov.vehiclecontrol.vehicle.services.VehicleMaintenanceService;
import com.suleimanov.vehiclecontrol.vehicle.services.VehicleService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/vehicles/vehicle-maintenances")
public class VehicleMaintenanceController {

  @Autowired private VehicleMaintenanceService vehicleMaintenanceService;
  @Autowired private VehicleService vehicleService;
  @Autowired private SupplierService supplierService;

  @GetMapping()
  public String getAll(Model model, String keyword) {
    List<VehicleMaintenance> vehicleMaintenances = keyword == null
            ? vehicleMaintenanceService.getVehicleMaintenances()
            : vehicleMaintenanceService.getByKeyword(keyword);

    model.addAttribute("maintenances", vehicleMaintenances);
    return "/vehicles/maintenances";
  }

  @GetMapping("/page/{field}")
  public String getAllWithSort(@PathVariable("field") String field,
                               @PathParam("sortDir") String sortDir, Model model){
    model.addAttribute("maintenances", vehicleMaintenanceService.getVehicleWithSort(field, sortDir));
    model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
    return "/vehicles/maintenances";
  }

  @GetMapping("/{id}")
  @ResponseBody
  public VehicleMaintenance getVehicleMaintenance(@PathVariable Long id) {
    return vehicleMaintenanceService.getById(id).orElse(null);
  }

  @GetMapping("/addNew")
  public String getAddNew(Model model){
    model.addAttribute("vehicles", vehicleService.getVehiclesByStatusNoRemote());
    model.addAttribute("suppliers", supplierService.getSuppliers());
    return "/vehicles/maintenanceAdd";
  }

  @PostMapping()
  public String addNew(VehicleMaintenance vehicleMaintenance) {
    vehicleMaintenanceService.save(vehicleMaintenance);
    return "redirect:/vehicles/vehicle-maintenances";
  }

  @GetMapping("/{op}/{id}")
  public String getEditAndDetails(@PathVariable String op,
                                  @PathVariable Long id, Model model){

    model.addAttribute("vehicles", vehicleService.getVehicles());
    model.addAttribute("suppliers", supplierService.getSuppliers());
    model.addAttribute("maintenance", vehicleMaintenanceService.getById(id).orElse(null));
    return "/vehicles/maintenance" + op;
  }

  @RequestMapping(value = "/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
  public String delete(@PathVariable Long id) {
    vehicleMaintenanceService.delete(id);
    return "redirect:/vehicles/vehicle-maintenances";
  }
}








