package com.api.eccom.service;

import com.api.eccom.model.Invoice_Details;
import com.api.eccom.repository.Invoice_DetailsRepository;
import com.api.eccom.validation.InvoiceDetailValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.eccom.model.Invoice;


import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class InvoiceDetailService {
    @Autowired
    private InvoiceDetailValidation invoiceDetailValidation;

    @Autowired
    private Invoice_DetailsRepository invoice_DetailsRepository;

    public Invoice_Details create(Invoice_Details invoiceDetail, Invoice newInvoice) throws Exception {
        invoiceDetail.setInvoice(newInvoice);
        invoiceDetailValidation.createValidation(invoiceDetail);
        invoiceDetail.setPrice(invoiceDetail.getAmount() * invoiceDetail.getProducts().getPrice());
        invoice_DetailsRepository.save(invoiceDetail);
        return invoiceDetail;
    }

    public Invoice_Details update(Invoice_Details newInvoiceDetail) throws Exception{
        invoiceDetailValidation.updateValidation(newInvoiceDetail);
        return invoice_DetailsRepository.save(newInvoiceDetail);
    }

    public Invoice_Details findById(Long id) throws Exception{
        invoiceDetailValidation.findByIdValidation(id);
        return invoice_DetailsRepository.findById(id).orElse(null);
    }

    public List<Invoice_Details> findAll() {
        return this.invoice_DetailsRepository.findAll();
    }

}
