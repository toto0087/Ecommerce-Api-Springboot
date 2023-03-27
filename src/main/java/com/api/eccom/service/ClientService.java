package com.api.eccom.service;

import com.api.eccom.exception.ClientAlreadyExist;
import com.api.eccom.exception.ClientNotFound;
import com.api.eccom.model.Client;
import com.api.eccom.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ClientService {


    @Autowired
    private ClientRepository clientRepository;

    public Client create(Client newClient) throws ClientAlreadyExist {
        Optional<Client> clientOp = this.clientRepository.findBydocnumber(newClient.getDocnumber());

        if(clientOp.isPresent()) {
            log.info("El cliente ya existe" + newClient);
            throw new ClientAlreadyExist("El cliente que quiere agregar ya existe");
        } else {
            return this.clientRepository.save(newClient);
        }
    }

    public Client update(Client newClient, Long id) throws Exception{
        if (id <= 0) {
            throw new Exception("El id no es valido");
        }
        Optional<Client> clientOp = this.clientRepository.findById(id);

        if(clientOp.isEmpty()) {
            log.info("El cliente que intenta modificar no existe" + newClient);
            throw new ClientNotFound("El cliente que intenta modificar no existe");
        } else {
            Client clientBd = clientOp.get();

            clientBd.setDocnumber(newClient.getDocnumber());
            clientBd.setName(newClient.getName());
            clientBd.setLastname(newClient.getLastname());

            return this.clientRepository.save(clientBd);
        }
    }

    public Client findById(Long id) throws Exception{
        if (id <= 0) {
            throw new Exception("El id no es valido");
        }

        Optional<Client> clientOp = this.clientRepository.findById(id);

        if(clientOp.isEmpty()) {
            log.info("El cliente con el id brindado no existe en la base de datos" + clientOp);
            throw new ClientNotFound("El cliente solicitado no existe");
        } else {
            return clientOp.get();
        }
    }


    public List<Client> findAll() {
        return this.clientRepository.findAll();
    }

    public Client deleteById(Long id) throws Exception {
        if (id <= 0) {
            throw new Exception("El id no es valido");
        }

        Optional<Client> clientOp = this.clientRepository.findById(id);

        if(clientOp.isEmpty()) {
            log.info("El Cliente con el id brindado no existe en la base de datos" + clientOp);
            throw new ClientNotFound("El cliente solicitado no existe");
        } else {
            clientRepository.deleteById(id);
        }
        return null;
    }
}
