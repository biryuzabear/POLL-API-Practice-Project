package com.fedorchenko.testTask.dto;

import com.fedorchenko.testTask.entities.Poll;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(description = "Схема для представления основных данных об опросе с ссылками на конкретный опрос.")
public class PollDescriptionDTO implements Serializable {

    @Schema(description = "Имя опроса")
    private final String name;
    @Schema(description = "Описание опроса")
    private final String description;
    @Schema(description = "Генерируемая ссылка на сам опрос с вопросами и ответами")
    private final String url;

    public PollDescriptionDTO(Poll poll, boolean isAnswered) {
        name = poll.getName();
        description = poll.getDescription();
        if (isAnswered)
            url = "/api/user_api/answered/" + poll.getId();
        else
            url = "/api/user_api/polls/" + poll.getId();
    }


    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}
