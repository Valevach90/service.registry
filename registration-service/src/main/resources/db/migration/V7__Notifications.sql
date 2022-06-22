CREATE TABLE notifications
(
    email      VARCHAR(255),
    code       VARCHAR(255)                            NOT NULL,
    time       TIMESTAMP                               NOT NULL,
    CONSTRAINT pk_notifications PRIMARY KEY (email)
);
