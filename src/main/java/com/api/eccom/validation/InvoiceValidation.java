package com.api.eccom.validation;

import com.api.eccom.model.Client;
import com.api.eccom.model.Invoice_Details;
import com.api.eccom.model.Products;
import com.api.eccom.repository.ClientRepository;
import com.api.eccom.repository.ProductsRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class InvoiceValidation {
    private final ClientRepository clientRepository;
    private final ProductsRepository productsRepository;

    public InvoiceValidation(ClientRepository clientRepository, ProductsRepository productsRepository) {
        this.clientRepository = clientRepository;
        this.productsRepository = productsRepository;
    }

    public void createValidation(Client client) throws Exception {

        Optional<Client> paramClient = clientRepository.findById(client.getId());

        if (paramClient.isEmpty()) {
            throw new Exception("El cliente no existe");
        }
    }

    public void createValidation(Invoice_Details invoiceDetail, Client client) throws Exception {
        Optional<Client> paramClient = clientRepository.findById(client.getId());
        Optional<Products> paramProduct = productsRepository.findById(invoiceDetail.getProducts().getId());

        if(paramClient.isEmpty()){
            throw new Exception("El cliente no existe");
        }

        if(paramProduct.get().getStock() < invoiceDetail.getAmount()){
            throw new Exception("El producto no tiene stock suficiente");
        }
    }

    public void existsByIdValidation(Optional object) throws Exception {
        if(object.isEmpty()){
            throw new Exception("El objeto no existe");
        }
    }

    public void findByIdValidation(Long id) throws Exception {
        if(id <= 0){
            throw new Exception("El id no es válido");
        }
    }

}


