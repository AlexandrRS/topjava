DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

-- password
INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');
-- admin
INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES ('ROLE_USER', 100000);
INSERT INTO user_roles (role, user_id) VALUES ('ROLE_ADMIN', 100001);

INSERT INTO meals (datetime, description, calories,userid)
VALUES ('2015-05-30 10:00:00.000000', 'Завтрак', 500, 100000);
INSERT INTO meals (datetime, description, calories,userid)
VALUES ('2015-05-30 13:00:00.000000', 'Обед', 1000, 100000);
--INSERT INTO meals (datetime, description, calories,userid)
--VALUES ('2015-05-30 20:00:00.000000', 'Ужин', 500, 100000);
--INSERT INTO meals (datetime, description, calories,userid)
--VALUES ('2015-05-31 10:00:00.000000', 'Завтрак', 500, 100000);
--INSERT INTO meals (datetime, description, calories,userid)
--VALUES ('2015-05-31 13:00:00.000000', 'Обед', 1000, 100000);
--INSERT INTO meals (datetime, description, calories,userid)
--VALUES ('2015-05-31 20:00:00.000000', 'Ужин', 510, 100000);
--INSERT INTO meals (datetime, description, calories,userid)
--VALUES ('2015-06-01 14:00:00.000000', 'Админ ланч', 510, 100001);
--INSERT INTO meals (datetime, description, calories,userid)
--VALUES ('2015-06-01 21:00:00.000000', 'Админ ужин', 510, 100001);