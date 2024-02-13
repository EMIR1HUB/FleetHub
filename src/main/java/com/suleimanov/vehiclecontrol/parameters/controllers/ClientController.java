package com.suleimanov.vehiclecontrol.parameters.controllers;

import com.suleimanov.vehiclecontrol.parameters.services.ClientService;
import com.suleimanov.vehiclecontrol.parameters.services.RegionService;
import com.suleimanov.vehiclecontrol.parameters.models.Client;
import com.suleimanov.vehiclecontrol.parameters.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/clients")
public class ClientController {

  @Autowired private ClientService clientService;
  @Autowired private RegionService regionService;
  @Autowired private CountryService countryService;


  @GetMapping()
  public String getClients(Model model) {
    model.addAttribute("clients", clientService.getClients());
    model.addAttribute("countries", countryService.getCountries());
    model.addAttribute("regions", regionService.getRegions());
    return "client";
  }

  @GetMapping("/findById")
  @ResponseBody
  public Optional<Client> findById(Integer id) {
    return clientService.findById(id);
  }

  @PostMapping("/addNew")
  public String addNew(Client client) {
    clientService.save(client);
    return "redirect:/clients";
  }

  @RequestMapping(value = "/update", method = {RequestMethod.PUT, RequestMethod.GET})
  public String update(Client client) {
    clientService.save(client);
    return "redirect:/clients";
  }

  @RequestMapping(value = "/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
  public String delete(Integer id) {
    clientService.delete(id);
    return "redirect:/clients";
  }
}
