package com.example.graphDatabase;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

import com.example.graphDatabase.service.GraphDatabaseService;

@SpringBootApplication
@EnableNeo4jRepositories("com.example.graphDatabase.repository")
public class GraphDatabaseApplication implements  CommandLineRunner{
     @Autowired
	GraphDatabaseService graphDatabaseService;
	public static void main(String[] args) {
		SpringApplication.run(GraphDatabaseApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		graphDatabaseService.runExample();
		
	}

}
