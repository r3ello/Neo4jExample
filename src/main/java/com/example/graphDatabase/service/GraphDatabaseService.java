/**
 * 
 */
package com.example.graphDatabase.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.graphDatabase.model.Lender;
import com.example.graphDatabase.repository.ClientRepository;
import com.example.graphDatabase.repository.LenderRepository;

/**
 * @author Rafael Bello Lara
 *
 */
@Component
public class GraphDatabaseService {

	@Autowired
	LenderRepository lenderRepository;
	@Autowired
	ClientRepository clientRepository;
	
	public void runExample() 
	{
		System.out.println("All Lenders with interest rate between 0.0 and 0.29");
		lenderRepository.findByInterestRateBetween(0.0, 0.29).forEach(System.out::println);
		
		System.out.println("All Clients with balance amount greater than 1500 , Balance = client.totalCredit - client.totalDebit");
		clientRepository.findByBalanceGreaterThan(1500.0).forEach(System.out::println);
		
		System.out.println("Total Loans amount Client: Client1");
		System.out.println(clientRepository.getTotalLoansAmount("Client1"));
		
		
	}
}
