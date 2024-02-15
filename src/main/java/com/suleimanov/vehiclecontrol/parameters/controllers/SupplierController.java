package com.suleimanov.vehiclecontrol.parameters.controllers;

import com.suleimanov.vehiclecontrol.parameters.models.Supplier;
import com.suleimanov.vehiclecontrol.parameters.services.CountryService;
import com.suleimanov.vehiclecontrol.parameters.services.RegionService;
import com.suleimanov.vehiclecontrol.parameters.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/parameters/suppliers")
public class SupplierController {

  @Autowired private SupplierService supplierService;
  @Autowired private RegionService regionService;
  @Autowired private CountryService countryService;

  @GetMapping()
  public String getAll(Model model) {
    model.addAttribute("suppliers", supplierService.getSuppliers());
    model.addAttribute("countries", countryService.getCountries());
    model.addAttribute("regions", regionService.getRegions());
    return "/parameters/suppliers";
  }

  @GetMapping("/addNew")
  public String getAddNew(Model model){
    model.addAttribute("countries", countryService.getCountries());
   return "/parameters/supplierAdd";
  }

  @PostMapping()
  public String addNew(Supplier supplier) {
    supplierService.save(supplier);
    return "redirect:/parameters/suppliers";
  }

  @RequestMapping(value = "/{op}/{id}", method = {RequestMethod.PUT, RequestMethod.GET})
  public String edit(@PathVariable String op, @PathVariable Integer id, Model model) {
    model.addAttribute("countries", countryService.getCountries());
    model.addAttribute("regions", regionService.getRegions());
    model.addAttribute("supplier", supplierService.getById(id));
    return "/parameters/supplier" + op;
  }

  @RequestMapping(value = "/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
  public String delete(@PathVariable Integer id) {
    supplierService.delete(id);
    return "redirect:/parameters/suppliers";
  }
}
