package com.backendPDG.backend_Hurtado_Garaicoa_PDG.controller;

import com.backendPDG.backend_Hurtado_Garaicoa_PDG.dto.QuestionDTO;
import com.backendPDG.backend_Hurtado_Garaicoa_PDG.model.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping(path = "/addQuestion/{examId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QuestionDTO> addQuestion(
            @PathVariable(name = "examId") Long examId,
            @Valid @RequestBody QuestionDTO questionDTO) {
        return new ResponseEntity<>(questionService.addQuestion(questionDTO, examId), HttpStatus.CREATED);
    }

    @GetMapping("/getQuestion/{id}")
    public ResponseEntity<QuestionDTO> getQuestionById(
            @PathVariable(name = "id") Long questionId) {
        return new ResponseEntity<>(questionService.getQuestionById(questionId), HttpStatus.OK);
    }

    @GetMapping("/getQuestions")
    public ResponseEntity<List<QuestionDTO>> getQuestions() {
        return new ResponseEntity<>(questionService.getQuestions(), HttpStatus.OK);
    }

    @GetMapping("/getQuestionsFromExam/{examId}")
    public ResponseEntity<Set<QuestionDTO>> getQuestionsFromExam(
            @PathVariable(name = "examId") Long examId) {
        Set<QuestionDTO> questions = questionService.getQuestionsFromExam(examId);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @PutMapping("/updateQuestion/{id}")
    public ResponseEntity<QuestionDTO> updateQuestion(
            @PathVariable(name = "id") Long questionId,
            @RequestBody QuestionDTO questionDTO) {
        return new ResponseEntity<>(questionService.updateQuestion(questionId, questionDTO), HttpStatus.OK);
    }

    @DeleteMapping("/deleteQuestion/{id}")
    public ResponseEntity<String> deleteQuestion(
            @PathVariable(name = "id") Long questionId) {
        return new ResponseEntity<>(questionService.deleteQuestion(questionId), HttpStatus.OK);
    }
}

