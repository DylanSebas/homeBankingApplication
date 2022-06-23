package com.mindhub.homebanking.service;

import com.mindhub.homebanking.dto.AccountDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface AccountService {

    List<AccountDTO> getAccountDto();

    AccountDTO getAccountDto(long id);

    void saveAccount(Account account);

    Account getAccountFindByNumber(String Number);


}
