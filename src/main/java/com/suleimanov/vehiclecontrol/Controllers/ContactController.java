package com.suleimanov.vehiclecontrol.Controllers;

import com.suleimanov.vehiclecontrol.Models.Contact;
import com.suleimanov.vehiclecontrol.Services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/contacts")
public class ContactController {

  @Autowired private ContactService contactService;

  @GetMapping()
  public String getContacts(Model model) {
    model.addAttribute("contacts", contactService.getContacts());
    return "contact";
  }


  @GetMapping("/findById")
  @ResponseBody
  public Optional<Contact> findById(Integer id) {
    return contactService.findById(id);
  }

  @PostMapping("/addNew")
  public String addNew(Contact contact) {
    contactService.save(contact);
    return "redirect:/contacts";
  }

  @RequestMapping(value = "/update", method = {RequestMethod.PUT, RequestMethod.GET})
  public String update(Contact contact) {
    contactService.save(contact);
    return "redirect:/contacts";
  }

  @RequestMapping(value = "/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
  public String delete(Integer id) {
    contactService.delete(id);
    return "redirect:/contacts";
  }
}
