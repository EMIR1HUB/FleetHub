package com.suleimanov.vehiclecontrol.vehicle.controllers;

import com.suleimanov.vehiclecontrol.hr.services.EmployeeService;
import com.suleimanov.vehiclecontrol.vehicle.models.Vehicle;
import com.suleimanov.vehiclecontrol.vehicle.models.VehicleMake;
import com.suleimanov.vehiclecontrol.vehicle.models.VehicleModel;
import com.suleimanov.vehiclecontrol.parameters.services.LocationService;
import com.suleimanov.vehiclecontrol.vehicle.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/vehicles/vehicle")
public class VehicleController {

  // Vehicles
  // Location
  // Employee
  // Vehicle Make
  // Vehicle Status
  // Vehicle Type
  // Vehicle Model

  @Autowired private VehicleService vehicleService;
  @Autowired private LocationService locationService;
  @Autowired private EmployeeService employeeService;
  @Autowired private VehicleMakeService vehicleMakeService;
  @Autowired private VehicleStatusService vehicleStatusService;
  @Autowired private VehicleTypeService vehicleTypeService;
  @Autowired private VehicleModelService vehicleModelService;
  @Value("${upload.path}")
  private String uploadPhotoPath;

  private Integer idByUploadPhoto;

  @GetMapping()
  public String getVehicle(Model model) {
    model.addAttribute("vehicles", vehicleService.getVehicles());
    model.addAttribute("locations", locationService.getLocations());
    model.addAttribute("employees", employeeService.getEmployees());
    model.addAttribute("vehicleMakes", vehicleMakeService.getVehiclesMakes());
    model.addAttribute("vehicleStatuses", vehicleStatusService.getVehiclesStatus());
    model.addAttribute("vehicleTypes", vehicleTypeService.getVehiclesTypes());
    model.addAttribute("vehicleModels", vehicleModelService.getVehiclesModels());
    return "vehicle";
  }

  @GetMapping("/findById")
  @ResponseBody
  public Optional<Vehicle> findById(Integer id) {
    return vehicleService.findById(id);
  }

  @PostMapping("/addNew")
  public String addNew(Vehicle vehicle) {
    vehicle.setName(vehicleMakeService.getVehicleMakeDescriptionById(vehicle.getVehiclemakeid()) + "-" +
            vehicleModelService.getVehicleModelDescriptionById(vehicle.getVehiclemodelid()));
    vehicle.setPhoto("default.jpg");
    vehicleService.save(vehicle);
    return "redirect:/vehicles";
  }

  @RequestMapping(value = "/update", method = {RequestMethod.PUT, RequestMethod.GET})
  public String update(Vehicle vehicle) {
    vehicleService.save(vehicle);
    return "redirect:/vehicles";
  }

  @RequestMapping(value = "/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
  public String delete(Integer id) {
    vehicleService.delete(id);
    return "redirect:/vehicles";
  }

  @GetMapping("/parameters/make/{id}")
  @ResponseBody
  public List<VehicleModel> getRegionsByCountry(@PathVariable Integer id){
    VehicleMake vehicleMake = vehicleMakeService.findById(id).orElse(null);
    if (vehicleMake != null) {
      return vehicleModelService.getVehicleModelByMake(vehicleMake);
    } else {
      return Collections.emptyList();
    }
  }

  @GetMapping("/parameters/uploadPhoto/{id}")
  public void getIdByCountry(@PathVariable Integer id){
    idByUploadPhoto = id;
  }

  @PostMapping("/uploadPhoto")
  public String uploadPhoto(@RequestParam("file") MultipartFile file) throws IOException {
    String uuid = UUID.randomUUID() + ".jpg";
    file.transferTo(new File(uploadPhotoPath + "vehicles/" + uuid));

    String oldPhoto = vehicleService.findById(idByUploadPhoto).map(Vehicle::getPhoto).orElse("");
    vehicleService.findById(idByUploadPhoto).ifPresent(vehicle -> {
      vehicle.setPhoto(uuid);
      vehicleService.save(vehicle);
    });

    File fileToDelete = new File(uploadPhotoPath + "vehicles/" + oldPhoto);
    if (fileToDelete.delete()){
      System.out.println("Файл успешно удален.");
    } else {
      System.err.println("Не удалось удалить файл.");
    }
    return "redirect:/vehicles";
  }
}
