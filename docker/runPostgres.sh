#!/usr/bin/env bash

# Script file to be executed in the container
SQL_SCRIPT=postgres.sql
echo "create table db (id integer primary key, name varchar);" > $SQL_SCRIPT
echo "create sequence hibernate_sequence increment 1 minvalue 1 maxvalue 9223372036854775807 start 1 cache 1;" >> $SQL_SCRIPT
echo "insert into db (id, name) values (nextval('hibernate_sequence'), 'postgres');" >> $SQL_SCRIPT
# Hibernate needs a sequence to get the PKs from

chmod 777 $SQL_SCRIPT

# Create the container on the base of postgres 9.6 using our pre-defined SQL files, expose a port and detach from it;
docker run --name em_postgres \
    --volume $(pwd)/$SQL_SCRIPT:/docker-entrypoint-initdb.d/$SQL_SCRIPT \
    --publish 54329:5432 \
    --env POSTGRES_USER=testuser --env POSTGRES_PASSWORD=password --env POSTGRES_DB=testdb \
    --detach \
    postgres:9.6
