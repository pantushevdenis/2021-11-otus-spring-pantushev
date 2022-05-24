package ru.otus.spring.pantushev.services;

import ru.otus.spring.pantushev.services.ext.UIStudentResponse;

/*
    Интерфейс ввода-вывода и организации диалога с пользователем.
    Реализация может быть как в виде экранных форм,
    так и в виде консольного интерфейса.
 */
public interface UIService {
    /*
    Выводит сообщение
     */
    void showMessage(String message);
    /*
    Выводит очередной вопрос message
    Выводит требование правильности (admonition) - число или q.
    Если число, возвращает <число>, false,
    если ввод был q, возвращает 0, true.
    Если пользователль вводил ни число, ни q, вывод требования и ввод.
     */
    UIStudentResponse requestAnswer(String message);
    /*
    Запрашивает да или нет в виде y для да или любой другой символ для нет.
     */
    boolean requestYes(String message);
}
