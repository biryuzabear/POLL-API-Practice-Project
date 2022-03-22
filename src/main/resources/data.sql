INSERT INTO users (name, password, enabled)
values ('user',
        '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a',
        1);

INSERT INTO authorities (name, authority)
values ('user', 'ROLE_USER');

insert into polls (name, start, end, description)
values ('BFF Poll', CURDATE(), '2022-04-21', 'This poll help to understand what winx witch are you');

insert into polls (name, start, end, description)
values ('Ended poll', '2022-03-20', '2022-03-21', 'This poll has been ended');

insert into questions (text, type, poll_id)
values ('What color is your favourite?', 'CHOICE', 1);

insert into options (question_id, text)
values (1, 'Blue');

insert into options (question_id, text)
values (1, 'Red');

insert into options (question_id, text)
values (1, 'Green');

insert into questions (text, type, poll_id)
values ('What do you you like?', 'MULTICHOICE', 1);

insert into options (question_id, text)
values (2, 'Apples');

insert into options (question_id, text)
values (2, 'Bananas');

insert into options (question_id, text)
values (2, 'Potatoes');

insert into questions (text, type, poll_id)
values ('Type your name:', 'TEXT', 1);

insert into answers(user_id, question_id, text)
values (1, 1, 'Blue');

insert into answers(user_id, question_id, text)
values (1, 2, 'Apples, Bananas, Potatoes');

insert into answers(user_id, question_id, text)
values (1, 3, 'Bob');
