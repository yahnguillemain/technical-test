DROP TABLE IF EXISTS EMPLOYEE;
DROP TABLE IF EXISTS DEPARTMENT;
CREATE TABLE DEPARTMENT
(
    ID                    IDENTITY NOT NULL PRIMARY KEY,
    LAST_UPDATE_TIMESTAMP TIMESTAMP,
    NAME                  VARCHAR(50)
);


CREATE TABLE EMPLOYEE
(
    ID                    IDENTITY     NOT NULL PRIMARY KEY,
    LAST_UPDATE_TIMESTAMP TIMESTAMP,
    FIRSTNAME             VARCHAR(100) NOT NULL,
    LASTNAME              VARCHAR(100) NOT NULL,
    GENDER                VARCHAR(6)   NOT NULL,
    JOB_TITLE             VARCHAR(100) NOT NULL,
    CONTRACT_STARTDATE    DATE         NOT NULL,
    CONTRACT_ENDDATE      DATE,
    MANAGER_ID            INT,
    DEPARTMENT_ID         INT
);

ALTER TABLE EMPLOYEE
    ADD FOREIGN KEY (MANAGER_ID)
        REFERENCES EMPLOYEE (ID);

ALTER TABLE EMPLOYEE
    ADD FOREIGN KEY (DEPARTMENT_ID)
        REFERENCES DEPARTMENT (ID);

DROP TABLE IF EXISTS USER_PERMISSION;
DROP TABLE IF EXISTS USERS;

CREATE TABLE USERS
(
    LOGIN      VARCHAR(50) NOT NULL PRIMARY KEY,
    PASSWD     VARCHAR(100),
    BIRTH_DATE DATE,
    ENABLED    BOOLEAN
);



CREATE TABLE USER_PERMISSION
(
    ID         IDENTITY NOT NULL PRIMARY KEY,
    USER_LOGIN VARCHAR(50),
    PERMISSION VARCHAR(50)
);

ALTER TABLE USER_PERMISSION
    ADD FOREIGN KEY (USER_LOGIN) REFERENCES USERS (LOGIN);
