package com.suleimanov.vehiclecontrol.Controllers;

import com.suleimanov.vehiclecontrol.Models.Supplier;
import com.suleimanov.vehiclecontrol.Services.CountryService;
import com.suleimanov.vehiclecontrol.Services.RegionService;
import com.suleimanov.vehiclecontrol.Services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/suppliers")
public class SupplierController {

  @Autowired private SupplierService supplierService;
  @Autowired private RegionService regionService;
  @Autowired private CountryService countryService;

  @GetMapping()
  public String getSupplier(Model model) {
    model.addAttribute("suppliers", supplierService.getSuppliers());
    model.addAttribute("countries", countryService.getCountries());
    model.addAttribute("regions", regionService.getRegions());
    return "supplier";
  }

  @GetMapping("/findById")
  @ResponseBody
  public Optional<Supplier> findById(Integer id) {
    return supplierService.findById(id);
  }

  @PostMapping("/addNew")
  public String addNew(Supplier supplier) {
    supplierService.save(supplier);
    return "redirect:/suppliers";
  }

  @RequestMapping(value = "/update", method = {RequestMethod.PUT, RequestMethod.GET})
  public String update(Supplier supplier) {
    supplierService.save(supplier);
    return "redirect:/suppliers";
  }

  @RequestMapping(value = "/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
  public String delete(Integer id) {
    supplierService.delete(id);
    return "redirect:/suppliers";
  }
}
