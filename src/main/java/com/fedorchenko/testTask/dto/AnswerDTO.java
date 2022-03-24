package com.fedorchenko.testTask.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fedorchenko.testTask.entities.Answer;
import io.swagger.v3.oas.annotations.media.Schema;


import java.io.Serializable;
import java.util.Objects;

@Schema(description = "Схема для передачи данных об ответе.")
public class AnswerDTO implements Serializable {
    @Schema(description = "Текстовый ответ на вопрос")
    private final String text;

    @JsonCreator
    public AnswerDTO(String text) {
        this.text = text;
    }

    public AnswerDTO(Answer answer){
        text = answer.getText();
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerDTO entity = (AnswerDTO) o;
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
