package com.mindhub.homebanking.repository;

import com.mindhub.homebanking.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RepositoryRestResource
public interface ClientRepository extends JpaRepository<Client,Long>{

    Client findByEmail(String email);

}
