CREATE ROLE admin WITH
    LOGIN
    SUPERUSER
    CREATEDB
    CREATEROLE
    INHERIT
    REPLICATION
    CONNECTION LIMIT -1
    PASSWORD '1234';

DROP DATABASE patika;

CREATE DATABASE patika
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    TABLESPACE = pg_default;

CREATE SCHEMA IF NOT EXISTS patikaSchema AUTHORIZATION root;

SET search_path TO patikaSchema;

CREATE TABLE coordinates (
    cityName varchar(100) PRIMARY KEY NOT NULL,
    lat double precision NOT NULL,
    lon double precision NOT NULL
);