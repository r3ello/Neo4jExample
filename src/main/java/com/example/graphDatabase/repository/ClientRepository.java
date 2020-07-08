/**
 * 
 */
package com.example.graphDatabase.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.example.graphDatabase.model.Client;


/**
 * @author Rafael Bello Lara
 *
 */
public interface ClientRepository extends Neo4jRepository<Client, Long> {
	
	@Query("MATCH (client:Client) " + 
			"WHERE (client.totalCredit - client.totalDebit)  > $0 " + 
			"RETURN client")
	List<Client> findByBalanceGreaterThan(Double balance);
	
	@Query("MATCH (client:Client)<-[r:LOAN]-(lender:Lender) " + 
			"WHERE client.name=$0 " + 
			"RETURN SUM(r.amount)")
	Double getTotalLoansAmount(String clientName);
} 
