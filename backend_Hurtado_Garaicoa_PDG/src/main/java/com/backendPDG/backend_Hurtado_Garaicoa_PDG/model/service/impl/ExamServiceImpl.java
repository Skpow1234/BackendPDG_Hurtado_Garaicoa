package com.backendPDG.backend_Hurtado_Garaicoa_PDG.model.service.impl;

import com.backendPDG.backend_Hurtado_Garaicoa_PDG.dto.ExamDTO;
import com.backendPDG.backend_Hurtado_Garaicoa_PDG.exception.ResourceNotFoundException;
import com.backendPDG.backend_Hurtado_Garaicoa_PDG.model.entity.Category;
import com.backendPDG.backend_Hurtado_Garaicoa_PDG.model.entity.Exam;
import com.backendPDG.backend_Hurtado_Garaicoa_PDG.model.repository.CategoryRepository;
import com.backendPDG.backend_Hurtado_Garaicoa_PDG.model.repository.ExamRepository;
import com.backendPDG.backend_Hurtado_Garaicoa_PDG.model.repository.UserRepository;
import com.backendPDG.backend_Hurtado_Garaicoa_PDG.model.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {
    private final ExamRepository examRepository;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public ExamDTO addExam(ExamDTO examDTO) {
        Exam exam = mapToEntity(examDTO);
        Category category = categoryRepository.findById(examDTO.getCategoryId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category",
                                "Id",
                                examDTO.getCategoryId()
                        ));

        exam.setCategory(category);
        Exam responseExam = examRepository.save(exam);

        // Obtén los datos completos de Exam después de guardarlos
        Exam finalResponseExam = responseExam;
        responseExam = examRepository.findById(responseExam.getExamId())
                .orElseThrow(() -> new ResourceNotFoundException("Exam", "Id", finalResponseExam.getExamId()));

        // Mapea los datos completos de Exam a ExamDTO
        ExamDTO examResponse = mapToDto(responseExam);

        return examResponse;
    }


    @Override
    public List<ExamDTO> getExams() {
        List<Exam> examDtoList = examRepository.findAll();
        List<ExamDTO> examDTOS = examDtoList
                .stream()
                .map(
                        exam -> mapToDto(exam))
                .collect(Collectors.toList());
        return examDTOS;
    }

    @Override
    public ExamDTO getExamById(Long examId) {
        Exam exam = examRepository.findById(examId)
                .orElseThrow(()->
                        new ResourceNotFoundException("Exam", "Id", examId));
        return mapToDto(exam);
    }

    @Override
    public ExamDTO updateExam(Long examId, ExamDTO examDTO) {
        Exam exam = examRepository.findById(examId)
                .orElseThrow(()->
                        new ResourceNotFoundException("Exam", "Id", examId));
        Exam newExam = mapToEntity(examDTO);
        exam.setTitle(newExam.getTitle());
        exam.setDescription(newExam.getDescription());
        examRepository.save(exam);
        return mapToDto(exam);
    }

    @Override
    public String deleteExam(Long examId) {
        examRepository.findById(examId)
                .orElseThrow(()->
                        new ResourceNotFoundException("Exam", "Id", examId));
        examRepository.deleteById(examId);
        return "Exam successfully  deleted.";
    }

    @Override
    public List<ExamDTO> getActiveExams() {
        List<Exam> exams = this.examRepository.findByActive(true);
        return exams.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private ExamDTO mapToDto(Exam exam){
        ExamDTO examDTO = new ExamDTO();
        examDTO.setExamId(exam.getExamId());
        examDTO.setTitle(exam.getTitle());
        examDTO.setDescription(exam.getDescription());
        examDTO.setMaxScore(exam.getMaxScore());
        examDTO.setNumberQuestions(exam.getNumberQuestions());
        examDTO.setFirstVideo(exam.getFirstVideo());
        examDTO.setSecondVideo(exam.getSecondVideo());
        examDTO.setCategoryId(exam.getCategory().getId());
        return examDTO;
    }

    private Exam mapToEntity(ExamDTO examDTO){
        Exam exam = modelMapper.map(examDTO, Exam.class);
        return exam;
    }
    private String authenticatedUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        return username;
    }
}
