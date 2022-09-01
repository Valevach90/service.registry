CREATE TABLE notifications
(
    email  VARCHAR(255),
    code   VARCHAR(255),
    time   TIMESTAMP    NOT NULL,
    status VARCHAR(255) NOT NULL,
    CONSTRAINT pk_notifications PRIMARY KEY (email)
);
