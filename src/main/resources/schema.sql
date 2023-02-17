DROP SCHEMA IF EXISTS "memcache" CASCADE;

CREATE SCHEMA "memcache";

DROP TABLE IF EXISTS "memcache".task CASCADE;

CREATE TABLE "memcache".task
(
    id int NOT NULL,
    name character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT task_pk PRIMARY KEY (id)
);

