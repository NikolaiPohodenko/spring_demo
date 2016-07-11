DROP TABLE IF EXISTS RESPONCES;

CREATE TABLE RESPONCES  ( id        INT             NOT NULL
                        , userId    INT             default NULL
                        , title     VARCHAR(256)    default NULL
                        , body      VARCHAR(2048)   default NULL

                        , PRIMARY KEY (id)
                        );
