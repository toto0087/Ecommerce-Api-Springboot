package com.api.eccom.service;


import com.api.eccom.model.Client;
import com.api.eccom.repository.ClientRepository;
import com.api.eccom.validation.ClientValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class ClientService {


    @Autowired
    private ClientValidation clientValidation;

    @Autowired
    private ClientRepository clientRepository;

    public Client create(Client newClient) throws Exception {
        clientValidation.createValidation(newClient);
        return clientRepository.save(newClient);
    }

    public Client update(Client newClient, Long id) throws Exception{
        clientValidation.updateValidation(newClient);
        return clientRepository.save(newClient);
    }

    public Client findById(Long id) throws Exception{
        clientValidation.findByIdValidation(id);
        return clientRepository.findById(id).orElse(null);
    }


    public List<Client> findAll() {
        return this.clientRepository.findAll();
    }

    public Client deleteById(Long id) throws Exception {
        clientValidation.deleteValidation(id);
        clientRepository.deleteById(id);
        return null;
    }
}
