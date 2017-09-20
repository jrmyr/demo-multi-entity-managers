#!/usr/bin/env bash

# Script file to be executed in the container

SQL_SCRIPT=mysql.sql
#echo "create table db (name text);" > $SQL_SCRIPT
#echo "insert into db (name) values ('domain');" >> $SQL_SCRIPT
#chmod 777 $SQL_SCRIPT

echo "create table db (id integer NOT NULL AUTO_INCREMENT, name text, primary key (id));" > $SQL_SCRIPT
echo "insert into db (id, name) values (1, 'mysql');" >> $SQL_SCRIPT
# Hibernate needs a sequence to get the PKs from
#echo "CREATE SEQUENCE hibernate_sequence INCREMENT 1 MINVALUE 10 MAXVALUE 9223372036854775807 START 1 CACHE 1;" >> $SQL_SCRIPT
chmod 777 $SQL_SCRIPT


# Create the container on the base of postgres 9.6 using our pre-defined SQL files, expose a port and detach from it;
docker run --name em_mysql \
    --volume $(pwd)/$SQL_SCRIPT:/docker-entrypoint-initdb.d/$SQL_SCRIPT \
    --publish 33069:3306 \
    --env MYSQL_USER=testuser --env MYSQL_PASSWORD=password --env MYSQL_DATABASE=testdb --env MYSQL_ROOT_PASSWORD=password \
    --detach \
    mysql:5.6
