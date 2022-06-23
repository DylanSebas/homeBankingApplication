package com.mindhub.homebanking.controller;


import com.mindhub.homebanking.dto.ClientDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repository.AccountRepository;
import com.mindhub.homebanking.repository.ClientRepository;
import com.mindhub.homebanking.service.AccountService;
import com.mindhub.homebanking.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;
import java.util.List;

import static com.mindhub.homebanking.Utils.utils.getRandomNumber;
import static java.util.stream.Collectors.toList;

@RestController @RequestMapping("/api")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/clients")
    public List<ClientDTO> getAll() {
        return clientService.getClientDto();
    }

    @GetMapping("clients/{id}")
    public ClientDTO getClient(@PathVariable Long id){
        return clientService.getClientDto(id);
    }


    @Autowired
    private PasswordEncoder passwordEncoder;



    @PostMapping( "/clients")

    public ResponseEntity<Object> register(

            @RequestParam String firstName, @RequestParam String lastName,

            @RequestParam String email, @RequestParam String password) {


        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {

            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);

        }


        if (clientService.getClientByEmail(email) !=  null) {

            return new ResponseEntity<>("Name already in use", HttpStatus.FORBIDDEN);

        }



        Client clientNew=new Client(firstName, lastName, email, passwordEncoder.encode(password));
        clientService.saveClient(clientNew);
       accountService.saveAccount(new Account("VIN"+getRandomNumber(1000000,99999999), LocalDateTime.now(),0.,clientNew));

        return new ResponseEntity<>(HttpStatus.CREATED);

    }


    @GetMapping("/clients/current")
    public ClientDTO getAll(Authentication authentication) {
        return new ClientDTO(clientService.getClientCurrent(authentication));
    }


}