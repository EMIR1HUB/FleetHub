package com.suleimanov.vehiclecontrol.parameters.controllers;

import com.suleimanov.vehiclecontrol.parameters.models.Location;
import com.suleimanov.vehiclecontrol.parameters.services.CountryService;
import com.suleimanov.vehiclecontrol.parameters.services.LocationService;
import com.suleimanov.vehiclecontrol.parameters.services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/parameters/locations")
public class LocationController {

  @Autowired private LocationService locationService;
  @Autowired private CountryService countryService;
  @Autowired private RegionService regionService;

  public void addModelAttribute(Model model){
    model.addAttribute("regions", regionService.getRegions());
    model.addAttribute("countries", countryService.getCountries());
    model.addAttribute("locations", locationService.getLocations());
  }

  @GetMapping()
  public String getLocation(Model model) {
    model.addAttribute("locations", locationService.getLocations());
    return "/parameters/locations";
  }

  @GetMapping("/addNew")
  public String getAddNew(Model model){
    model.addAttribute("countries", countryService.getCountries());
    return "/parameters/locationAdd";
  }

  @PostMapping()
  public String addNew(Location location) {
    locationService.save(location);
    return "redirect:/parameters/locations";
  }

  @GetMapping("/{op}/{id}")
  public String getEdit(@PathVariable String op, @PathVariable Integer id, Model model) {
    model.addAttribute("countries", countryService.getCountries());
    model.addAttribute("regions", regionService.getRegions());
    model.addAttribute("location", locationService.getById(id));
    return "/parameters/location" + op;
  }

  @RequestMapping(value = "/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
  public String delete(@PathVariable Integer id) {
    locationService.delete(id);
    return "redirect:/parameters/locations";
  }
}
