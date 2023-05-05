package com.api.eccom.service;



import com.api.eccom.model.*;
import com.api.eccom.repository.ClientRepository;
import com.api.eccom.repository.InvoiceRepository;
import com.api.eccom.validation.InvoiceValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.eccom.repository.ProductsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private ProductsRepository productRepository;
    @Autowired
    private InvoiceValidation invoiceValidation;
    @Autowired
    private InvoiceDetailService invoiceDetailService;
    @Autowired
    private ClientRepository clientRepository;


    public List<Invoice> create(List<Invoice_Details> invoiceDetailList, Client client) throws Exception {
        invoiceValidation.existsByIdValidation(clientRepository.findById(client.getId()));
        List<Invoice> addedInvoices = new ArrayList<>();
        double total = 0;
        // recorre la lista de detalles de factura
        for(Invoice_Details invoiceDetail : invoiceDetailList) {
            Optional<Products> paramProduct = productRepository.findById(invoiceDetail.getProducts().getId());
            invoiceValidation.existsByIdValidation(paramProduct);
            invoiceDetail.setProducts(paramProduct.get());
            //suma el precio del producto por cantidad
            total += invoiceDetail.getAmount() * invoiceDetail.getProducts().getPrice();
        }
        //se crea nueva fc
        Invoice invoice = new Invoice();
        invoice.setClient(client);
        invoice.setTotal(total);
        // valida la factura
        invoiceValidation.createValidation(client);
        Invoice invoiceCreated = invoiceRepository.save(invoice);
        for(Invoice_Details invoiceDetail : invoiceDetailList) {
            invoiceDetailService.create(invoiceDetail, invoiceCreated);
            addedInvoices.add(invoiceCreated);
        }
        return addedInvoices;
    }


    public Invoice findById(Long id) throws Exception{
        invoiceValidation.findByIdValidation(id);
        return invoiceRepository.findById(id).orElse(null);
    }

    public List<Invoice> findAll() {
        return this.invoiceRepository.findAll();
    }
}
