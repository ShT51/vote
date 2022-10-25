INSERT INTO role (id, name)
VALUES (1, 'ROLE_USER'),
       (2, 'ROLE_ADMIN');

INSERT INTO users(name, email, password)
VALUES ('Sam', 'sam@ring.com', '$2a$10$w3CpKTFr9HcacOWppdeO.OqAngpIPE9PM1DtlY3c1GKvVfhL0zo0m'),
       ('Frodo', 'frodo@ring.com', '$2a$10$1VawGVsKK6dff3ru6HYK8.iMM3TPdlZR6BJERG2KUVcrlJTrrItOK'),
       ('Pipin', 'pipin@ring.com', '$2a$10$ZdlMK9.IYiMZ3NCWSJKHCeNPMItqOipDOkDj8US6010E/6.7geGRS'),
       ('Bilbo', 'bilbo@ring.com', '$2a$10$ZdlMK9.IYiMZ3NCWSJKHCeNPMItqOipDOkDj8US6010E/6.7geGRS');

INSERT INTO user_role(users_id, role_id)
VALUES (1, 1),
       (2, 2),
       (3, 1),
       (4, 1);

INSERT INTO restaurant(name)
VALUES ('KFC'),
       ('McDuck'),
       ('BG');

INSERT INTO vote(vote_date, restaurant_id, users_id)
VALUES (CURRENT_DATE, 1, 1),
       (CURRENT_DATE, 2, 2),
       (CURRENT_DATE, 2, 3),
       (CURRENT_DATE, 2, 4);

INSERT INTO dish(name, price)
VALUES ('Basket', 9.99),
       ('Drumstick', 4.99),
       ('BigMack', 2.99),
       ('BigTasty', 5.99),
       ('Royal-Hamburger', 4.99),
       ('Coca', 0.99);

INSERT INTO restaurant_dish(restaurant_id, dish_id)
VALUES (1, 1),
       (1, 2),
       (2, 3),
       (2, 4),
       (3, 5);
