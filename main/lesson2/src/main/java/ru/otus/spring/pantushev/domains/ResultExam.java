package ru.otus.spring.pantushev.domains;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
public class ResultExam {
    @NonNull private final Integer answersTotal;
    @NonNull private final Integer answersCorrect;
    @NonNull private final Double percentageOfCorrectAnswers;
    @NonNull private final Boolean examSuccesful;
}
