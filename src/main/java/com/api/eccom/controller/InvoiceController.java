package com.api.eccom.controller;


import com.api.eccom.model.Invoice;
import com.api.eccom.model.InvoiceList;
import com.api.eccom.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping(path = "/")
    public ResponseEntity<List<Invoice>> create(@RequestBody InvoiceList request) throws Exception {
        return new ResponseEntity<>(invoiceService.create(request.invoiceDetailList, request.client), HttpStatus.OK);
    }

    @GetMapping(path = "/{invoice_detail_id}")
    public ResponseEntity<Invoice> findById(@PathVariable Long invoice_detail_id) throws Exception {
        return new ResponseEntity<>(this.invoiceService.findById(invoice_detail_id), HttpStatus.OK);
    }

    @GetMapping(path = "/")
    public ResponseEntity<List<Invoice>> findAll()  {
        return new ResponseEntity<>(this.invoiceService.findAll(), HttpStatus.OK);
    }

}
