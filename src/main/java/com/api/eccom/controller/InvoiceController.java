package com.api.eccom.controller;

import com.api.eccom.exception.ClientAlreadyExist;
import com.api.eccom.exception.InvoiceAlreadyExist;
import com.api.eccom.model.Client;
import com.api.eccom.model.Invoice;
import com.api.eccom.service.ClientService;
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
    public ResponseEntity<Invoice> create(@RequestBody Invoice invoice) throws InvoiceAlreadyExist {
        return new ResponseEntity<>(this.invoiceService.create(invoice), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Invoice> update(@RequestBody Invoice invoice, @PathVariable Long id) throws Exception {
        return new ResponseEntity<>(this.invoiceService.update(invoice,id), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Invoice> findById(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(this.invoiceService.findById(id), HttpStatus.OK);
    }

    @GetMapping(path = "/")
    public ResponseEntity<List<Invoice>> findAll()  {
        return new ResponseEntity<>(this.invoiceService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Invoice> deleteById(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(this.invoiceService.deleteById(id), HttpStatus.OK);
    }

}
