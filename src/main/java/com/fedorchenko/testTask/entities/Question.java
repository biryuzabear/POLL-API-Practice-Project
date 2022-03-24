package com.fedorchenko.testTask.entities;

import com.fasterxml.jackson.annotation.*;
import com.fedorchenko.testTask.enums.QuestionType;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "questions")
@Schema(description = "Схема для изменения сущности Question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Column(name = "text", length = 280)
    private String text;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private QuestionType type;

    @ManyToOne
    @JoinColumn(name = "poll_id")
    private Poll poll;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Option> options = new ArrayList<>();

    @JsonManagedReference
    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    @JsonBackReference
    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public QuestionType getQuestionType() {
        return type;
    }

    public void setQuestionType(QuestionType questionType) {
        this.type = questionType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}