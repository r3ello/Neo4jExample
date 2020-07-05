/**
 * 
 */
package com.example.graphDatabase.model;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



/**
 * @author Rafael Bello Lara
 *
 */
@RelationshipEntity(type = "LOAN")
@Getter
@Setter
@NoArgsConstructor

public class LoanAmount {

	@Id @GeneratedValue   private Long relationshipId;
    @Property  private Double amount;
    @StartNode private Lender lender;
    @EndNode   private Client client;
    
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return "-[:LOAN {amount:"+amount+"}]-> "+client.getName()+")";
    }
}
