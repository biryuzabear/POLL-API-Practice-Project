package com.fedorchenko.testTask.dto;

import com.fedorchenko.testTask.entities.Poll;

import java.io.Serializable;

public class PollDescriptionDto implements Serializable {
    private final String name;
    private final String description;
    private final String url;

    public PollDescriptionDto(Poll poll, boolean isAnswered) {
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
