package com.suleimanov.vehiclecontrol.accounts.controllers;

import com.suleimanov.vehiclecontrol.accounts.models.Invoice;
import com.suleimanov.vehiclecontrol.parameters.services.ClientService;
import com.suleimanov.vehiclecontrol.accounts.services.InvoiceService;
import com.suleimanov.vehiclecontrol.accounts.services.InvoiceStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/invoices")
public class InvoiceController {

  @Autowired private InvoiceService invoiceService;
  @Autowired private ClientService clientService;
  @Autowired private InvoiceStatusService invoiceStatusService;

  @GetMapping("")
  public String getInvoice(Model model) {
    model.addAttribute("invoices", invoiceService.getInvoices());
    model.addAttribute("clients", clientService.getClients());
    model.addAttribute("invoiceStatuses", invoiceStatusService.getInvoiceStatuses());
    return "invoice";
  }

  @GetMapping("/findById")
  @ResponseBody
  public Optional<Invoice> findById(Integer id) {
    return invoiceService.findById(id);
  }

  @PostMapping("/addNew")
  public String addNew(Invoice invoice) {
    invoiceService.save(invoice);
    return "redirect:/invoices";
  }

  @RequestMapping(value = "/update", method = {RequestMethod.PUT, RequestMethod.GET})
  public String update(Invoice invoice) {
    invoiceService.save(invoice);
    return "redirect:/invoices";
  }

  @RequestMapping(value = "/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
  public String delete(Integer id) {
    invoiceService.delete(id);
    return "redirect:/invoices";
  }
}
