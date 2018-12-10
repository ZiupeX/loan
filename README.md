START SERVICE:

 1. Download source 
 
 2. mvn clean package 
 
 3. mvn spring-boot:run
 
CREATING LOAN:

http://localhost:8080/apply?amount=2.54&term=2007-07-16T19:20:30.45

EXTENDING LOAN:

http://localhost:8080/extend?loanId=1&term=2017-07-16T19:20:30.45
 
 No stubs. To test demo I created in LoanService as @Service singleton one List with loan. Loans are stored in memory. Of course in normal enviroment we will integrate service with database
 
