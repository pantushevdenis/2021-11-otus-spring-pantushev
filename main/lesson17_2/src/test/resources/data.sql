insert into JENRES (ID, Name) values (1, 'Jenre1');
insert into JENRES (ID, Name) values (2, 'Jenre2');

insert into AUTHORS (ID, fullName, shortName, dateOfBirdth) values (1, 'Author1', 'Author1', '2001-01-10');
insert into AUTHORS (ID, fullName, shortName, dateOfBirdth) values (2, 'Author2', 'Author2', '2001-01-10');

insert into BOOKS (Name, AuthorID, JenreID, PublishingYear) values ('Book1', 1, 1, 2000);
insert into BOOKS (Name, AuthorID, JenreID, PublishingYear) values ('Book2', 2, 1, 2001);
insert into BOOKS (Name, AuthorID, JenreID, PublishingYear) values ('Book3', 2, 2, 2002);

insert into COMMENTS (Content, BookId) values ('Comment1_1', 1);
insert into COMMENTS (Content, BookId) values ('Comment1_2', 1);
insert into COMMENTS (Content, BookId) values ('Comment1_3', 1);
insert into COMMENTS (Content, BookId) values ('Comment2_1', 2);