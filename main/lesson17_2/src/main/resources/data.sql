insert into JENRES (ID, Name) values (1, 'Роман');
insert into JENRES (ID, Name) values (2, 'Рассказ');
insert into JENRES (ID, Name) values (3, 'Новелла');
insert into JENRES (ID, Name) values (4, 'Очерк');
insert into JENRES (ID, Name) values (5, 'Поэма');
insert into JENRES (ID, Name) values (6, 'Повесть');
insert into JENRES (ID, Name) values (7, 'Пьеса');
insert into JENRES (ID, Name) values (8, 'Рассказ');

insert into AUTHORS (ID, fullName, shortName, dateOfBirdth) values (1, 'Алексей Николаевич Толстой', 'Алексей Толстой', '1888-01-10');
insert into AUTHORS (ID, fullName, shortName, dateOfBirdth) values (2, 'Эдуард Успенский', 'Э. Успенский', '1937-12-1');
insert into AUTHORS (ID, fullName, shortName, dateOfBirdth) values (3, 'Лазарь Иосифович Лагин', 'Л. Лагин', '1903-11-21');
insert into AUTHORS (ID, fullName, shortName, dateOfBirdth) values (4, 'Виктор Юзефович Драгунский', 'Виктор Драгунский', '1913-12-01');
insert into AUTHORS (ID, fullName, shortName, dateOfBirdth) values (5, 'Лев Николаевич Толстой', 'Лев Толстой', '1828-08-28');

insert into BOOKS (Name, AuthorID, JenreID, PublishingYear) values ('Приключения Буратино', 1, 6, 2011);
insert into BOOKS (Name, AuthorID, JenreID, PublishingYear) values ('Любимая девочка Дяди Федора', 2, 6, 2000);
insert into BOOKS (Name, AuthorID, JenreID, PublishingYear) values ('Старик Хоттабыч', 3, 6, 1990);
insert into BOOKS (Name, AuthorID, JenreID, PublishingYear) values ('Денискины рассказы', 4, 2, 2001);

insert into COMMENTS(Content, BookId) values ('Это про Буратино книжка', 1);
insert into COMMENTS(Content, BookId) values ('Это про Старика Хоттабыча книжка', 3);



