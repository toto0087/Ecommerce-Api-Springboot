package com.api.eccom.controller;

import com.api.eccom.exception.InvoiceDetailAlreadyExist;
import com.api.eccom.model.Invoice_Details;
import com.api.eccom.service.InvoiceDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/invoiceDetails")
public class InvoiceDetailController {

    @Autowired
    private InvoiceDetailService invoiceDetailService;

    @PostMapping(path = "/")
    public ResponseEntity<Invoice_Details> create(@RequestBody Invoice_Details invoice_Details) throws InvoiceDetailAlreadyExist {
        return new ResponseEntity<>(this.invoiceDetailService.create(invoice_Details), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Invoice_Details> update(@RequestBody Invoice_Details invoice_Details, @PathVariable Long id) throws Exception {
        return new ResponseEntity<>(this.invoiceDetailService.update(invoice_Details,id), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Invoice_Details> findById(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(this.invoiceDetailService.findById(id), HttpStatus.OK);
    }

    @GetMapping(path = "/")
    public ResponseEntity<List<Invoice_Details>> findAll()  {
        return new ResponseEntity<>(this.invoiceDetailService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Invoice_Details> deleteById(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(this.invoiceDetailService.deleteById(id), HttpStatus.OK);
    }



}
