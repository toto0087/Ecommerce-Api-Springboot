package com.api.eccom.service;



import com.api.eccom.model.Invoice;
import com.api.eccom.repository.InvoiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public Invoice create(Invoice newInvoice) throws Exception {
        Optional<Invoice> invoiceOp = this.invoiceRepository.findById(newInvoice.getId());

        if(invoiceOp.isPresent()) {
            log.info("La factura ya existe" + newInvoice);
            throw new Exception("La factura que quiere agregar ya existe");
        } else {
            return this.invoiceRepository.save(newInvoice);
        }
    }

    public Invoice update(Invoice newInvoice, Long id) throws Exception{
        if (id <= 0) {
            throw new Exception("El id no es valido");
        }
        Optional<Invoice> invoiceOp = this.invoiceRepository.findById(id);

        if(invoiceOp.isEmpty()) {
            log.info("La factura que intenta modificar no existe" + newInvoice);
            throw new Exception("La factura que intenta modificar no existe");
        } else {
            Invoice invoiceBd = invoiceOp.get();

            invoiceBd.setCreated_at(newInvoice.getCreated_at());
            invoiceBd.setClient(newInvoice.getClient());
            invoiceBd.setTotal(newInvoice.getTotal());

            return this.invoiceRepository.save(invoiceBd);
        }
    }

    public Invoice findById(Long id) throws Exception{
        if (id <= 0) {
            throw new Exception("El id no es valido");
        }

        Optional<Invoice> invoiceOp = this.invoiceRepository.findById(id);

        if(invoiceOp.isEmpty()) {
            log.info("La factura con el id brindado no existe en la base de datos" + invoiceOp);
            throw new Exception("La factura solicitada no existe");
        } else {
            return invoiceOp.get();
        }
    }

    public List<Invoice> findAll() {
        return this.invoiceRepository.findAll();
    }


    public Invoice deleteById(Long id) throws Exception {
        if (id <= 0) {
            throw new Exception("El id no es valido");
        }

        Optional<Invoice> invoiceOp = this.invoiceRepository.findById(id);

        if(invoiceOp.isEmpty()) {
            log.info("La factura con el id brindado no existe en la base de datos" + invoiceOp);
            throw new Exception("La factura solicitada no existe");
        } else {
            invoiceRepository.deleteById(id);
        }
        return null;
    }

}
