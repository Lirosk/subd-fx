DROP SCHEMA IF EXISTS subd;
CREATE SCHEMA subd;

use subd;

-- tables creation

CREATE TABLE branches
(
	branch_id INT NOT NULL AUTO_INCREMENT,
	name TEXT NOT NULL,
    addr TEXT NOT NULL,
    PRIMARY KEY(branch_id)
);

CREATE TABLE promotions
(
	promotion_id INT NOT NULL AUTO_INCREMENT,
	name TEXT NOT NULL,
    descr TEXT NOT NULL,
    PRIMARY KEY(promotion_id)
);

CREATE TABLE subscriptions
(
	subscription_id INT NOT NULL AUTO_INCREMENT,
	name TEXT NOT NULL,
    price INT NOT NULL,
    descr TEXT NOT NULL,
    PRIMARY KEY(subscription_id)
);

CREATE TABLE users
(
	user_id INT NOT NULL AUTO_INCREMENT,
	name TEXT NOT NULL,
    subscription_id INT DEFAULT null,
    employee_id INT,
    password TEXT NOT NULL,
    PRIMARY KEY(user_id),
    FOREIGN KEY(subscription_id) REFERENCES subscriptions(subscription_id)
);

CREATE TABLE debtors
(
	debtor_id INT NOT NULL AUTO_INCREMENT,
	name TEXT NOT NULL,
    addr TEXT NOT NULL,
    PRIMARY KEY(debtor_id),
    FOREIGN KEY(debtor_id) REFERENCES users(user_id)
);

CREATE TABLE books
(
	book_id INT NOT NULL AUTO_INCREMENT,
    title TEXT NOT NULL,
    author TEXT DEFAULT null,
    PRIMARY KEY(book_id)
);

CREATE TABLE employees
(
	employee_id INT NOT NULL AUTO_INCREMENT,
	name TEXT NOT NULL,
    type TEXT NOT NULL,
    branch_id INT NOT NULL,
    user_id INT,
    password TEXT NOT NULL,
    PRIMARY KEY(employee_id),
    FOREIGN KEY(branch_id) REFERENCES branches(branch_id),
    FOREIGN KEY(user_id) REFERENCES users(user_id)
);
ALTER TABLE users ADD CONSTRAINT FOREIGN KEY(employee_id) REFERENCES employees(employee_id);

CREATE TABLE future_goals
(
	future_goal_id INT NOT NULL AUTO_INCREMENT,
	employee_id INT NOT NULL,
    descr TEXT NOT NULL,
    PRIMARY KEY(future_goal_id),
    FOREIGN KEY(employee_id) REFERENCES employees(employee_id)
);

CREATE TABLE logs
(
	log_id INT NOT NULL AUTO_INCREMENT,
	user_id INT NOT NULL,
    time TIMESTAMP NOT NULL,
    descr TEXT NOT NULL,
    PRIMARY KEY(log_id),
    FOREIGN KEY(user_id) REFERENCES users(user_id)
);


-- VALUES insertion
SET FOREIGN_KEY_CHECKS = 1;

-- books
INSERT INTO books(title, author) VALUES
(
	"Python documentation",
    "Python Software Foundation"
);
INSERT INTO books(title, author) VALUES
(
	"How to dumb Earth to .json",
    "Demiurge"
);
INSERT INTO books(title) VALUES
(
	"ymg' mgah'n'ghft mgepmgvulgtlagln orr'ee"
);
INSERT INTO books(title, author) VALUES
(
	"The Lusty Argonian Maid",
    "Crassius Curio"
);

-- branches
INSERT INTO branches(name, addr) VALUES
(
	"Uln throdog r'luhhor ahornah uaaah n'gha",
    "R'lyeh"
);
INSERT INTO branches(name, addr) VALUES
(
	"Gravity staff",
    "Gargantua"
);

-- employees
INSERT INTO employees(name, type, branch_id) VALUES
(
	"God",
    "The Creator",
    1
);
INSERT INTO employees(name, type, branch_id) VALUES
(
	"Tom Cooper",
    "Pilot/Medium",
    2
);

-- future_goals
INSERT INTO future_goals(employee_id, descr) VALUES
(
	1,
    "Apocalypse"
);
INSERT INTO future_goals(employee_id, descr) VALUES
(
	2,
    "Transmit the quantum data from 8:00 through 20:00"
);

-- promotions
INSERT INTO promotions(name, descr) VALUES
(
	"mg orr'ee",
    "nilgh'ri orr'ee ephaiah donated l' throdog r'luhhor"
);
INSERT INTO promotions(name, descr) VALUES
(
	"ph' nog geb",
    "na'ah'ehye candy l' ooboshu'drnn"
);

-- subscriptions
INSERT INTO subscriptions(name, price, descr) VALUES
(
	"Regular",
    0.99,
    "You can be every day here"
);
INSERT INTO subscriptions(name, price, descr) VALUES
(
	"ALL IN",
    99.99,
    "TBM"
);
INSERT INTO subscriptions(name, price, descr) VALUES
(
	"Free WI-FI",
    49.99,
    "Free WI-FI"
);
INSERT INTO subscriptions(name, price, descr) VALUES
(
	"An Ancient Summoning every sunday",
    0,
    "TBM"
);

-- users
INSERT INTO users(name, password) VALUES("God", "God");
INSERT INTO users(name, subscription_id, password) VALUES("Ameba", 1, "Ameba");
INSERT INTO users(name, subscription_id, password) VALUES("Lochne", 3, "Lochne");

-- logs
INSERT INTO logs(user_id, time, descr) VALUES(
	1,
    DATE("00-01-01 00:00:00"),
    "God just signed in"
);
INSERT INTO logs(user_id, time, descr) VALUES(
	1, 
	DATE("00-01-01 00:00:00"), 
	CONCAT(
		"Then God said, "
        "‘Let there be light’; "
        "and there was light. "
		"And God saw the light, ",
        "that it was good; "
        "and God divided the light from the darkness. "
		"God called the light Day, "
        "and the darkness He called Night. "
		"So the evening and the morning were the first day."
	)
);
INSERT INTO logs(user_id, time, descr) VALUES(
	1, 
	DATE("00-01-02 00:00:00"), 
	CONCAT(
		"Then God said, "
        "‘Let there be a firmament in the midst of the waters, "
        "and let it divide the waters from the waters.’ "
        "Thus God made the firmament, "
        "and divided the waters which were under the firmament from the waters which were above the firmament; "
        "and it was so. "
        "And God called the firmament Heaven. "
        "So the evening and the morning were the second day."
	)
);
INSERT INTO logs(user_id, time, descr) VALUES(
	1, 
	DATE("00-01-03 00:00:00"), 
	CONCAT(
		"Then God said, "
        "‘Let the waters under the heavens be gathered together into one place, "
        "and let the dry land appear’; "
        "and it was so. "
        "And God called the dry land Earth, "
        "and the gathering together of the waters He called Seas. "
        "And God saw that it was good. "
        '\n'
        "Then God said, "
        "‘Let the earth bring forth grass, "
        "the herb that yields seed, "
        "and the fruit tree that yields fruit according to its kind, "
        "whose seed is in itself, "
        "on the earth’; "
        "and it was so. "
        "And the earth brought forth grass, "
        "the herb that yields seed according to its kind, "
        "and the tree that yields fruit, "
        "whose seed is in itself according to its kind. "
        "And God saw that it was good. "
        "So the evening and the morning were the third day."
	)
);
INSERT INTO logs(user_id, time, descr) VALUES(
	1, 
	DATE("00-01-04 00:00:00"), 
	CONCAT(
		"Then God said, "
        "‘Let there be lights in the firmament of the heavens to divide the day from the night; "
        "and let them be for signs and seasons, "
        "and for days and years; "
        "and let them be for lights in the firmament of the heavens to give light on the earth’; "
        "and it was so. "
        "Then God made two great lights: "
        "the greater light to rule the day, "
        "and the lesser light to rule the night. "
        "He made the stars also. "
        "God set them in the firmament of the heavens to give light on the earth, "
        "and to rule over the day and over the night, " 
        "and to divide the light from the darkness. "
        "And God saw that it was good. "
        "So the evening and the morning were the fourth day."
	)
);
INSERT INTO logs(user_id, time, descr) VALUES(
	1, 
	DATE("00-01-05 00:00:00"), 
	CONCAT(
		"Then God said, "
        "‘Let the waters abound with an abundance of living creatures, "
        "and let birds fly above the earth across the face of the firmament of the heavens.’ "
        "So God created great sea creatures and every living thing that moves, "
        "with which the waters abounded, "
        "according to their kind, "
        "and every winged bird according to its kind. "
        "And God saw that it was good. "
        "And God blessed them, saying, " 
        "‘Be fruitful and multiply, "
        "and fill the waters in the seas, "
        "and let birds multiply on the earth.’ "
        "So the evening and the morning were the fifth day."
	)
);
INSERT INTO logs(user_id, time, descr) VALUES(
	1, 
	DATE("00-01-06 00:00:00"), 
	CONCAT(
		"Then God said, "
        "‘Let the earth bring forth the living creature according to its kind: "
        "cattle and creeping thing and beast of the earth, "
        "each according to its kind’; "
        "and it was so. "
        "And God made the beast of the earth according to its kind, "
        "cattle according to its kind, "
        "and everything that creeps on the earth according to its kind. "
        "And God saw that it was good."
        '\n'
        "Then God said, "
        "‘Let Us make man in Our image, " 
        "according to Our likeness; "
        "let them have dominion over the fish of the sea, "
        "over the birds of the air, " 
        "and over the cattle, "
        "over all the earth and over every creeping thing that creeps on the earth.’ "
        "So God created man in His own image; "
        "in the image of God He created him; "
        "male and female He created them. "
        "Then God blessed them, "
        "and God said to them, "
        "‘Be fruitful and multiply; "
        "fill the earth and subdue it; "
        "have dominion over the fish of the sea, "
        "over the birds of the air, "
        "and over every living thing that moves on the earth. "
        '\n'
        "And God said, " 
        "‘See, "
        "I have given you every herb that yields seed which is on the face of all the earth, "
        "and every tree whose fruit yields seed; "
        "to you it shall be for food. "
        "Also, to every beast of the earth, to every bird of the air, "
        "and to everything that creeps on the earth, in which there is life, "
        "I have given every green herb for food’; "
        "and it was so. "
        "Then God saw everything that He had made, "
        "and indeed it was very good. "
        "So the evening and the morning were the sixth day."
	)
);
INSERT INTO logs(user_id, time, descr) VALUES(
	1, 
	DATE("00-01-07 00:00:00"), 
	CONCAT(
			"Thus the heavens and the earth, "
            "and all the host of them, " 
            "were finished. "
            "And on the seventh day God ended His work which He had done, "
            "and He rested on the seventh day from all His work which He had done. "
            "Then God blessed the seventh day and sanctified it, "
            "because in it He rested from all His work which God had created and made."
	)
);
INSERT INTO logs(user_id, time, descr) VALUES(
	2, 
	DATE("2021-12-01 21:22:00"),
	"Just signed up"
);
INSERT INTO logs(user_id, time, descr) VALUES(
	3, 
	DATE("2021-12-01 21:24:00"),
	"Just signed up"
);