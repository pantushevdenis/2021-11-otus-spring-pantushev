package ru.otus.spring.pantushev.dao;

import ru.otus.spring.pantushev.beans.QuestionBean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class QuestionDaoResource
    implements QuestionDao
{
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public QuestionDaoResource(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public QuestionBean getFirstQuestion() {
        return null;
    }

    @Override
    public Iterator<QuestionBean> getQuestionsAsIterator()
            throws IOException
    {
        ArrayList<QuestionBean> questions = new ArrayList<>();

        try(InputStream is = getClass().getClassLoader().getResourceAsStream("questions.csv"))
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line = br.readLine();
            while(line != null)
            {
                //System.out.println(line);
                StringTokenizer tokens = new StringTokenizer(line, ",");
                String idStr = tokens.nextToken();
                int id;
                try {
                    id = Integer.parseInt(idStr);
                }
                catch (NumberFormatException e)
                {
                    throw new IOException(e);
                }
                String bodyStr = tokens.nextToken();
                String answersStr = "";
                while(tokens.hasMoreTokens())
                {
                    String answer = tokens.nextToken();
                    answersStr += ", " + answer;
                }

                questions.add(new QuestionBean(id, bodyStr, answersStr));

                line = br.readLine();
            }

        }

        return questions.iterator();
    }
}
