package com.fedorchenko.testTask.dto;

import com.fedorchenko.testTask.entities.Answer;
import com.fedorchenko.testTask.entities.Option;
import com.fedorchenko.testTask.entities.Poll;
import com.fedorchenko.testTask.entities.Question;
import com.fedorchenko.testTask.enums.PollType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Schema(description = "Схема для представления и передачи данных об опросе вместе с вопросами, вариантами ответов и самими ответами.")
public class PollWithQuestionsDTO implements Serializable {
    @Schema(description = "Имя опроса")
    private final String name;
    @Schema(description = "Описание опроса")
    private final String description;
    @Schema(description = "Список вопросов опроса")
    private final List<QuestionDto> questions;
    @Schema(description = "Тип опроса: актуальный, закончившийся, не начавшийся. В случае, когда форма" +
            "предоставляет информацию о пройденном опросе, информация об этом также передается в этом поле")
    private final PollType pollType;

    public PollWithQuestionsDTO(Poll poll) {
        this.name = poll.getName();
        this.description = poll.getDescription();
        List<QuestionDto> questionDTOs = new ArrayList<>();
        for (Question question : poll.getQuestions()) {
            questionDTOs.add(new QuestionDto(question));
        }
        this.questions = questionDTOs;
        Date now = new Date();
        if (poll.getStart().after(now))
            this.pollType = PollType.NOT_STARTED;
        else if (poll.getEnd().before(now))
            this.pollType = PollType.ENDED;
        else this.pollType = PollType.ACTUAL;
    }


    public PollWithQuestionsDTO(Poll poll, List<QuestionDto> questionDtos) {
        this.name = poll.getName();
        this.description = poll.getDescription();
        this.questions = questionDtos;
        pollType = PollType.ANSWERED;
    }


    public PollType getPollType() {
        return pollType;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<QuestionDto> getQuestions() {
        return questions;
    }

    @Schema(description = "Схема для представления и передачи данных о вопрос вместе с вариантами ответов и выбранным ответом.")
    public static class QuestionDto implements Serializable {
        @Schema(description = "Текст вопроса")
        private final String text;
        @Schema(description = "Варианты ответов, если они есть")
        private final List<String> optionTexts;
        @Schema(description = "Генерируемая ссылка на сам вопрос с ответом")
        private final String url;

        public QuestionDto(Question question, Answer answer){
        this(question);
        optionTexts.add("Chosen answer: " + answer.getText());
        }

        public QuestionDto(Question question) {
            text = question.getText();
            List<String> optionsTexts = new ArrayList<>();
            for (Option option : question.getOptions()) {
                optionsTexts.add(option.getText());
            }
            this.optionTexts = optionsTexts;
            url = "/api/user_api/questions/" + question.getId();
        }


        public String getText() {
            return text;
        }

        public List<String> getOptionTexts() {
            return optionTexts;
        }

        public String getUrl() {
            return url;
        }
    }

}
