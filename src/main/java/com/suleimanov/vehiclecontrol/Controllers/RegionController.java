package com.suleimanov.vehiclecontrol.Controllers;

import com.suleimanov.vehiclecontrol.Models.Country;
import com.suleimanov.vehiclecontrol.Models.Region;
import com.suleimanov.vehiclecontrol.Repositories.Services.CountryService;
import com.suleimanov.vehiclecontrol.Repositories.Services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/regions")
public class RegionController {

  @Autowired
  private RegionService regionService;
  @Autowired
  private CountryService countryService;

  @GetMapping()
  public String getRegion(Model model) {
    List<Region> regionList = regionService.getRegions();
    model.addAttribute("regions", regionList);

    List<Country> countryList = countryService.getCountries();
    model.addAttribute("countries", countryList);

    return "region";
  }

  @GetMapping("/findById")
  @ResponseBody
  public Optional<Region> findById(Integer id) {
    return regionService.findById(id);
  }

  @PostMapping("/addNew")
  public String addNew(Region region) {
    regionService.save(region);
    return "redirect:/regions";
  }

  @RequestMapping(value = "/update", method = {RequestMethod.PUT, RequestMethod.GET})
  public String update(Region region) {
    regionService.save(region);
    return "redirect:/regions";
  }

  @RequestMapping(value = "/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
  public String delete(Integer id) {
    regionService.delete(id);
    return "redirect:/regions";
  }
}
