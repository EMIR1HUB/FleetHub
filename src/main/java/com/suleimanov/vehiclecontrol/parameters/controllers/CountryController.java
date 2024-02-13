package com.suleimanov.vehiclecontrol.parameters.controllers;

import com.suleimanov.vehiclecontrol.parameters.models.Country;
import com.suleimanov.vehiclecontrol.parameters.models.Region;
import com.suleimanov.vehiclecontrol.parameters.services.CountryService;
import com.suleimanov.vehiclecontrol.parameters.services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/parameters/countries")
public class CountryController {

  @Autowired private CountryService countryService;
  @Autowired private RegionService regionService;

  @GetMapping()
  public String getAll(Model model) {
    model.addAttribute("countries", countryService.getCountries());
    return "/parameters/country";
  }

  // The get Country by Id
  @GetMapping("/{id}")
  @ResponseBody
  public Country getCountry(@PathVariable Integer id){
    return countryService.getById(id);
  }

  @GetMapping("/addNew")
  public String getAddNew(){
    return "/parameters/countryAdd";
  }

  @PostMapping()
  public String addNew(Country country){
    countryService.save(country);
    return "redirect:/parameters/countries";
  }

  @GetMapping("/edit/{id}")
  public String getEdit(@PathVariable Integer id, Model model){
    model.addAttribute("country", countryService.getById(id));
    return "/parameters/countryEdit";
  }

  @RequestMapping(value = "/update/{id}", method = {RequestMethod.PUT, RequestMethod.GET})
  public String update(Country country){
    countryService.save(country);
    return "redirect:/parameters/countries";
  }

  @RequestMapping(value = "/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
  public String delete(@PathVariable Integer id){
    countryService.delete(id);
    return "redirect:/parameters/countries";
  }

  @GetMapping("/details/{id}")
  public String getDetails(@PathVariable Integer id, Model model){
    model.addAttribute("country", countryService.getById(id));
    return "parameters/countryDetails";
  }

  @GetMapping("/parameters/country/{id}")
  @ResponseBody
  public List<Region> getRegionsByCountry(@PathVariable Integer id){
    Country country = countryService.getById(id);
    if (country != null) {
      return regionService.getRegionsByCountry(country);
    } else {
      return Collections.emptyList();
    }
  }
}
