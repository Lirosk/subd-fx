-- tables creation

use subd;

SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS users_to_employees;
DROP TABLE IF EXISTS employees_to_branches;
DROP TABLE IF EXISTS future_goals_to_employees;
DROP TABLE IF EXISTS books_to_branches;
DROP TABLE IF EXISTS users_to_subscriptions;
DROP TABLE IF EXISTS branches;
DROP TABLE IF EXISTS promotions;
DROP TABLE IF EXISTS subscriptions;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS future_goals;
-- DROP TABLE IF EXISTS logs;

SET FOREIGN_KEY_CHECKS=1;

CREATE TABLE branches
(
    branch_id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    branch_name VARCHAR(20) NOT NULL,
    branch_addr TEXT NOT NULL
);


CREATE TABLE promotions
(
    promotion_id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    promotion_name TEXT NOT NULL,
    promotion_descr TEXT NOT NULL
);


CREATE TABLE subscriptions
(
	subscription_id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
	subscription_name TEXT NOT NULL,
    subscription_descr TEXT NOT NULL,
    subscription_price INTEGER NOT NULL
);


CREATE TABLE users
(
    user_id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user_name VARCHAR(20) NOT NULL UNIQUE
);


CREATE TABLE books
(
    book_id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    book_title TEXT NOT NULL,
    book_author TEXT DEFAULT null
);

CREATE TABLE employees
(
    employee_id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    employee_name VARCHAR(20) NOT NULL UNIQUE,
    employee_type TEXT NOT NULL
);


CREATE TABLE future_goals
(
    future_goal_id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    future_goal_descr TEXT NOT NULL
);


/*
CREATE TABLE logs
(
    log_id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    log_at DATETIME NOT NULL,
    log_descr TEXT NOT NULL
);
*/


CREATE TABLE users_to_subscriptions
(
    user_to_subscription_id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user_id INTEGER NOT NULL UNIQUE,
    subscription_id INTEGER NOT NULL,
    FOREIGN KEY(user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY(subscription_id) REFERENCES subscriptions(subscription_id) ON DELETE CASCADE
);


CREATE TABLE users_to_employees
(
    user_to_employee_id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user_id INTEGER NOT NULL,
    employee_id INTEGER NOT NULL,
    FOREIGN KEY(user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY(employee_id) REFERENCES employees(employee_id) ON DELETE CASCADE
);


CREATE TABLE employees_to_branches
(
    employee_to_branche_id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    employee_id INTEGER NOT NULL,
    branch_id INTEGER NOT NULL,
    FOREIGN KEY(employee_id) REFERENCES employees(employee_id) ON DELETE CASCADE,
    FOREIGN KEY(branch_id) REFERENCES branches(branch_id) ON DELETE CASCADE
);


CREATE TABLE future_goals_to_employees
(
    future_goal_to_employee_id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    future_goal_id INTEGER NOT NULL,
    employee_id INTEGER NOT NULL,
    FOREIGN KEY(future_goal_id) REFERENCES future_goals(future_goal_id) ON DELETE CASCADE,
    FOREIGN KEY(employee_id) REFERENCES employees(employee_id) ON DELETE CASCADE
);


CREATE TABLE books_to_branches
(
    book_to_branch_id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    book_id INTEGER NOT NULL,
    branch_id INTEGER NOT NULL,
    FOREIGN KEY(book_id) REFERENCES books(book_id) ON DELETE CASCADE,
    FOREIGN KEY(branch_id) REFERENCES branches(branch_id) ON DELETE CASCADE
);

-- branches
INSERT INTO branches(branch_name, branch_addr) VALUES
(
	'R''luhhor vulgtmagl',
    'R''lyeh'
);
INSERT INTO branches(branch_name, branch_addr) VALUES
(
	'Gravity staff',
    'Gargantua'
);
INSERT INTO branches(branch_name, branch_addr) VALUES 
(
	'LIBRARYY',
    'localhost:port/shitshitshit'
);



-- books
INSERT INTO books(book_title, book_author) VALUES
(
	'Python documentation',
    'Python Software Foundation'
);
INSERT INTO books(book_title, book_author) VALUES
(
	'How to dumb Earth to .json',
    'Demiurge'
);
INSERT INTO books(book_title) VALUES
(
	'ymg'' mgah''n''ghft mgepmgvulgtlagln orr''ee'
);
INSERT INTO books(book_title, book_author) VALUES
(
	'The Lusty Argonian Maid',
    'Crassius Curio'
);
INSERT INTO books(book_title) VALUES
(
	'TRUTH ABOUT AMERIKANS ON THE MOON!!!'
);



-- books_to_branches
INSERT INTO books_to_branches(book_id, branch_id) VALUES
(
    1, 1
);
INSERT INTO books_to_branches(book_id, branch_id) VALUES
(
    1, 3
);
INSERT INTO books_to_branches(book_id, branch_id) VALUES
(
    2, 1
);
INSERT INTO books_to_branches(book_id, branch_id) VALUES
(
    2, 2
);
INSERT INTO books_to_branches(book_id, branch_id) VALUES
(
    3, 2
);
INSERT INTO books_to_branches(book_id, branch_id) VALUES
(
    4, 1
);
INSERT INTO books_to_branches(book_id, branch_id) VALUES
(
    5, 2
);
INSERT INTO books_to_branches(book_id, branch_id) VALUES
(
    5, 3
);



-- employees
INSERT INTO employees(employee_name, employee_type) VALUES
(
	'God',
    'The Creator'
);
INSERT INTO employees(employee_name, employee_type) VALUES
(
	'Tom Cooper',
    'Pilot/Medium'
);
INSERT INTO employees(employee_name, employee_type) VALUES
(
	'NONAME1',
    'TBM1'
);
INSERT INTO employees(employee_name, employee_type) VALUES
(
	'NONAME2',
    'TBM2'
);



-- employees_to_branches
INSERT INTO employees_to_branches(employee_id, branch_id) VALUES
(
    1, 1
);
INSERT INTO employees_to_branches(employee_id, branch_id) VALUES
(
    2, 2
);
INSERT INTO employees_to_branches(employee_id, branch_id) VALUES
(
    3, 1
);
INSERT INTO employees_to_branches(employee_id, branch_id) VALUES
(
    4, 2
);



-- future_goals
INSERT INTO future_goals(future_goal_descr) VALUES
(
    'Apocalypse'
);
INSERT INTO future_goals(future_goal_descr) VALUES
(
    'Transmit the quantum data from 8:00 through 20:00'
);



-- future_goals_to_employees
INSERT INTO future_goals_to_employees(future_goal_id, employee_id) VALUES
(
    1, 1
);
INSERT INTO future_goals_to_employees(future_goal_id, employee_id) VALUES
(
    2, 2
);



-- promotions
INSERT INTO promotions(promotion_name, promotion_descr) VALUES
(
	'mg orr''ee',
    'nilgh''ri orr''ee ephaiah donated l'' throdog r''luhhor'
);
INSERT INTO promotions(promotion_name, promotion_descr) VALUES
(
	'ph'' nog geb',
    'na''ah''ehye candy l'' ooboshu''drnn'
);



-- subscriptions
INSERT INTO subscriptions(subscription_name, subscription_price, subscription_descr) VALUES
(
	'Regular',
    0.99,
    'You can be every day here'
);
INSERT INTO subscriptions(subscription_name, subscription_price, subscription_descr) VALUES
(
	'ALL IN',
    99.99,
    'TBM'
);
INSERT INTO subscriptions(subscription_name, subscription_price, subscription_descr) VALUES
(
	'Free WI-FI',
    49.99,
    'Free WI-FI'
);
INSERT INTO subscriptions(subscription_name, subscription_price, subscription_descr) VALUES
(
	'An Ancient Summoning every sunday',
    0,
    'TBM'
);



-- users
INSERT INTO users(user_name) VALUES('God');
INSERT INTO users(user_name) VALUES('Ameba');
INSERT INTO users(user_name) VALUES('Lochne');



-- users_to_employees
INSERT INTO users_to_employees(user_id, employee_id) VALUES
(
	1, 1
);



-- users_to_subscriptions
INSERT INTO users_to_subscriptions(user_id, subscription_id) VALUES
(
    2, 1
);
INSERT INTO users_to_subscriptions(user_id, subscription_id) VALUES
(
    3, 3
);


/*
-- logs
INSERT INTO logs(log_at, log_descr) VALUES(
    '0000-01-01 00:00:00',
    'God just signed in'
);
INSERT INTO logs(log_at, log_descr) VALUES(
	'0000-01-01 00:00:00',
    CONCAT(
		'Then God said, ',
        '‘Let there be light’; ',
        'and there was light. ',
		'And God saw the light, ',
        'that it was good; ',
        'and God divided the light from the darkness. ',
		'God called the light Day, ',
        'and the darkness He called Night. ',
		'So the evening and the morning were the first day.'
        )
);
INSERT INTO logs(log_at, log_descr) VALUES(
	'0000-01-02 00:00:00',
    CONCAT(
		'Then God said, ',
        '‘Let there be a firmament in the midst of the waters, ',
        'and let it divide the waters from the waters.’ ',
        'Thus God made the firmament, ',
        'and divided the waters which were under the firmament from the waters which were above the firmament; ',
        'and it was so. ',
        'And God called the firmament Heaven. ',
        'So the evening and the morning were the second day.'
        )
);
INSERT INTO logs(log_at, log_descr) VALUES(
	'0000-01-03 00:00:00',
    CONCAT(
		'Then God said, ',
        '‘Let the waters under the heavens be gathered together into one place, ',
        'and let the dry land appear’; ',
        'and it was so. ',
        'And God called the dry land Earth, ',
        'and the gathering together of the waters He called Seas. ',
        'And God saw that it was good. ',
        '\n',
        'Then God said, ',
        '‘Let the earth bring forth grass, ',
        'the herb that yields seed, ',
        'and the fruit tree that yields fruit according to its kind, ',
        'whose seed is in itself, ',
        'on the earth’; ',
        'and it was so. ',
        'And the earth brought forth grass, ',
        'the herb that yields seed according to its kind, ',
        'and the tree that yields fruit, ',
        'whose seed is in itself according to its kind. ',
        'And God saw that it was good. ',
        'So the evening and the morning were the third day.'
        )
);
INSERT INTO logs(log_at, log_descr) VALUES(
	'0000-01-04 00:00:00',
    CONCAT(
		'Then God said, ',
        '‘Let there be lights in the firmament of the heavens to divide the day from the night; ',
        'and let them be for signs and seasons, ',
        'and for days and years; ',
        'and let them be for lights in the firmament of the heavens to give light on the earth’; ',
        'and it was so. ',
        'Then God made two great lights: ',
        'the greater light to rule the day, ',
        'and the lesser light to rule the night. ',
        'He made the stars also. ',
        'God set them in the firmament of the heavens to give light on the earth, ',
        'and to rule over the day and over the night, ',
        'and to divide the light from the darkness. ',
        'And God saw that it was good. ',
        'So the evening and the morning were the fourth day.'
        )
);
INSERT INTO logs(log_at, log_descr) VALUES(
	'0000-01-05 00:00:00',
    CONCAT(
		'Then God said, ',
        '‘Let the waters abound with an abundance of living creatures, ',
        'and let birds fly above the earth across the face of the firmament of the heavens.’ ',
        'So God created great sea creatures and every living thing that moves, ',
        'with which the waters abounded, ',
        'according to their kind, ',
        'and every winged bird according to its kind. ',
        'And God saw that it was good. ',
        'And God blessed them, saying, ',
        '‘Be fruitful and multiply, ',
        'and fill the waters in the seas, ',
        'and let birds multiply on the earth.’ ',
        'So the evening and the morning were the fifth day.'
        )
);
INSERT INTO logs(log_at, log_descr) VALUES(
	'0000-01-06 00:00:00',
    CONCAT(
		'Then God said, ',
        '‘Let the earth bring forth the living creature according to its kind: ',
        'cattle and creeping thing and beast of the earth, ',
        'each according to its kind’; ',
        'and it was so. ',
        'And God made the beast of the earth according to its kind, ',
        'cattle according to its kind, ',
        'and everything that creeps on the earth according to its kind. ',
        'And God saw that it was good.',
        '\n',
        'Then God said, ',
        '‘Let Us make man in Our image, ',
        'according to Our likeness; ',
        'let them have dominion over the fish of the sea, ',
        'over the birds of the air, ',
        'and over the cattle, ',
        'over all the earth and over every creeping thing that creeps on the earth.’ ',
        'So God created man in His own image; ',
        'in the image of God He created him; ',
        'male and female He created them. ',
        'Then God blessed them, ',
        'and God said to them, ',
        '‘Be fruitful and multiply; ',
        'fill the earth and subdue it; ',
        'have dominion over the fish of the sea, ',
        'over the birds of the air, ',
        'and over every living thing that moves on the earth. ',
        '\n',
        'And God said, ',
        '‘See, ',
        'I have given you every herb that yields seed which is on the face of all the earth, ',
        'and every tree whose fruit yields seed; ',
        'to you it shall be for food. ',
        'Also, to every beast of the earth, to every bird of the air, ',
        'and to everything that creeps on the earth, in which there is life, ',
        'I have given every green herb for food’; ',
        'and it was so. ',
        'Then God saw everything that He had made, ',
        'and indeed it was very good. ',
        'So the evening and the morning were the sixth day.'
        )
);
INSERT INTO logs(log_at, log_descr) VALUES(
	'0000-01-07 00:00:00',
    CONCAT(
             'Thus the heavens and the earth, ',
            'and all the host of them, ',
            'were finished. ',
            'And on the seventh day God ended His work which He had done, ',
            'and He rested on the seventh day from all His work which He had done. ',
            'Then God blessed the seventh day and sanctified it, ',
            'because in it He rested from all His work which God had created and made.'
            )
);
INSERT INTO logs(log_at, log_descr) VALUES(
	'2021-12-01 21:22:00',
	'Ameba just signed up'
);
INSERT INTO logs(log_at, log_descr) VALUES(
	'2021-12-01 21:24:00',
	'Lochne just signed up'
);
*/
