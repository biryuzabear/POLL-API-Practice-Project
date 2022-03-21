INSERT INTO users (name, password, enabled)
values ('user',
        '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a',
        1);

INSERT INTO authorities (name, authority)
values ('user', 'ROLE_USER');