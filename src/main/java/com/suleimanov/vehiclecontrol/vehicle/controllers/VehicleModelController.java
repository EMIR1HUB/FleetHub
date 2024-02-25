package com.suleimanov.vehiclecontrol.vehicle.controllers;

import com.suleimanov.vehiclecontrol.vehicle.models.VehicleModel;
import com.suleimanov.vehiclecontrol.vehicle.services.VehicleMakeService;
import com.suleimanov.vehiclecontrol.vehicle.services.VehicleModelService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/vehicles/vehicle-models")
public class VehicleModelController {

  @Autowired
  private VehicleModelService vehicleModelService;

  @Autowired
  private VehicleMakeService vehicleMakeService;

  @GetMapping()
  public String getAll(Model model, String keyword) {
    List<VehicleModel> vehicleModelsList = (keyword == null)
            ? vehicleModelService.getVehiclesModels()
            : vehicleModelService.getByKeyword(keyword);

    model.addAttribute("models", vehicleModelsList);
    model.addAttribute("makes", vehicleMakeService.getVehiclesMakes());
    return "/vehicles/models";
  }

  @GetMapping("/page/{field}")
  public String getAllWithSort(@PathVariable("field") String field, @PathParam("sortDir") String sortDir,
                               Model model) {
    model.addAttribute("models", vehicleModelService.getAllWithSort(field, sortDir));
    model.addAttribute("makes", vehicleMakeService.getVehiclesMakes());
    model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
    return "/vehicles/models";
  }

  @GetMapping("/{id}")
  @ResponseBody
  public VehicleModel getVehicleModel(@PathVariable Integer id) {
    return vehicleModelService.getById(id);
  }

  @PostMapping()
  public String addNew(VehicleModel vehicleModel) {
    vehicleModelService.save(vehicleModel);
    return "redirect:/vehicles/vehicle-models";
  }

  @GetMapping("/{op}/{id}")
  public String getEdit(@PathVariable String op, @PathVariable Integer id, Model model) {
    model.addAttribute("models", vehicleModelService.getById(id));
    return "/vehicles/model" + op;
  }


  @RequestMapping(value = "/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
  public String delete(@PathVariable Integer id) {
    vehicleModelService.delete(id);
    return "redirect:/vehicles/vehicle-models";
  }
}
