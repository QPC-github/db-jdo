-- SchemaType: datastore identity orm

connect 'jdbc:derby:jdotckdb;create=true' user 'tckuser' password 'tckuser';

CREATE SCHEMA datastoreidentity_orm;
SET SCHEMA datastoreidentity_orm;

-------------------------
-- mylib
-------------------------

DROP TABLE PCRect;
DROP TABLE PCPoint;

CREATE TABLE PCPoint (
    DATASTORE_IDENTITY BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
    ID BIGINT,
    X INTEGER NOT NULL,
    Y INTEGER,
    CONSTRAINT PCPNT_CONST PRIMARY KEY (DATASTORE_IDENTITY)
);

CREATE TABLE PCRect (
    DATASTORE_IDENTITY BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
    ID BIGINT,
    UPPER_LEFT BIGINT REFERENCES PCPoint NOT NULL,
    LOWER_RIGHT BIGINT REFERENCES PCPoint NOT NULL,
    CONSTRAINT PCRCT_CONST PRIMARY KEY (DATASTORE_IDENTITY)
);
