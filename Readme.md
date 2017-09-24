# Multi entity managers


As a fork, this very useful repo: https://github.com/snicoll-demos/demo-multi-entity-managers was extended to
support 2 different databases (MySql + PostgreSQL).
In other words, this is a demo how you can use a PostgreSQL and a MySQL datasource in your application in parallel. Even
if this might not be 100% compatible with latest fancy programming paradigms, the use case might occur anyway...
 
To prove the concept, 2 shell scripts where added which create docker containters to directly test the application.


## How To use/test it:
1. Clone this repository
2. Run the .sh files in the _docker_ folder to create the docker containers which host databases initialized with basic
data:
    
        ./runMysql.sh   
        ./runPostgres.sh
    
3. Start the application by running the main class _DemoMultiEntityManagers_.
4. To check the outcome of the program run, the content of both databases can be shown by one single command:

        docker exec -it em_postgres psql -U postgres -d testdb -c "select * from db;" && \
        docker exec -it em_mysql mysql -ppassword testdb -e "select * from db;"
    
    Both databases should now have an additional key with a date of _step 3_'s execution.

