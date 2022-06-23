package com.mindhub.homebanking.service;

import com.mindhub.homebanking.dto.ClientDTO;
import com.mindhub.homebanking.models.Client;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ClientService {

    List<ClientDTO> getClientDto();

    Client getClientCurrent(Authentication authentication);

    ClientDTO getClientDto(long id);

    void saveClient(Client client);

    Client getClientByEmail(String email);


}
