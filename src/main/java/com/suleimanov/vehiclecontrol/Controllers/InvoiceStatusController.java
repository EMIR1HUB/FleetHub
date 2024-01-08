package com.suleimanov.vehiclecontrol.Controllers;

import com.suleimanov.vehiclecontrol.Models.InvoiceStatus;
import com.suleimanov.vehiclecontrol.Services.InvoiceStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/invoices_statuses")
public class InvoiceStatusController {

  @Autowired
  private InvoiceStatusService invoiceStatusService;

  @GetMapping()
  public String getInvoiceStatus(Model model) {
    List<InvoiceStatus> invoiceStatusList = invoiceStatusService.getInvoiceStatuses();

    model.addAttribute("invoiceStatuses", invoiceStatusList);
    return "invoice_status";
  }

  @GetMapping("/findById")
  @ResponseBody
  public Optional<InvoiceStatus> findById(Integer id) {
    return invoiceStatusService.findById(id);
  }

  @PostMapping("/addNew")
  public String addNew(InvoiceStatus invoiceStatus) {
    invoiceStatusService.save(invoiceStatus);
    return "redirect:/invoices_statuses";
  }

  @RequestMapping(value = "/update", method = {RequestMethod.PUT, RequestMethod.GET})
  public String update(InvoiceStatus invoiceStatus) {
    invoiceStatusService.save(invoiceStatus);
    return "redirect:/invoices_statuses";
  }

  @RequestMapping(value = "/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
  public String delete(Integer id) {
    invoiceStatusService.delete(id);
    return "redirect:/invoices_statuses";
  }
}
