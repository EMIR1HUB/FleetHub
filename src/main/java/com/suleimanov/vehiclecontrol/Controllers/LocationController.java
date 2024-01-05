package com.suleimanov.vehiclecontrol.Controllers;

import com.suleimanov.vehiclecontrol.Models.Country;
import com.suleimanov.vehiclecontrol.Models.Location;
import com.suleimanov.vehiclecontrol.Models.Region;
import com.suleimanov.vehiclecontrol.Services.CountryService;
import com.suleimanov.vehiclecontrol.Services.LocationService;
import com.suleimanov.vehiclecontrol.Services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/locations")
public class LocationController {

  @Autowired private LocationService locationService;
  @Autowired private CountryService countryService;
  @Autowired private RegionService regionService;

  @GetMapping()
  public String getLocation(Model model) {
    List<Location> locationList = locationService.getLocations();
    List<Country> countryList = countryService.getCountries();
    List<Region> regionList = regionService.getRegions();

    model.addAttribute("locations", locationList);
    model.addAttribute("countries", countryList);
    model.addAttribute("regions", regionList);

    return "location";
  }

  @GetMapping("/findById")
  @ResponseBody
  public Optional<Location> findById(Integer id) {
    return locationService.findById(id);
  }

  @PostMapping("/addNew")
  public String addNew(Location location) {
    locationService.save(location);
    return "redirect:/locations";
  }

  @RequestMapping(value = "/update", method = {RequestMethod.PUT, RequestMethod.GET})
  public String update(Location location) {
    locationService.save(location);
    return "redirect:/locations";
  }

  @RequestMapping(value = "/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
  public String delete(Integer id) {
    locationService.delete(id);
    return "redirect:/locations";
  }
}
