package ru.otus.spring.pantushev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.pantushev.domains.*;

import java.util.List;
import java.util.Scanner;

@Service
public class ExamExecutorServiceImpl
        implements ExamExecutorService
{
    private QuestionService questionsService;
    private ExamCalcResultServiceImpl examCalcResultServiceImpl;

    @Autowired
    public ExamExecutorServiceImpl(QuestionService questionsService, ExamCalcResultServiceImpl examCalcResultServiceImpl) {
        this.questionsService = questionsService;
        this.examCalcResultServiceImpl = examCalcResultServiceImpl;
    }

    @Override
    public void ExamExecute() {
        Scanner sIn = new Scanner(System.in);

        do {
            StudentResponsesList studentResponsesList = new StudentResponsesList();
            try {
                List<Question> questionsList = questionsService.getQuestionsList(); //Это не нужно повторять, просто для демонстрации кэширования загрузки экзамена в дао.
                for (Question q : questionsList) {
                    System.out.println("Question " + q.getId() + ": " + q.getBody());
                    int idAnswer = 1;
                    for (Answer a : q.getAnswers()) {
                        System.out.print("\t");
                        System.out.print(idAnswer);
                        System.out.println(":" + a.getBody());

                        idAnswer++;
                    }
                    int answer;
                    while (true) {
                        System.out.print("Your answer (number, q for exit from exam): ");
                        System.out.flush();
                        String answerStr = sIn.next();
                        if (answerStr.equals("q")) {
                            System.exit(0);
                        }
                        try {
                            answer = Integer.parseInt(answerStr);
                            break;
                        } catch (Exception e) {
                        }
                    }

                    StudentResponse studentResponse = new StudentResponse(q.getId(), answer);
                    studentResponsesList.add(studentResponse);

                    System.out.println();
                }

                ResultExam resultExam = examCalcResultServiceImpl.calcResultExam(studentResponsesList);

                String msgResCalc = "Result of exam: %d of %d (%f percents)";
                System.out.println(String.format(msgResCalc,
                        resultExam.getAnswersCorrect(),
                        resultExam.getAnswersTotal(),
                        resultExam.getPercentageOfCorrectAnswers()));
                if (resultExam.getExamSuccesful())
                {
                    System.out.println("Eexam passsed succesfully! Congratulation!");
                }
                else {
                    System.out.println("The exam has not been passed. Please try again.");
                }

                System.out.println();
            }
            catch (Exception e)
            {
                System.out.println(e);
            }

            System.out.println("Repeat exam (y/any key for none)?");
        } while(sIn.next().equals("y"));
    }
}
