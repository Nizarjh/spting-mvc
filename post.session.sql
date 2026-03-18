
CREATE TABLE person (
    id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    name character varying(250) UNIQUE NOT NULL,
    age integer NOT NULL CHECK (age > 6)
);

INSERT INTO person (name, age) VALUES
('Иванов Иван Иванович', 54),
('Петров Пётр Петрович', 64),
('Алексеев Алексей Алексеевич', 35),
('Познер Владимир Владимирович', 79),
('Фёдоров Мирон Янович', 38);

CREATE TABLE book (
    bookid uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    ownerid uuid REFERENCES person(id) ON DELETE SET NULL,
    title character varying(100) NOT NULL,
    author character varying(250) NOT NULL,
    publishdate timestamp without time zone NOT NULL
);

INSERT INTO book (ownerid, title, author, publishdate) VALUES
(NULL, 'Над пропастью во ржи', 'Джером Сэлинджер', '1951-07-16'),
(NULL, 'День опричника', 'Владимир Сорокин', '2006-01-01'),
(NULL, 'Тайные виды на гору Фудзи', 'Владимир Пелевин', '2018-03-15'),
(NULL, 'Философия Java', 'Брюс Эккель', '2018-05-01'),
(NULL, 'Психопатология обыденной жизни', 'Зигмунд Фрейд', '1904-01-01'),
(NULL, 'Игра в бисер', 'Герман Гессе', '1943-01-01'),
(NULL, 'Бытие и время', 'Мартин Хайдеггер', '1927-01-01');