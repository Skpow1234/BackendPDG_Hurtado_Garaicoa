package com.backendPDG.backend_Hurtado_Garaicoa_PDG.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExamDTO {
    private Long examId;
    private String title;
    private String description;
    private String maxScore;
    private String numberQuestions;
    private boolean active = false;
    private String firstVideo;
    private String secondVideo;
    private Long categoryId;
    private List<QuestionDTO> questions;
}
