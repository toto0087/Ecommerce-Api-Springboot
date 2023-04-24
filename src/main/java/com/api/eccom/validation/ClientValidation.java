package com.api.eccom.validation;

import com.api.eccom.model.Client;
import com.api.eccom.repository.ClientRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClientValidation {
    private final ClientRepository clientRepository;


    public ClientValidation(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    public void createValidation(Client client) throws Exception {
        hasInvalidValues(client);
    }

    public void updateValidation(Client client) throws Exception {
        hasInvalidValues(client);

        Optional<Client> opClient = clientRepository.findById(client.getId());

        if(opClient.isEmpty()){
            throw new Exception("El cliente que intenta modificar no existe");
        }
    }

    public void findByIdValidation(Long id) throws Exception {
        if(id <= 0){
            throw new Exception("El id no es válido");
        }
    }

    public void deleteValidation(Long id) throws Exception {
        if(id <= 0){
            throw new Exception("El id no es válido");
        }
    }

    private void hasInvalidValues(Client client) throws Exception{
        if(client.getName() == null || client.getName().isEmpty()) {
            throw new Exception("El nombre del cliente no tiene que estar vacio");
        }
        if (client.getLastname() == null || client.getLastname().isEmpty()) {
            throw new Exception("El apellido del cliente no tiene que estar vacío");
        }
        if (client.getDocnumber() == null || client.getDocnumber().isEmpty()) {
            throw new Exception("El documento del cliente no tiene que estar vacío");
        }
    }
}
