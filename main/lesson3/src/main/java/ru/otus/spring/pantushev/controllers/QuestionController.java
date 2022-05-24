package ru.otus.spring.pantushev.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.pantushev.domains.Answer;
import ru.otus.spring.pantushev.domains.QuestionHead;
import ru.otus.spring.pantushev.domains.getQuestionQuantity;
import ru.otus.spring.pantushev.services.QuestionService;

import java.util.List;

@RestController()
@RequestMapping("/questions")
public class QuestionController {

    final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("quantity")
    public getQuestionQuantity getQuestionsQuantity() throws Exception {

        int quantity = questionService.getQuestionsQuantity();
        getQuestionQuantity qq = new getQuestionQuantity(quantity);
        return qq;
    }

    @GetMapping("")
    public List<QuestionHead> getQuestionHeadsList() throws Exception
    {
        List<QuestionHead> questionHeads = questionService.getQuestionHeadsList();
        return questionHeads;
    }


    @GetMapping("{idQuestion}/answers")
    List<Answer> getAnswersList(@PathVariable("idQuestion") int idQuestion) throws Exception
    {
        List<Answer> answers = questionService.getAnswersList(idQuestion);
        return answers;
    }
}
