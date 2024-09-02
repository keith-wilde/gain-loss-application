(Keep general plan/notes in here)

CSV, not excel
2. Get importing of csv into dto objects working.
(Probably use POI library?)
 - Get file import converted into java objects 1st
 - Import on application start or through endpoint? 
   - To start with, check if table empty before doing import on app startup?

3.   entities, repos, do real service impl - include basic unit tests? 

4. Implement individual repo methods based on requirements
 - maybe need native queries for some cases?

5. Extras if have time - 
tests that import their own data? - example of real database connection is staging config? 
endpoint that does import 
Use docker compose with spring and postgres containers. 

   

**Questions**
h2 vs postgres? , h2 for now to get developed
automatically create tables (h2 ) or do a setup sql file to create initial tables (schema.sql)? 

