package com.backendPDG.backend_Hurtado_Garaicoa_PDG.model.service;

import com.backendPDG.backend_Hurtado_Garaicoa_PDG.dto.ExamDTO;
import com.backendPDG.backend_Hurtado_Garaicoa_PDG.dto.QuestionDTO;

import java.util.List;
import java.util.Map;

public interface ExamService {
    ExamDTO addExam(ExamDTO examDTO);

    List<ExamDTO> getExams();

    ExamDTO getExamById(Long examId);

    ExamDTO updateExam(Long examId, ExamDTO examDTO);

    String deleteExam(Long examId);

    List<ExamDTO> getActiveExams();

    Map<String, Object> evaluateExam(List<QuestionDTO> questions);
}
