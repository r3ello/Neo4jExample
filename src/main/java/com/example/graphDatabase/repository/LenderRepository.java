/**
 * 
 */
package com.example.graphDatabase.repository;

import java.util.List;

import org.springframework.data.domain.Range;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.example.graphDatabase.model.Lender;

/**
 * @author Rafael Bello Lara
 *
 */
public interface LenderRepository extends Neo4jRepository<Lender, Long>{
 
	List<Lender> findByInterestRateBetween(Double min,Double max);
 
}
