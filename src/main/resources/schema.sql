CREATE TABLE authorities (
                             name VARCHAR(50) NOT NULL,
                             authority VARCHAR(50) NOT NULL,
                             FOREIGN KEY (name) REFERENCES users(name)
);

CREATE UNIQUE INDEX ix_auth_username
    on authorities (name,authority);