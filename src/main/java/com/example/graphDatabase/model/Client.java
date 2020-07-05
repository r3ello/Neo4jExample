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
public class Client {
	  @Id @GeneratedValue private Long id;
	   private String name;
	   
	   private Double totalCredit;
	   private Double totalDebit;
	   
	   @Relationship(type = "PROVIDER")
	   private List<Client> clients = new ArrayList<Client>();
	   
	   
}
