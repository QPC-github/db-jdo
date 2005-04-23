-- SchemaType: datastore-identity strategy="identity"

connect 'jdbc:derby:jdotckdb;create=true' user 'tckuser' password 'tckuser';

-------------------------
-- mylib
-------------------------

DROP TABLE PCPoint;
DROP TABLE PCPoint2;
DROP TABLE PCRect;
DROP TABLE PrimitiveTypes;

CREATE TABLE PCPoint (
    ID BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
    X INTEGER NOT NULL,
    Y INTEGER NOT NULL,
    CONSTRAINT PCPNT_CONST PRIMARY KEY (ID)
);

CREATE TABLE PCPoint2 (
    ID BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
    X INTEGER NOT NULL,
    Y INTEGER NOT NULL,
    CONSTRAINT PCPNT2_CONST PRIMARY KEY (ID)
);

CREATE TABLE PCRect (
    ID BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
    UPPER_LEFT BIGINT NOT NULL,
    LOWER_RIGHT BIGINT NOT NULL,
    CONSTRAINT PCRCT_CONST PRIMARY KEY (ID)
);

CREATE TABLE PrimitiveTypes (
    ID BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
    booleanNotNull CHAR FOR BIT DATA NOT NULL,
    booleanNull CHAR FOR BIT DATA NOT NULL,
    byteNotNull SMALLINT NOT NULL,
    byteNull SMALLINT NOT NULL,
    shortNotNull SMALLINT NOT NULL,
    shortNull SMALLINT NOT NULL,
    intNotNull INTEGER NOT NULL,
    intNull INTEGER NOT NULL,
    longNotNull INTEGER NOT NULL,
    longNull INTEGER NOT NULL,
    floatNotNull FLOAT NOT NULL,
    floatNull FLOAT NOT NULL,
    doubleNotNull FLOAT NOT NULL,
    doubleNull FLOAT NOT NULL,
    charNotNull CHAR NOT NULL,
    charNull CHAR NOT NULL,
    dateNull DATE NOT NULL,
    stringNull VARCHAR (256) NOT NULL,
    bigDecimal DECIMAL NOT NULL,
    bigInteger INTEGER NOT NULL,
    PrimitiveTypes INTEGER NOT NULL,
    CONSTRAINT PCPNT_PT PRIMARY KEY (ID)
);

-------------------------
-- company
-------------------------

DROP TABLE insuranceplans;
DROP TABLE project_reviewer;
DROP TABLE project_member;
DROP TABLE employee_phoneno_type;
DROP TABLE persons;
DROP TABLE projects;
DROP TABLE departments;
DROP TABLE companies;

CREATE TABLE companies (
    ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
    NAME VARCHAR(32) NOT NULL,
    FOUNDEDDATE VARCHAR(32) NOT NULL,
    STREET VARCHAR(64),
    CITY VARCHAR(64),
    STATE CHAR(2),
    ZIPCODE CHAR(5),
    COUNTRY VARCHAR(64),
    CONSTRAINT COMPS_PK PRIMARY KEY (ID)
);

CREATE TABLE departments (
    ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
    NAME VARCHAR(32) NOT NULL,
    EMP_OF_THE_MONTH INTEGER,
    COMPANYID INTEGER REFERENCES companies,
    CONSTRAINT DEPTS_PK PRIMARY KEY (ID)
);

CREATE TABLE persons (
    PERSONID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
    FIRSTNAME VARCHAR(32) NOT NULL,
    LASTNAME VARCHAR(32) NOT NULL,
    MIDDLENAME VARCHAR(32),
    BIRTHDATE VARCHAR(32) NOT NULL,
    STREET VARCHAR(64),
    CITY VARCHAR(64),
    STATE CHAR(2),
    ZIPCODE CHAR(5),
    COUNTRY VARCHAR(64),
    HIREDATE VARCHAR(32) NOT NULL,
    WEEKLYHOURS FLOAT NOT NULL,
    DEPARTMENT INTEGER REFERENCES departments NOT NULL,
    FUNDINGdEPT INTEGER REFERENCES departments NOT NULL,
    MANAGER INTEGER REFERENCES persons NOT NULL,
    MENTOR INTEGER REFERENCES persons NOT NULL,
    HRADVISOR INTEGER REFERENCES persons NOT NULL,
    SALARY FLOAT NOT NULL,
    WAGE FLOAT NOT NULL,
    DISCRIMINATOR varchar(64) NOT NULL,
    CONSTRAINT EMPS_PK PRIMARY KEY (PERSONID)
);

CREATE TABLE insuranceplans (
    INSID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
    CARRIER VARCHAR(64) NOT NULL,
    LIFETIME_ORTHO_BENEFIT DECIMAL NOT NULL,
    PLANtYPE VARCHAR(8) NOT NULL,
    DISCRIMINATOR VARCHAR(64) NOT NULL,
    EMPLOYEE INTEGER REFERENCES persons,
    CONSTRAINT INS_PK PRIMARY KEY (INSID)
);

CREATE TABLE projects (
    PROJID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
    NAME VARCHAR(32) NOT NULL,
    BUDGET DECIMAL NOT NULL,
    CONSTRAINT PROJS_PK PRIMARY KEY (PROJID)
);

CREATE TABLE project_reviewer (
    projid INTEGER REFERENCES projects NOT NULL,
    reviewer INTEGER REFERENCES persons NOT NULL
);

CREATE TABLE project_member (
    projid INTEGER REFERENCES projects NOT NULL,
    member INTEGER REFERENCES persons NOT NULL
);

CREATE TABLE employee_phoneno_type (
    empid INTEGER REFERENCES persons NOT NULL,
    phoneno VARCHAR(16) NOT NULL,
    type VARCHAR(16) NOT NULL
);

ALTER TABLE departments 
    ADD CONSTRAINT EMP_MO_FK FOREIGN KEY
        (EMP_OF_THE_MONTH) REFERENCES persons(PERSONID)
;

-------------------------
--fieldtypes
-------------------------

DROP TABLE FieldsOfByte;

CREATE TABLE FieldsOfByte (
    IDENTIFIER INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
    BYTE0 SMALLINT,
    BYTE1 SMALLINT,
    BYTE2 SMALLINT,
    BYTE4 SMALLINT,
    BYTE5 SMALLINT,
    BYTE6 SMALLINT,
    BYTE11 SMALLINT,
    BYTE12 SMALLINT,
    BYTE13 SMALLINT,
    BYTE16 SMALLINT,
    BYTE17 SMALLINT,
    BYTE18 SMALLINT,
    BYTE20 SMALLINT,
    BYTE21 SMALLINT,
    BYTE22 SMALLINT,
    BYTE30 SMALLINT,
    BYTE31 SMALLINT,
    BYTE32 SMALLINT,
    BYTE36 SMALLINT,
    BYTE37 SMALLINT,
    BYTE38 SMALLINT,
    BYTE40 SMALLINT,
    BYTE41 SMALLINT,
    BYTE42 SMALLINT,
    BYTE43 SMALLINT,
    BYTE47 SMALLINT,
    BYTE48 SMALLINT,
    BYTE49 SMALLINT,
    BYTE52 SMALLINT,
    BYTE53 SMALLINT,
    BYTE54 SMALLINT,
    BYTE56 SMALLINT,
    BYTE57 SMALLINT,
    BYTE58 SMALLINT,
    BYTE66 SMALLINT,
    BYTE67 SMALLINT,
    BYTE68 SMALLINT,
    BYTE72 SMALLINT,
    BYTE73 SMALLINT,
    BYTE74 SMALLINT,
    BYTE76 SMALLINT,
    BYTE77 SMALLINT,
    BYTE78 SMALLINT,
    BYTE83 SMALLINT,
    BYTE84 SMALLINT,
    BYTE85 SMALLINT,
    BYTE88 SMALLINT,
    BYTE89 SMALLINT,
    BYTE90 SMALLINT,
    BYTE92 SMALLINT,
    BYTE93 SMALLINT,
    BYTE94 SMALLINT,
    BYTE102 SMALLINT,
    BYTE103 SMALLINT,
    BYTE104 SMALLINT,
    BYTE108 SMALLINT,
    BYTE109 SMALLINT,
    BYTE110 SMALLINT,
    BYTE112 SMALLINT,
    BYTE113 SMALLINT,
    BYTE114 SMALLINT,
    BYTE119 SMALLINT,
    BYTE120 SMALLINT,
    BYTE121 SMALLINT,
    BYTE124 SMALLINT,
    BYTE125 SMALLINT,
    BYTE126 SMALLINT,
    BYTE128 SMALLINT,
    BYTE129 SMALLINT,
    BYTE130 SMALLINT,
    BYTE138 SMALLINT,
    BYTE139 SMALLINT,
    BYTE140 SMALLINT,
    CONSTRAINT AllTypes_PK PRIMARY KEY (IDENTIFIER)
);

-------------------------
--inheritance
-------------------------

DROP TABLE AllPersist;
DROP TABLE TopNonPersistB;
DROP TABLE TopPersist;
DROP TABLE FieldSameName;

CREATE TABLE AllPersist (
    KEY_VALUE INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
    DOUBLE_B DOUBLE,
    INT_B INTEGER,
    FLOAT_E FLOAT,
    SHORT_F SMALLINT,
    SECOND_OBJ INTEGER,
    THIRD_OBJ INTEGER,
    INT_H INTEGER,
    DISCRIMINATOR varchar(64) NOT NULL,
    CONSTRAINT AllPersist_PK PRIMARY KEY (KEY_VALUE),
    FOREIGN KEY (SECOND_OBJ) REFERENCES AllPersist (KEY_VALUE),
    FOREIGN KEY (THIRD_OBJ) REFERENCES AllPersist (KEY_VALUE)
);

CREATE TABLE TopNonPersistB (
    KEY_VALUE INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
    DOUBLE_B DOUBLE,
    INT_B INTEGER,
    FLOAT_E FLOAT,
    SHORT_F SMALLINT,
    SECOND_OBJ INTEGER,
    THIRD_OBJ INTEGER,
    INT_H INTEGER,
    DISCRIMINATOR varchar(64) NOT NULL,
    CONSTRAINT TopNonPersistB_PK PRIMARY KEY (KEY_VALUE),
    FOREIGN KEY (SECOND_OBJ) REFERENCES TopNonPersistB (KEY_VALUE),
    FOREIGN KEY (THIRD_OBJ) REFERENCES TopNonPersistB (KEY_VALUE)
);

CREATE TABLE TopPersist (
    KEY_VALUE INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
    DOUBLE_B DOUBLE,
    INT_B INTEGER,
    FLOAT_E FLOAT,
    SHORT_F SMALLINT,
    SECOND_OBJ INTEGER,
    THIRD_OBJ INTEGER,
    INT_H INTEGER,
    DISCRIMINATOR varchar(64) NOT NULL,
    CONSTRAINT TopPersist_PK PRIMARY KEY (KEY_VALUE),
    FOREIGN KEY (SECOND_OBJ) REFERENCES TopPersist (KEY_VALUE),
    FOREIGN KEY (THIRD_OBJ) REFERENCES TopPersist (KEY_VALUE)
);

CREATE TABLE FieldSameName (
    KEY_VALUE INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
    N2 DOUBLE,
    N3 INTEGER,
    FSN2_N3 FLOAT,
    FSN3_N1 SMALLINT,
    FSN3_N2 INTEGER,
    FSN3_N3 INTEGER,
    FSN4_N3 INTEGER,
    DISCRIMINATOR varchar(64) NOT NULL,
    CONSTRAINT FieldSameName_PK PRIMARY KEY (KEY_VALUE),
    FOREIGN KEY (FSN3_N2) REFERENCES FieldSameName (KEY_VALUE),
    FOREIGN KEY (FSN3_N3) REFERENCES FieldSameName (KEY_VALUE)
);

-------------------------
--instancecallbacks
-------------------------

DROP TABLE ICNonPersistFds;
DROP TABLE InstanceCallbacks;

CREATE TABLE ICNonPersistFds (
    ICKEY INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
    D DOUBLE,
    S SMALLINT,
    FLOATVAL FLOAT,
    INTVAL INTEGER,
    CONSTRAINT ICNP_PK PRIMARY KEY (ICKEY)
);

CREATE TABLE InstanceCallbacks (
    ICKEY INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
    NAME VARCHAR(64),
    TIMEDATE TIMESTAMP,
    NEXTOBJ INTEGER,
    CHILD INTEGER,
    INTVAL INTEGER,
    DOUBLEVAL DOUBLE,
    CHILD2DEL INTEGER,
    CHARVAL CHAR(254),
    CONSTRAINT IC_PK PRIMARY KEY (ICKEY)
);

-------------------------
--lifecycle
-------------------------

disconnect;
