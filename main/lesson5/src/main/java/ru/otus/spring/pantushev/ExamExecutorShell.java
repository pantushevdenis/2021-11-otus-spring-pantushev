package ru.otus.spring.pantushev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.pantushev.services.ExamExecutorServiceImpl;

@ShellComponent
public class ExamExecutorShell {
    ExamExecutorServiceImpl examExec;

    @Autowired
    public ExamExecutorShell(ExamExecutorServiceImpl examExec) {
        this.examExec = examExec;
    }

    @ShellMethod("Begin exam")
    public void begin()
    {
        examExec.oneExamExecute();
    }
}
