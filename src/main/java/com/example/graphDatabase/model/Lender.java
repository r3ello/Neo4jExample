/**
 * 
 */
package com.example.graphDatabase.model;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Rafael Bello Lara
 *
 */
@NodeEntity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lender {

	   @Id @GeneratedValue private Long id;
	   private String name;	  
	   private Double interestRate;
	   
	   @Relationship(type = "LOAN")
	   private List<LoanAmount> clients = new ArrayList<LoanAmount>();
}
