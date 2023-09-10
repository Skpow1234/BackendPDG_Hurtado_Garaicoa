package com.backendPDG.backend_Hurtado_Garaicoa_PDG.model.service;

import com.backendPDG.backend_Hurtado_Garaicoa_PDG.dto.ExamResultDTO;
import com.backendPDG.backend_Hurtado_Garaicoa_PDG.dto.QuestionDTO;

import java.util.List;
import java.util.Set;

public interface QuestionService {
    QuestionDTO addQuestion(QuestionDTO questionDTO, Long examId);
    List<QuestionDTO> getQuestions();
    QuestionDTO getQuestionById(Long questionId);
    QuestionDTO updateQuestion(Long questionId, QuestionDTO questionDTO);
    String deleteQuestion(Long questionId);
    Set<QuestionDTO> getQuestionsFromExam(Long examId);
    ExamResultDTO evaluateExam(Long examId, List<String> userAnswers, long startTimeMillis);
}
