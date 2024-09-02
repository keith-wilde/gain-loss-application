# Alpha Theory Integration Full Stack Developer 2024 Take-home Assignment

Using Java and SQL develop a small program that reads in `Gain Loss Portfolio Sample Data.csv` and reports Gain/Loss data per fund and per ticker within a Portfolio. 

Gain/Loss is defined as the difference in “Market Value” amounts between two time periods. `Gain/Loss = (New Value – Old Value) / Old Value`. The Portfolio value per day is the sum of contributing ticker “Market Value” amounts for each portfolio for that day.

Include some endpoints that:
- Display per ticker per portfolio Gain/Loss from the latest versus its prior time period. - group by query? 
- Display per ticker Gain/Loss from the oldest to the latest time period.
- Roll up Portfolio level Gain/Loss for both “the latest versus its prior time period” and “oldest to the latest time period”.
- Rank tickers within a Portfolio in order of total gain/loss.
- Determine which Portfolio had a better performance from the earliest time period
- (Optional) Anything else you see fit.

For implementation please:

1. Use Spring Boot 2.7+ or 3.x.
2. Instead of reading from the file directly for each request, please first load in the data from excel into a SQL database via java (or something else) then interact with the database from java via JPA (or some other ORM) for each request.

We're more interested in how you code / design rather than correctness of the financial calculations.

To submit please push the code to a **private** github repository and add add all users from the **Github Users** list as collaborators:

**Github Users** to add as collaborators to your private Github repo: alphatheory-dzalika, alphatheory-adahan, alphatheory-ccarneiro


IDeas

PortfolioRecord (as entity?)

Have endpoint "import" to import excel sheet

BigDecimals
docker compose
postgres and spring 
have excel in project, copy into spring image
Maybe have a folder where if anything in it, gets run at startup, otherwise runs only on import from other folder

Data part

group by dates? 

POI - for excel

