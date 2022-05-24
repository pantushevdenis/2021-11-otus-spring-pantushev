package ru.otus.spring.pantushev.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.spring.pantushev.domains.Answer;
import ru.otus.spring.pantushev.domains.Question;
import ru.otus.spring.pantushev.domains.QuestionHead;
import ru.otus.spring.pantushev.domains.QuestionsList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

@Component
public class QuestionDaoResourceCSV
    implements QuestionDao
{
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    @Value("${questions.filename}")
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    @Override
    public QuestionsList getQuestionsList()
            throws Exception
    {
        QuestionsList questionsList = new QuestionsList();
        try(InputStream is = getClass().getClassLoader().getResourceAsStream(fileName))
        {
            if (is == null)
            {
                throw new Exception("Resource file " + fileName + " not found!");
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line = br.readLine();
            while(line != null)
            {
                //System.out.println(line);
                StringTokenizer tokens = new StringTokenizer(line, ";");
                String idStr = tokens.nextToken();
                int id;
                try {
                    id = Integer.parseInt(idStr);
                }
                catch (NumberFormatException e)
                {
                    throw new IOException("Integer expected for question number");
                }
                String correctAnswerStr = tokens.nextToken();
                int correctAnswer;
                try {
                    correctAnswer = Integer.parseInt(correctAnswerStr);
                }
                catch (NumberFormatException e)
                {
                    throw new IOException("Integer expected for correct answer");
                }
                String bodyStr = tokens.nextToken();
                QuestionHead qh = new QuestionHead(id, bodyStr);
                Question qb = new Question(qh, correctAnswer);

                List<Answer> answers = qb.getAnswers();
                while(tokens.hasMoreTokens())
                {
                    String answer = tokens.nextToken();
                    answers.add(new Answer(answer));
                }

                questionsList.add(qb);

                line = br.readLine();
            }
        } catch (IOException e) {
            throw new Exception(e);
        }
        return questionsList;
    }

}
