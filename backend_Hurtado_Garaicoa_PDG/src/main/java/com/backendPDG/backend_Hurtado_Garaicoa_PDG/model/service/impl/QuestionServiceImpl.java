package com.backendPDG.backend_Hurtado_Garaicoa_PDG.model.service.impl;

import com.backendPDG.backend_Hurtado_Garaicoa_PDG.dto.ExamResultDTO;
import com.backendPDG.backend_Hurtado_Garaicoa_PDG.dto.QuestionDTO;
import com.backendPDG.backend_Hurtado_Garaicoa_PDG.exception.ExamNotFoundException;
import com.backendPDG.backend_Hurtado_Garaicoa_PDG.exception.ResourceNotFoundException;
import com.backendPDG.backend_Hurtado_Garaicoa_PDG.model.entity.Exam;
import com.backendPDG.backend_Hurtado_Garaicoa_PDG.model.entity.Question;
import com.backendPDG.backend_Hurtado_Garaicoa_PDG.model.repository.ExamRepository;
import com.backendPDG.backend_Hurtado_Garaicoa_PDG.model.repository.QuestionRepository;
import com.backendPDG.backend_Hurtado_Garaicoa_PDG.model.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    public Set<QuestionDTO> getQuestionsFromExam(Long examId) {
        Optional<Exam> optionalExam = examRepository.findById(examId);

        if (optionalExam.isPresent()) {
            Exam exam = optionalExam.get();
            Set<Question> examQuestions = exam.getQuestions();

            // Asegúrate de que cada pregunta esté asociada al examen actual.
            for (Question question : examQuestions) {
                if (!exam.equals(question.getExams())) {
                    // Asigna el examen al que pertenece la pregunta.
                    question.setExams(exam);
                    questionRepository.save(question);
                }
            }

            // Utiliza streams para convertir las preguntas a QuestionDTO y recopilarlas en un conjunto
            Set<QuestionDTO> questionDTOs = examQuestions.stream()
                    .map(this::mapToDto)
                    .collect(Collectors.toSet());

            return questionDTOs;
        } else {
            // Manejar el caso en que el examen no se encuentre
            throw new ExamNotFoundException("No se encontró un examen con ID: " + examId);
        }
    }




    public ExamResultDTO evaluateExam(Long examId, List<String> userAnswers, long startTimeMillis) {
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Exam", "Id", examId));

        List<Question> examQuestions = new ArrayList<>(exam.getQuestions());
        int totalQuestions = examQuestions.size();
        int correctAnswers = 0;

        if (totalQuestions != userAnswers.size()) {
            throw new IllegalArgumentException("El número de respuestas proporcionadas no coincide con el número de preguntas en el examen.");
        }

        for (int i = 0; i < totalQuestions; i++) {
            Question question = examQuestions.get(i);
            String userAnswer = userAnswers.get(i);

            if (question.getAnswer().equalsIgnoreCase(userAnswer)) {
                correctAnswers++;
            }
        }

        double score = ((double) correctAnswers / totalQuestions) * 100;

        long endTimeMillis = System.currentTimeMillis();
        long timeElapsedMillis = endTimeMillis - startTimeMillis;

        ExamResultDTO examResultDTO = new ExamResultDTO();
        examResultDTO.setExamId(exam.getExamId());
        examResultDTO.setTitle(exam.getTitle());
        examResultDTO.setTotalQuestions(totalQuestions);
        examResultDTO.setCorrectAnswers(correctAnswers);
        examResultDTO.setScore(score);
        examResultDTO.setTimeElapsedMillis(timeElapsedMillis);

        return examResultDTO;
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

