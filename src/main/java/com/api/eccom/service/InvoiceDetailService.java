package com.api.eccom.service;



import com.api.eccom.model.Invoice_Details;
import com.api.eccom.repository.Invoice_DetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class InvoiceDetailService {
    @Autowired
    private Invoice_DetailsRepository invoice_DetailsRepository;

    public Invoice_Details create(Invoice_Details newInvoiceDetail) throws Exception {
        Optional<Invoice_Details> invoiceDOp = this.invoice_DetailsRepository.findById(newInvoiceDetail.getInvoice_detail_id());
        return null;
    }

    public Invoice_Details update(Invoice_Details newInvoiceDetail, Long id) throws Exception{
        if (id <= 0) {
            throw new Exception("El id no es valido");
        }
        Optional<Invoice_Details> invoiceDOp = this.invoice_DetailsRepository.findById(id);

        if(invoiceDOp.isEmpty()) {
            log.info("Los detalles de factura que intenta modificar no existen" + newInvoiceDetail);
            throw new Exception("Los detalles de factura que intenta modificar no existe");
        } else {
            Invoice_Details invoiceDBd = invoiceDOp.get();

            invoiceDBd.setAmount(newInvoiceDetail.getAmount());
            invoiceDBd.setPrice(newInvoiceDetail.getPrice());
            invoiceDBd.setInvoice(newInvoiceDetail.getInvoice());
            invoiceDBd.setProducts(newInvoiceDetail.getProducts());

            return this.invoice_DetailsRepository.save(invoiceDBd);
        }
    }

    public Invoice_Details findById(Long id) throws Exception{
        if (id <= 0) {
            throw new Exception("El id no es valido");
        }

        Optional<Invoice_Details> invoiceDOp = this.invoice_DetailsRepository.findById(id);

        if(invoiceDOp.isEmpty()) {
            log.info("Los detalles de factura con el id brindado no existen en la base de datos" + invoiceDOp);
            throw new Exception("La factura solicitada no existe");
        } else {
            return invoiceDOp.get();
        }
    }

    public List<Invoice_Details> findAll() {
        return this.invoice_DetailsRepository.findAll();
    }

    public Invoice_Details deleteById(Long id) throws Exception {
        if (id <= 0) {
            throw new Exception("El id no es valido");
        }

        Optional<Invoice_Details> invoiceDOp = this.invoice_DetailsRepository.findById(id);

        if(invoiceDOp.isEmpty()) {
            log.info("Los detalles de factura con el id brindado no existen en la base de datos" + invoiceDOp);
            throw new Exception("Los detalles de factura solicitados no existen");
        } else {
            invoice_DetailsRepository.deleteById(id);
        }
        return null;
    }

}
