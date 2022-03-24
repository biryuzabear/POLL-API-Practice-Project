INSERT INTO users (name, password, enabled)
values ('admin',
        '$2a$10$ycchOA5goOMKPDi4DtX27uXZifL2HuYP7BM3mUr5TSMqkQ9nNZ2.C',
        1);

INSERT INTO authorities (name, authority)
values ('admin', 'ROLE_ADMIN');

insert into polls (name, start, end, description)
values ('BFF Poll', '2022-03-21', '2022-04-21', 'This poll help to understand who is your really best friend');

insert into polls (name, start, end, description)
values ('Ended poll', '2022-03-20', '2022-03-21', 'This poll has been ended');

insert into polls (name, start, end, description)
values ('Whats your name poll?', '2022-03-20', '2022-05-21', 'Answer your name');

insert into questions (text, type, poll_id)
values ('Type your name, please:', 'TEXT', 3);

insert into questions (text, type, poll_id)
values ('What color is your favourite?', 'CHOICE', 1);

insert into options (question_id, text)
values (2, 'Blue');

insert into options (question_id, text)
values (2, 'Red');

insert into options (question_id, text)
values (2, 'Green');

insert into questions (text, type, poll_id)
values ('What do you you like?', 'MULTICHOICE', 1);

insert into options (question_id, text)
values (3, 'Apples');

insert into options (question_id, text)
values (3, 'Bananas');

insert into options (question_id, text)
values (3, 'Potatoes');

insert into questions (text, type, poll_id)
values ('Type your name:', 'TEXT', 1);

insert into answers(user_id, question_id, text)
values (1, 2, 'Blue');

insert into answers(user_id, question_id, text)
values (1, 3, 'Apples, Bananas, Potatoes');

insert into answers(user_id, question_id, text)
values (1, 4, 'Bob');
