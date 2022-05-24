insert into JENRES (ID, Name) values (1, 'Роман');
insert into JENRES (ID, Name) values (2, 'Рассказ');
insert into JENRES (ID, Name) values (3, 'Новелла');
insert into JENRES (ID, Name) values (4, 'Очерк');
insert into JENRES (ID, Name) values (5, 'Поэма');
insert into JENRES (ID, Name) values (6, 'Повесть');
insert into JENRES (ID, Name) values (7, 'Пьеса');
insert into JENRES (ID, Name) values (8, 'Рассказ');

insert into AUTHORS (ID, Name) values (1, 'Алексей Толстой');
insert into AUTHORS (ID, Name) values (2, 'Э. Успенский');
insert into AUTHORS (ID, Name) values (3, 'Л. Лагин');
insert into AUTHORS (ID, Name) values (4, 'Виктор Драгунский');
insert into AUTHORS (ID, Name) values (5, 'Лев Толстой');

insert into BOOKS (Name, AuthorID, JenreID, PublishingYear) values ('Приключения Буратино', 1, 6, 2011);
insert into BOOKS (Name, AuthorID, JenreID, PublishingYear) values ('Любимая девочка Дяди Федора', 2, 6, 2000);
insert into BOOKS (Name, AuthorID, JenreID, PublishingYear) values ('Старик Хоттабыч', 3, 6, 1990);
insert into BOOKS (Name, AuthorID, JenreID, PublishingYear) values ('Денискины рассказы', 4, 2, 2001);

insert into COMMENTS(Content, BookId) values ('Это про Буратино книжка', 1);
insert into COMMENTS(Content, BookId) values ('Это про Старика Хоттабыча книжка', 3);

insert into USERS (UserName, Password, Enabled) values ('Admin', 'Admin123', 1);
insert into USERS (UserName, Password, Enabled) values ('User1', 'Qwert', 1);
insert into USERS (UserName, Password, Enabled) values ('User2', '123', 1);

insert into AUTHORITIES (UserName, Authority) values ('Admin', 'ROLE_ADMIN');
insert into AUTHORITIES (UserName, Authority) values ('Admin', 'ROLE_USER_WRITER');
insert into AUTHORITIES (UserName, Authority) values ('Admin', 'ROLE_USER_READER');
insert into AUTHORITIES (UserName, Authority) values ('User1', 'ROLE_USER_WRITER');
insert into AUTHORITIES (UserName, Authority) values ('User1', 'ROLE_USER_READER');
insert into AUTHORITIES (UserName, Authority) values ('User2', 'ROLE_USER_READER');





