insert into JENRES (ID, Name) values (1, 'Jenre1');
insert into JENRES (ID, Name) values (2, 'Jenre2');

insert into AUTHORS (ID, Name) values (1, 'Author1');
insert into AUTHORS (ID, Name) values (2, 'Author2');

insert into BOOKS (Name, AuthorID, JenreID, PublishingYear) values ('Book1', 1, 1, 2000);
insert into BOOKS (Name, AuthorID, JenreID, PublishingYear) values ('Book2', 2, 1, 2001);
insert into BOOKS (Name, AuthorID, JenreID, PublishingYear) values ('Book3', 2, 2, 2002);

inserT into COMMENTS (Content, BookId) values ('Comment1_1', 1);
insert into COMMENTS (Content, BookId) values ('Comment1_2', 1);
insert into COMMENTS (Content, BookId) values ('Comment1_3', 1);
insert into COMMENTS (Content, BookId) values ('Comment2_1', 2);

insert into USERS (UserName, Password, Enabled) values ('Admin', 'Admin123', 1);
insert into USERS (UserName, Password, Enabled) values ('User1', '123', 1);
insert into USERS (UserName, Password, Enabled) values ('User2', '123', 1);

insert into AUTHORITIES (UserName, Authority) values ('Admin', 'ROLE_ADMIN');
insert into AUTHORITIES (UserName, Authority) values ('Admin', 'ROLE_USER');
insert into AUTHORITIES (UserName, Authority) values ('User1', 'ROLE_USER');
insert into AUTHORITIES (UserName, Authority) values ('User2', 'ROLE_USER');
