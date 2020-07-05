# Neo4jExample
Neo4j and Spring Boot Example:
Many context of real live can be modeled as Graph, in this case I want show the relationship between Lender - Client and Client - Client .
Lender loan money to client.
![Lender loan to client](https://github.com/r3ello/Neo4jExample/blob/master/lender-cliente.jpg?raw=true)
Cliente can be provider to other client. 
![Client as provider to other client](https://github.com/r3ello/Neo4jExample/blob/master/cliente-cliente.jpg?raw=true)


# Requirements
Install neo4j (available in https://neo4j.com/download/)
Create new database an import data example:
```
CREATE (Lender1:Lender {name:'Lender1',interestRate:0.3})
CREATE (Lender2:Lender {name:'Lender2',interestRate:0.27})
CREATE (Lender3:Lender {name:'Lender3',interestRate:0.19})

CREATE (Client1:Client{name:'Client1',totalCredit:12500,totalDebit:1780})
CREATE (Client2:Client{name:'Client2',totalCredit:10000,totalDebit:880})
CREATE (Client3:Client{name:'Client3',totalCredit:11700,totalDebit:1562})
CREATE (Client4:Client{name:'Client4',totalCredit:12900,totalDebit:1790})
CREATE (Client5:Client{name:'Client5',totalCredit:19255,totalDebit:1000})
CREATE (Client6:Client{name:'Client6',totalCredit:2500,totalDebit:1570})
CREATE (Client7:Client{name:'Client7',totalCredit:30000,totalDebit:1240})
CREATE (Client8:Client{name:'Client8',totalCredit:21365,totalDebit:950})
CREATE (Client9:Client{name:'Client9',totalCredit:15784,totalDebit:2350})
CREATE (Client10:Client{name:'Client10',totalCredit:17286,totalDebit:990})
CREATE (Client11:Client{name:'Client11',totalCredit:9800,totalDebit:1750})
CREATE (Client12:Client{name:'Client12',totalCredit:11090,totalDebit:800})


CREATE
  (Lender1)-[:LOAN {amount:520}]->(Client1),
  (Lender1)-[:LOAN {amount:355}]->(Client4),
  (Lender1)-[:LOAN {amount:300}]->(Client5),
  (Lender1)-[:LOAN {amount:500}]->(Client10),
  (Lender2)-[:LOAN {amount:270}]->(Client2),
  (Lender2)-[:LOAN {amount:300}]->(Client3),
  (Lender2)-[:LOAN {amount:190}]->(Client8),
  (Lender3)-[:LOAN {amount:250}]->(Client12),
  (Lender3)-[:LOAN {amount:100}]->(Client9),
  (Lender3)-[:LOAN {amount:300}]->(Client8),
  (Lender3)-[:LOAN {amount:280}]->(Client1),
  (Lender3)-[:LOAN {amount:190}]->(Client6),
  (Lender3)-[:LOAN {amount:90}]->(Client7),
  (Lender2)-[:LOAN {amount:170}]->(Client11),
  (Lender2)-[:LOAN {amount:200}]->(Client1),
  (Lender2)-[:LOAN {amount:320}]->(Client6),
  (Lender3)-[:LOAN {amount:80}]->(Client7),
  
  
  (Client4)-[:PROVIDER]->(Client1),
  (Client8)-[:PROVIDER]->(Client5),
  (Client6)-[:PROVIDER]->(Client1),
  (Client1)-[:PROVIDER]->(Client3),
  (Client12)-[:PROVIDER]->(Client7),
  (Client7)-[:PROVIDER]->(Client4),
  (Client3)-[:PROVIDER]->(Client9),
  (Client2)-[:PROVIDER]->(Client1) ;
```
You can see the data as shown in the following image 
![financialNetwork](https://github.com/r3ello/Neo4jExample/blob/master/financialNetwork.jpg?raw=true)

# Dependencies
```
<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-data-neo4j</artifactId>
</dependency>
<dependency>
		<groupId>org.projectlombok</groupId>
		<artifactId>lombok</artifactId>
		<optional>true</optional>
</dependency>
```
# Model
```
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
```
# Repository
Using Neo4jRepository<T,ID>

```
public interface LenderRepository extends Neo4jRepository<Lender, Long>{
 
	List<Lender> findByInterestRateBetween(Double min,Double max);
 
}

public interface ClientRepository extends Neo4jRepository<Client, Long> {
	
	@Query("MATCH (client:Client)<-[r:LOAN]-(lender:Lender) " + 
			"WHERE (client.totalCredit - client.totalDebit)  > $0 " + 
			"RETURN client")
	List<Client> findByBalanceGreaterThan(Double balance);
	
	@Query("MATCH (client:Client)<-[r:LOAN]-(lender:Lender) " + 
			"WHERE client.name=$0 " + 
			"RETURN SUM(r.amount)")
	Double getTotalLoansAmount(String clientName);
} 
```
# Run example
```
public void runExample() 
	{
		System.out.println("All Lenders with interest rate between 0.0 and 0.29");
		lenderRepository.findByInterestRateBetween(0.0, 0.29).forEach(System.out::println);
		
		System.out.println("All Clients with balance amount greater than 1500 , Balance = client.totalCredit - client.totalDebit");
		clientRepository.findByBalanceGreaterThan(1500.0).forEach(System.out::println);
		
		System.out.println("Total Loans amount Client: Client1");
		System.out.println(clientRepository.getTotalLoansAmount("Client1"));
	}
  
```
# Console output
```
All Lenders with interest rate between 0.0 and 0.29
Lender(id=1, name=Lender2, interestRate=0.27, clients=[-[:LOAN {amount:270.0}]-> Client2), -[:LOAN {amount:300.0}]-> Client3), -[:LOAN {amount:170.0}]-> Client12), -[:LOAN {amount:190.0}]-> Client8), -[:LOAN {amount:200.0}]-> Client1), -[:LOAN {amount:320.0}]-> Client6)])
Lender(id=2, name=Lender3, interestRate=0.19, clients=[-[:LOAN {amount:300.0}]-> Client8), -[:LOAN {amount:100.0}]-> Client9), -[:LOAN {amount:250.0}]-> Client12), -[:LOAN {amount:280.0}]-> Client1), -[:LOAN {amount:80.0}]-> Client7), -[:LOAN {amount:190.0}]-> Client6), -[:LOAN {amount:90.0}]-> Client7)])
All Clients with balance amount greater than 1500 , Balance = client.totalCredit - client.totalDebit
Client(id=3, name=Client1, totalCredit=12500.0, totalDebit=1780.0, clients=[])
Client(id=4, name=Client2, totalCredit=10000.0, totalDebit=880.0, clients=[])
Client(id=5, name=Client3, totalCredit=11700.0, totalDebit=1562.0, clients=[])
Client(id=6, name=Client4, totalCredit=12900.0, totalDebit=1790.0, clients=[])
Client(id=7, name=Client5, totalCredit=19255.0, totalDebit=1000.0, clients=[])
Client(id=9, name=Client7, totalCredit=30000.0, totalDebit=1240.0, clients=[])
Client(id=10, name=Client8, totalCredit=21365.0, totalDebit=950.0, clients=[])
Client(id=11, name=Client9, totalCredit=15784.0, totalDebit=2350.0, clients=[])
Client(id=12, name=Client10, totalCredit=17286.0, totalDebit=990.0, clients=[])
Client(id=13, name=Client11, totalCredit=9800.0, totalDebit=1750.0, clients=[])
Client(id=14, name=Client12, totalCredit=11090.0, totalDebit=800.0, clients=[])
Total Loans amount Client: Client1
1000.0
```

