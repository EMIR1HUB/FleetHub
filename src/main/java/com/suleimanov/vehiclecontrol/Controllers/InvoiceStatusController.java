package com.suleimanov.vehiclecontrol.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InvoiceStatusController {

  @GetMapping("/invoices_statuses")
  public String getInvoiceStatus() {
    return "invoice_status";
  }
}
