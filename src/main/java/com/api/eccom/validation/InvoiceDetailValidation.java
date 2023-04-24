package com.api.eccom.validation;


import com.api.eccom.model.Invoice_Details;
import com.api.eccom.repository.Invoice_DetailsRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class InvoiceDetailValidation {

    private final Invoice_DetailsRepository invoice_DetailsRepository;

    public InvoiceDetailValidation(Invoice_DetailsRepository invoiceDetailsRepository) {
        invoice_DetailsRepository = invoiceDetailsRepository;
    }


    public void createValidation(Invoice_Details invoiceDetail) throws Exception {
        hasInvalidValues(invoiceDetail);
    }

    public void updateValidation(Invoice_Details invoiceDetail) throws Exception {
        hasInvalidValues(invoiceDetail);

        Optional<Invoice_Details> optionalInvoiceDetail = invoice_DetailsRepository.findById(invoiceDetail.getInvoice_detail_id());

        if (invoiceDetail.getInvoice_detail_id() <= 0){
            throw new Exception("El id no es válido");
        }
        if(optionalInvoiceDetail.isEmpty()){
            throw new Exception("El detalle de factura que intenta modificar no existe");
        }
    }



    public void findByIdValidation(Long id) throws Exception {
        if(id <= 0){
            throw new Exception("El id no es válido");
        }
    }


    private void hasInvalidValues(Invoice_Details invoiceDetail) throws Exception{
        if (invoiceDetail.getAmount() <= 0) {
            throw new Exception("La cantidad es inválida");
        }
        if(invoiceDetail.getPrice() != null){
            throw new Exception("El precio debe ser generado automáticamente");
        }
        if(invoiceDetail.getProducts() == null){
            throw new Exception("El producto es inválido.");
        }
        if (invoiceDetail.getInvoice() == null){
            throw new Exception("La factura es inválida.");
        }
    }
}
