package com.suleimanov.vehiclecontrol.parameters.controllers;

import com.suleimanov.vehiclecontrol.parameters.models.Region;
import com.suleimanov.vehiclecontrol.parameters.services.CountryService;
import com.suleimanov.vehiclecontrol.parameters.services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/parameters/regions")
public class RegionController {

  @Autowired
  private RegionService regionService;
  @Autowired
  private CountryService countryService;

  public void addModelAttribute(Model model) {
    model.addAttribute("regions", regionService.getRegions());
    model.addAttribute("countries", countryService.getCountries());
  }

  @GetMapping()
  public String getRegions(Model model) {
    addModelAttribute(model);
    return "/parameters/regions";
  }

  @GetMapping("/addNew")
  public String getAddNew(Model model) {
    addModelAttribute(model);
    return "/parameters/regionAdd";
  }

  @PostMapping()
  public String addNew(Region region) {
    regionService.save(region);
    return "redirect:/parameters/regions";
  }

  @GetMapping("/{op}/{id}")
  public String getEdit(@PathVariable String op, @PathVariable Integer id, Model model) {
    addModelAttribute(model);
    model.addAttribute("region", regionService.getById(id));
    return "/parameters/region" + op;
  }

  @RequestMapping(value = "/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
  public String delete(@PathVariable Integer id) {
    regionService.delete(id);
    return "redirect:/parameters/regions";
  }
}
