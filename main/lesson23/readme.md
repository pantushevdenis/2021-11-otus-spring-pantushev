# Spring Security: Механизмы аутентификации
Домашняя работа

Пантюшев Д.А.  
2021-11-otus-spring  
otus.ru  
2021-2022 гг.

### Краткое описание
В CRUD Web-приложение добавить механизм аутентификации.

### Цель
Цель: защитить Web-приложение аутентифкацией и простой авторизацией
Результат: приложение с использованием Spring Security.

### Требования
1. Добавить в приложение новую сущность - пользователь. Не обязательно реализовывать методы по созданию пользователей - допустимо добавить пользователей только через БД-скрипты.
2. В существующее CRUD-приложение добавить механизм Form-based аутентификации.
3. UsersServices реализовать самостоятельно.
4. Авторизация на всех страницах - для всех аутентифицированных. Форма логина - доступна для всех.
5. Написать тесты контроллеров, которые проверяют, что все необходимые ресурсы действительно защищены. Данное задание НЕ засчитывает предыдущие! Данное ДЗ будет использоваться в дальнейшем.

### Примечания 
Это домашнее задание выполняется на основе ДЗ 16 урока (Spring MVC на Thymeleaf).  
Это домашнее задание является основой для следующих.

### Замечания по реализации
Хоть в задаче не было, но реализоно:
Работа с сессиями. Для примера раелизована функциональность сортировки тиблицы книг, по любому полю в 
таблице. Выбор пользователя, по какому полю сортировать, сохраняется в сессии.
