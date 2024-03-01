package com.suleimanov.vehiclecontrol.vehicle.controllers;

import com.suleimanov.vehiclecontrol.hr.services.EmployeeService;
import com.suleimanov.vehiclecontrol.parameters.services.LocationService;
import com.suleimanov.vehiclecontrol.vehicle.models.Vehicle;
import com.suleimanov.vehiclecontrol.vehicle.services.*;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
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

  @Autowired
  private VehicleService vehicleService;
  @Autowired
  private LocationService locationService;
  @Autowired
  private EmployeeService employeeService;
  @Autowired
  private VehicleMakeService vehicleMakeService;
  @Autowired
  private VehicleStatusService vehicleStatusService;
  @Autowired
  private VehicleTypeService vehicleTypeService;
  @Autowired
  private VehicleModelService vehicleModelService;
  @Value("${upload.path}")
  private String uploadPhotoPath;

  private Integer idByUploadPhoto;

  private void addModelAttributes(Model model) {
    model.addAttribute("types", vehicleTypeService.getVehiclesTypes());
    model.addAttribute("makes", vehicleMakeService.getVehiclesMakes());
    model.addAttribute("locations", locationService.getLocations());
    model.addAttribute("employees", employeeService.getEmployees());
    model.addAttribute("statuses", vehicleStatusService.getVehiclesStatus());
  }

  @GetMapping()
  public String getAll(Model model, String keyword) {
    List<Vehicle> vehicles = (keyword == null)
            ? vehicleService.getVehicles()
            : vehicleService.getByKeyword(keyword);

    model.addAttribute("vehicles", vehicles);
    return "/vehicles/vehicles";
  }

  @GetMapping("/page/{field}")
  public String getAllWithSort(@PathVariable("field") String field,
                               @PathParam("sortDir") String sortDir, Model model) {
    model.addAttribute("vehicles", vehicleService.getVehicleWithSort(field, sortDir));
    model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
    return "/vehicles/vehicles";
  }

  @GetMapping("/{id}")
  @ResponseBody
  public Vehicle getVehicle(@PathVariable Integer id) {
    return vehicleService.getById(id).orElse(null);
  }

  @GetMapping("/addNew")
  public String getAddNew(Model model) {
    addModelAttributes(model);
    return "/vehicles/vehicleAdd";
  }

  @PostMapping()
  public String add(Vehicle vehicle, @RequestParam(value = "file", required = false) MultipartFile file) {
    // Установка названия
    vehicle.setName(vehicleMakeService.getVehicleMakeDescriptionById(vehicle.getVehiclemakeid()) + "-" +
            vehicleModelService.getVehicleModelDescriptionById(vehicle.getVehiclemodelid()));

    if (file != null && !file.isEmpty()) {  // Download Image
      try {
        byte[] bytesFile = file.getBytes();
        String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
        String fileName = UUID.randomUUID() + fileType;

        try (BufferedOutputStream stream = new BufferedOutputStream(
                new FileOutputStream(new File(uploadPhotoPath + fileName)))) {
          stream.write(bytesFile);
        }
        vehicle.setPhoto(fileName);
      } catch (Exception e) {
        return "Вам не удалось загрузить " + e.getMessage();
      }
    } else {
      if (vehicle.getPhoto().isEmpty()) {
        vehicle.setPhoto("default.jpg");
      }
    }
    vehicleService.save(vehicle);
    return "redirect:/vehicles/vehicle";
  }

  @GetMapping("/{op}/{id}")
  public String getEditAndDetails(@PathVariable String op,
                                  @PathVariable Integer id, Model model) {
    addModelAttributes(model);
    model.addAttribute("vehicle", vehicleService.getById(id).orElse(null));
    return "/vehicles/vehicle" + op;
  }


  @RequestMapping(value = "/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
  public String delete(@PathVariable Integer id) {
    vehicleService.delete(id);
    return "redirect:/vehicles/vehicle";
  }
  @GetMapping("/uploadPhoto/{id}")
  public void getIdByVehicle(@PathVariable Integer id) {
    idByUploadPhoto = id;
  }

  @PostMapping("/uploadPhoto")
  public String uploadPhoto(@RequestParam("file") MultipartFile file) throws IOException {
    if (file != null && !file.isEmpty()) {
      String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
      String fileName = UUID.randomUUID() + fileType;
      file.transferTo(new File(uploadPhotoPath + fileName));

      String oldPhoto = vehicleService.getById(idByUploadPhoto).map(Vehicle::getPhoto).orElse("");
      vehicleService.getById(idByUploadPhoto).ifPresent(vehicle -> {
        vehicle.setPhoto(fileName);
        vehicleService.save(vehicle);
      });

      File fileToDelete = new File(uploadPhotoPath + oldPhoto);
      if (fileToDelete.delete()) {
        System.out.println("Файл успешно удален.");
      } else {
        System.err.println("Не удалось удалить файл.");
      }
    }
    return "redirect:/vehicles/vehicle";
  }
}
