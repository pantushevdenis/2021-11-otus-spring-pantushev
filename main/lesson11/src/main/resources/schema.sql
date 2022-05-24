DROP TABLE IF EXISTS JENRES;
CREATE TABLE JENRES(
    ID BIGINT
    , Name VARCHAR(255) NOT NULL
    , PRIMARY KEY (ID)
    );

DROP TABLE IF EXISTS AUTHORS;
CREATE TABLE AUTHORS(
    ID BIGINT
    , Name VARCHAR(255) NOT NULL
    , PRIMARY KEY (ID)
    );

DROP TABLE IF EXISTS BOOKS;
CREATE TABLE BOOKS(
    ID BIGINT IDENTITY
    , Name VARCHAR(255)
    , AuthorID BIGINT NOT NULL
    , JenreID BIGINT NOT NULL
    , PublishingYear INT
    , PRIMARY KEY (ID)
    , FOREIGN KEY (AuthorID) REFERENCES AUTHORS(ID)
    , FOREIGN KEY (JenreID) REFERENCES JENRES(ID)
    );

DROP TABLE IF EXISTS COMMENTS;
CREATE TABLE COMMENTS(
    ID BIGINT IDENTITY
    , Content VARCHAR(255) NOT NULL
    , BookID BIGINT NOT NULL
    , PRIMARY KEY (ID)
    , FOREIGN KEY (BookID) REFERENCES BOOKS(ID) ON UPDATE CASCADE ON DELETE CASCADE
);