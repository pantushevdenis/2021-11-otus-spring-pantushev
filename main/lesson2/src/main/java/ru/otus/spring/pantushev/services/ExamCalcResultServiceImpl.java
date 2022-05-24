package ru.otus.spring.pantushev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring.pantushev.domains.ResultExam;
import ru.otus.spring.pantushev.domains.StudentResponse;
import ru.otus.spring.pantushev.domains.StudentResponsesList;

@Service
public class ExamCalcResultServiceImpl
    implements ExamCalcResultService
{
    private final QuestionService questionService;
    private double passingPercent;

    @Value("${examcalcresult.passingpercent}")
    public void setPassingPercent(double passingPercent) {
        this.passingPercent = passingPercent;
    }

    public double getPassingPercent() {
        return passingPercent;
    }

    @Autowired
    public ExamCalcResultServiceImpl(QuestionService questionService)
    {
        this.questionService = questionService;
    }

    public ResultExam calcResultExam(StudentResponsesList responses) throws Exception {
        int correctAnswerNumber = 0;
        int answerTotalNumber = 0;
        for (StudentResponse r: responses) {
            if (r.getIdAnswer() == questionService.getIdCorrectAnswer(r.getIdQuestion()))
            {
                correctAnswerNumber++;
            }
            answerTotalNumber++;
        }
        double perc = Math.floor(1000.0 * correctAnswerNumber / answerTotalNumber) / 10;
        boolean success = perc >= passingPercent;
        ResultExam res = ResultExam.builder()
                .answersTotal(answerTotalNumber)
                .answersCorrect(correctAnswerNumber)
                .percentageOfCorrectAnswers(perc)
                .examSuccesful(success)
                .build();

        return res;
    }

}
