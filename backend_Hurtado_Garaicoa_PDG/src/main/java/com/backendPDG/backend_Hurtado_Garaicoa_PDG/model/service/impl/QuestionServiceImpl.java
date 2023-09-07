package com.backendPDG.backend_Hurtado_Garaicoa_PDG.model.service.impl;

import com.backendPDG.backend_Hurtado_Garaicoa_PDG.dto.QuestionDTO;
import com.backendPDG.backend_Hurtado_Garaicoa_PDG.exception.ResourceNotFoundException;
import com.backendPDG.backend_Hurtado_Garaicoa_PDG.model.entity.Exam;
import com.backendPDG.backend_Hurtado_Garaicoa_PDG.model.entity.Question;
import com.backendPDG.backend_Hurtado_Garaicoa_PDG.model.repository.ExamRepository;
import com.backendPDG.backend_Hurtado_Garaicoa_PDG.model.repository.QuestionRepository;
import com.backendPDG.backend_Hurtado_Garaicoa_PDG.model.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final ModelMapper modelMapper;
    private final ExamRepository examRepository;

    @Override
    public QuestionDTO addQuestion(QuestionDTO questionDTO, Long examId) {
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Exam", "Id", examId));

        Question question = mapToEntity(questionDTO);
        question.setExams(exam); // Asigna el examen a la pregunta
        return mapToDto(questionRepository.save(question));
    }

    @Override
    public List<QuestionDTO> getQuestions() {
        List<Question> questionList = questionRepository.findAll();
        List<QuestionDTO> questionDTOS = questionList
                .stream()
                .map(
                        question -> mapToDto(question))
                .collect(Collectors.toList());
        return questionDTOS;
    }

    @Override
    public QuestionDTO getQuestionById(Long questionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(()->
                        new ResourceNotFoundException("Question", "Id", questionId));
        return mapToDto(question);
    }

    @Override
    public QuestionDTO updateQuestion(Long questionId, QuestionDTO questionDTO) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(()->
                        new ResourceNotFoundException("Question", "Id", questionId));
        Question newQuestion = mapToEntity(questionDTO);
        question.setContent(newQuestion.getContent());
        question.setOption1(newQuestion.getOption1());
        question.setOption2(newQuestion.getOption2());
        question.setOption3(newQuestion.getOption3());
        question.setOption4(newQuestion.getOption4());
        question.setAnswer(newQuestion.getAnswer());
        questionRepository.save(question);
        return mapToDto(question);
    }

    @Override
    public String deleteQuestion(Long questionId) {
        questionRepository.findById(questionId)
                .orElseThrow(()->
                        new ResourceNotFoundException("Question", "Id", questionId));
        questionRepository.deleteById(questionId);
        return "Question successfully deleted.";
    }
    @Override
    public Set<QuestionDTO> getQuestionsFromExam(Exam exam) {
        return questionRepository.findByExams(exam);
    }

    private QuestionDTO mapToDto(Question question) {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setQuestionId(question.getQuestionId());
        questionDTO.setContent(question.getContent());
        questionDTO.setOption1(question.getOption1());
        questionDTO.setOption2(question.getOption2());
        questionDTO.setOption3(question.getOption3());
        questionDTO.setOption4(question.getOption4());
        questionDTO.setAnswer(question.getAnswer());

        if (question.getExams() != null) {
            questionDTO.setExamId(question.getExams().getExamId());
        }

        return questionDTO;
    }



    private Question mapToEntity(QuestionDTO questionDTO){
        Question question = modelMapper.map(questionDTO, Question.class);
        return question;
    }
}

