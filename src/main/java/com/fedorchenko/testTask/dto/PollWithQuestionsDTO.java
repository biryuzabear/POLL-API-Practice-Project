package com.fedorchenko.testTask.dto;

import com.fedorchenko.testTask.entities.Answer;
import com.fedorchenko.testTask.entities.Option;
import com.fedorchenko.testTask.entities.Poll;
import com.fedorchenko.testTask.entities.Question;
import com.fedorchenko.testTask.enums.PollType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PollWithQuestionsDTO implements Serializable {
    private final String name;
    private final String description;
    private final List<QuestionDto> questions;
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


    public static class QuestionDto implements Serializable {
        private final String text;
        private final List<String> optionTexts;
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
