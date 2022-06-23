package com.mindhub.homebanking;

import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;


@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	public CommandLineRunner initData(CardRepository cardRepository,LoanRepository loanRepositorory, ClientRepository repository , AccountRepository accountRepository, TransactionRepository transaction, ClientLoanRepository clientLoanRepository) {
		return (args) -> {

			LocalDateTime myObj = LocalDateTime.now();


			Client dataOne= new Client("Mabel", "Morel","melba@mindhub.com", passwordEncoder.encode("melba"));
			Client dataTwo= new Client("Dylan", "yo","dylan.yo@mindhub.com", passwordEncoder.encode("dylan"));
			repository.save(dataOne);
			repository.save(dataTwo);


			Account accountOne=   new Account("VIN-5000",myObj,800.,dataOne);
			Account accountTwo=   new Account("VIN-8000",myObj,900.,dataOne);
			Account accountThree= new Account("VIN-7500",myObj,700.,dataTwo);
			accountRepository.save(accountOne);
			accountRepository.save(accountThree);
			accountRepository.save(accountTwo);
			System.out.println(dataOne.getAccounts());

			Loan loanOne=   new Loan("Personal",500000, List.of(12,24,36,48,60));
			Loan loanTwo=   new Loan("Hipotecario",100000,List.of(6,12,24));
			Loan loanThree= new Loan("Automotriz",300000, List.of(6,12,24,36));
			loanRepositorory.save(loanOne);
			loanRepositorory.save(loanTwo);
			loanRepositorory.save(loanThree);

			ClientLoan clientLoanOne=   new ClientLoan(400000,60,dataOne,loanTwo);
			ClientLoan clientLoanTwo=   new ClientLoan(50000,12,dataOne,loanOne);
			ClientLoan clientLoanThree= new ClientLoan(100000,24,dataTwo,loanOne);
			ClientLoan clientLoanFour=  new ClientLoan(200000,36,dataTwo,loanThree);
			clientLoanRepository.save(clientLoanOne);
			clientLoanRepository.save(clientLoanTwo);
			clientLoanRepository.save(clientLoanThree);
			clientLoanRepository.save(clientLoanFour);

			Card cardOne=   new Card(dataOne,CardType.CREDIT,myObj,myObj.plusYears(5), 857,"3545 6565 4151 1562",dataOne,CardColorType.GOLD);
			Card cardSix=   new Card(dataOne,CardType.DEBIT,myObj,myObj, 689,"4745 5456 6849 4541",dataOne,CardColorType.TITANIUM);
			cardRepository.save(cardOne);
			cardRepository.save(cardSix);

		};
	}


}
