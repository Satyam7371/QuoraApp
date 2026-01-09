package org.example.quoraapp.dtos;

import lombok.Data;

@Data
public class AnswerDto {
    private Long id;
    private String content;
    private Long userId;

    private Long questionId;
}
