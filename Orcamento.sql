--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.6
-- Dumped by pg_dump version 9.6.6

-- Started on 2018-01-14 18:05:57 -02

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12429)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2168 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 185 (class 1259 OID 16397)
-- Name: rubrica; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE rubrica (
    code integer NOT NULL,
    "previousBalance" double precision,
    "previousDebt" double precision,
    "previousCredit" double precision,
    "currentBalance" double precision,
    "isPercent" boolean,
    "valueChange" double precision,
    year integer NOT NULL,
    month integer NOT NULL,
    description character varying NOT NULL,
    CONSTRAINT "ValidMonth" CHECK (((month >= 1) AND (month <= 12))),
    CONSTRAINT "ValidYear" CHECK ((year > 0))
);


ALTER TABLE rubrica OWNER TO postgres;

--
-- TOC entry 2161 (class 0 OID 16397)
-- Dependencies: 185
-- Data for Name: rubrica; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY rubrica (code, "previousBalance", "previousDebt", "previousCredit", "currentBalance", "isPercent", "valueChange", year, month, description) FROM stdin;
54321	500.29998779296875	200.100006103515625	400.20001220703125	700.530029296875	t	10.3000001907348633	2018	11	Dummy
\.


--
-- TOC entry 2043 (class 2606 OID 16406)
-- Name: rubrica PrimaryKey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY rubrica
    ADD CONSTRAINT "PrimaryKey" PRIMARY KEY (code, month, year);


-- Completed on 2018-01-14 18:05:57 -02

--
-- PostgreSQL database dump complete
--

