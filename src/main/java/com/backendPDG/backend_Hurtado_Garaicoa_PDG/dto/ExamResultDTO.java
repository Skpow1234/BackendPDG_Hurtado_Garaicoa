package com.backendPDG.backend_Hurtado_Garaicoa_PDG.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExamResultDTO {
    private Long examId;
    private String title;
    private int totalQuestions;
    private int correctAnswers;
    private double score;
    private long timeElapsedMillis;
}
