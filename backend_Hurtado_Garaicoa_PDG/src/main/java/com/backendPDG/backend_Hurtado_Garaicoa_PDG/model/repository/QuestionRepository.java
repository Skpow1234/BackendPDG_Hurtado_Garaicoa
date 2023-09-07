package com.backendPDG.backend_Hurtado_Garaicoa_PDG.model.repository;

import com.backendPDG.backend_Hurtado_Garaicoa_PDG.dto.QuestionDTO;
import com.backendPDG.backend_Hurtado_Garaicoa_PDG.model.entity.Exam;
import com.backendPDG.backend_Hurtado_Garaicoa_PDG.model.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface QuestionRepository extends JpaRepository<Question,Long> {
    Set<QuestionDTO> findByExams(Exam exam);
}
