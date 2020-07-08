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
 
