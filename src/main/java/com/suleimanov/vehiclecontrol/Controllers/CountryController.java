package com.suleimanov.vehiclecontrol.Controllers;

import com.suleimanov.vehiclecontrol.Models.Country;
import com.suleimanov.vehiclecontrol.Services.CountryService;
import com.suleimanov.vehiclecontrol.Services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/countries")
public class CountryController {

  @Autowired
  private CountryService countryService;

  @Autowired
  RegionService regionService;

  @GetMapping()
  public String getAll(Model model) {
    model.addAttribute("countries", countryService.getCountries());
    model.addAttribute("regions", regionService.getRegions());
    return "country";
  }

  @GetMapping("/findById")
  @ResponseBody
  public Optional<Country> findById(Integer id){
    return countryService.findById(id);
  }

  @PostMapping("/addNew")
  public String addNew(Country country){
    countryService.save(country);
    return "redirect:/countries";
  }

  @RequestMapping(value = "/update", method = {RequestMethod.PUT, RequestMethod.GET})
  public String update(Country country){
    countryService.save(country);
    return "redirect:/countries";
  }

  @RequestMapping(value = "/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
  public String delete(Integer id){
    countryService.delete(id);
    return "redirect:/countries";
  }


}
