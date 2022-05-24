package ru.otus.spring.pantushev.domains;


import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class ResultExam {
    @NonNull private final Integer answersTotal;
    @NonNull private final Integer answersCorrect;
    @NonNull private final Double percentageOfCorrectAnswers;
    @NonNull private final Boolean examSuccesful;
}
