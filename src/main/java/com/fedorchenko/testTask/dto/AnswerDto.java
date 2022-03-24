package com.fedorchenko.testTask.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fedorchenko.testTask.entities.Answer;

import java.io.Serializable;
import java.util.Objects;

public class AnswerDto implements Serializable {
    private final String text;

    @JsonCreator
    public AnswerDto(String text) {
        this.text = text;
    }

    public AnswerDto(Answer answer){
        text = answer.getText();
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerDto entity = (AnswerDto) o;
        return Objects.equals(this.text, entity.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "text = " + text + ")";
    }
}
