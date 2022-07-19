CREATE SCHEMA IF NOT EXISTS afisha_schema
    AUTHORIZATION postgres;

CREATE TABLE IF NOT EXISTS afisha_schema.category
(
    uuid uuid NOT NULL,
    title character varying COLLATE pg_catalog."default" NOT NULL,
    date_create date,
    date_update date,
    CONSTRAINT category_pkey PRIMARY KEY (uuid)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS afisha_schema.category
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS afisha_schema.concerts
(
    uuid uuid NOT NULL,
    title character varying COLLATE pg_catalog."default" NOT NULL,
    description character varying COLLATE pg_catalog."default",
    dt_event bigint,
    dt_end_of_sale bigint,
    type character varying COLLATE pg_catalog."default",
    status character varying COLLATE pg_catalog."default",
    category uuid,
    date_create date,
    date_update date,
    CONSTRAINT uuid PRIMARY KEY (uuid),
    CONSTRAINT fk_category_concerts FOREIGN KEY (category)
    REFERENCES afisha_schema.category (uuid) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS afisha_schema.concerts
    OWNER to postgres;

    TABLESPACE pg_default;

CREATE TABLE IF NOT EXISTS afisha_schema.country
(
    uuid uuid NOT NULL,
    title character varying COLLATE pg_catalog."default" NOT NULL,
    description character varying COLLATE pg_catalog."default",
    date_create date,
    date_update date,
    CONSTRAINT country_pkey PRIMARY KEY (uuid)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS afisha_schema.country
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS afisha_schema.films
(
    uuid uuid NOT NULL,
    title character varying COLLATE pg_catalog."default" NOT NULL,
    description character varying COLLATE pg_catalog."default",
    dt_event bigint,
    dt_end_of_sale bigint,
    type character varying COLLATE pg_catalog."default",
    status character varying COLLATE pg_catalog."default",
    country uuid,
    release_year integer,
    release_date date,
    duration integer,
    date_create date,
    date_update date,
    CONSTRAINT films_pkey PRIMARY KEY (uuid),
    CONSTRAINT fk_country_films FOREIGN KEY (country)
    REFERENCES afisha_schema.country (uuid) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS afisha_schema.films
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS afisha_schema.users
(
    uuid uuid NOT NULL,
    mail character varying COLLATE pg_catalog."default" NOT NULL,
    nick character varying COLLATE pg_catalog."default" NOT NULL,
    role character varying COLLATE pg_catalog."default" NOT NULL,
    status character varying COLLATE pg_catalog."default" NOT NULL,
    password character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (uuid)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS afisha_schema.users
    OWNER to postgres;

