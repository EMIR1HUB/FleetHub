package com.suleimanov.vehiclecontrol.parameters.controllers;

import com.suleimanov.vehiclecontrol.parameters.models.Contact;
import com.suleimanov.vehiclecontrol.parameters.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/parameters/contacts")
public class ContactController {

  @Autowired private ContactService contactService;

  @GetMapping()
  public String getContacts(Model model) {
    model.addAttribute("contacts", contactService.getContacts());
    return "/parameters/contacts";
  }

  @GetMapping("/{id}")
  @ResponseBody
  public Contact getContact(@PathVariable Integer id) {
    return contactService.getById(id);
  }

  @GetMapping("/addNew")
  public String getAddNew(Model model){
    model.addAttribute("contacts", contactService.getContacts());
    return "/parameters/contactAdd";
  }

  @PostMapping()
  public String addNew(Contact contact) {
    contactService.save(contact);
    return "redirect:/parameters/contacts";
  }

  @GetMapping("/{op}/{id}")
  public String getEdit(@PathVariable String op, @PathVariable Integer id, Model model) {
    model.addAttribute("contact", contactService.getById(id));
    return "/parameters/contact" + op;
  }

  @RequestMapping(value = "/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
  public String delete(@PathVariable Integer id) {
    contactService.delete(id);
    return "redirect:/parameters/contacts";
  }
}
