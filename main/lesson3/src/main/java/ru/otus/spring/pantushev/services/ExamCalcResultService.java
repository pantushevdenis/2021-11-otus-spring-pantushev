package ru.otus.spring.pantushev.services;

import ru.otus.spring.pantushev.domains.ResultExam;
import ru.otus.spring.pantushev.domains.StudentResponsesList;

public interface ExamCalcResultService {
    public ResultExam calcResultExam(StudentResponsesList responses) throws Exception;
}
