package com.backendPDG.backend_Hurtado_Garaicoa_PDG.model.service;

import com.backendPDG.backend_Hurtado_Garaicoa_PDG.dto.ExamDTO;

import java.util.List;

public interface ExamService {
    ExamDTO addExam(ExamDTO examDTO);

    List<ExamDTO> getExams();

    ExamDTO getExamById(Long examId);

    ExamDTO updateExam(Long examId, ExamDTO examDTO);

    String deleteExam(Long examId);

    List<ExamDTO> getActiveExams();
}
