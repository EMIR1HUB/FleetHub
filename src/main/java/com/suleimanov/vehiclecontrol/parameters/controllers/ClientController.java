package com.suleimanov.vehiclecontrol.parameters.controllers;

import com.suleimanov.vehiclecontrol.parameters.models.Client;
import com.suleimanov.vehiclecontrol.parameters.services.ClientService;
import com.suleimanov.vehiclecontrol.parameters.services.CountryService;
import com.suleimanov.vehiclecontrol.parameters.services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/parameters/clients")
public class ClientController {

  @Autowired private ClientService clientService;
  @Autowired private RegionService regionService;
  @Autowired private CountryService countryService;


  @GetMapping()
  public String getClients(Model model) {
    model.addAttribute("clients", clientService.getClients());
    model.addAttribute("countries", countryService.getCountries());
    model.addAttribute("regions", regionService.getRegions());
    return "/parameters/clients";
  }

  @GetMapping("/{id}")
  @ResponseBody
  public Client getClient(@PathVariable Integer id) {
    return clientService.getById(id);
  }

  @GetMapping("/addNew")
  public String getAddNew(Model model){
    model.addAttribute("countries", countryService.getCountries());
    return "/parameters/clientAdd";
  }

  @PostMapping()
  public String addNew(Client client) {
    clientService.save(client);
    return "redirect:/parameters/clients";
  }

  @GetMapping("/{op}/{id}")
  public String getEdit(@PathVariable String op, @PathVariable Integer id, Model model) {
    model.addAttribute("countries", countryService.getCountries());
    model.addAttribute("regions", regionService.getRegions());
    model.addAttribute("client", clientService.getById(id));
    return "/parameters/client" + op;
  }

  @RequestMapping(value = "/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
  public String delete(@PathVariable Integer id) {
    clientService.delete(id);
    return "redirect:/clients";
  }
}
