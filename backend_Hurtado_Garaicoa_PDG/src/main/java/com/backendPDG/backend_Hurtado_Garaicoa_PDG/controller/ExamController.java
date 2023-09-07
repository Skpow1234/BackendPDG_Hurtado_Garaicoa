package com.backendPDG.backend_Hurtado_Garaicoa_PDG.controller;

import com.backendPDG.backend_Hurtado_Garaicoa_PDG.dto.ExamDTO;
import com.backendPDG.backend_Hurtado_Garaicoa_PDG.model.service.ExamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/exams")
@RequiredArgsConstructor
public class ExamController {

    @Autowired
    private ExamService examService;

    @PostMapping(path = "/createExam",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExamDTO> addExam(
            @Valid @RequestBody ExamDTO examDTO){
        return new ResponseEntity<>(examService.addExam(examDTO), HttpStatus.CREATED);
    }
    @GetMapping("/getExam/{id}")
    public ResponseEntity<ExamDTO> getExamById(
            @PathVariable(name = "id") Long examId){
        return new ResponseEntity<>(examService.getExamById(examId), HttpStatus.OK);
    }

    @GetMapping("/getExams")
    public ResponseEntity<List<ExamDTO>> getExams(){
        return new ResponseEntity<>(examService.getExams(), HttpStatus.OK);
    }

    @PutMapping("/updateExam/{id}")
    public ResponseEntity<ExamDTO> updateExam(
            @PathVariable(name = "id") Long examId,
            @RequestBody ExamDTO examDTO){
        return new ResponseEntity<>(examService.updateExam(examId, examDTO), HttpStatus.OK);
    }

    @DeleteMapping("/deleteExam/{id}")
    public ResponseEntity<String> deleteExam(
            @PathVariable(name = "id") Long examId){
        return new ResponseEntity<>(examService.deleteExam(examId), HttpStatus.OK);
    }

    @GetMapping("/getActiveExams")
    public ResponseEntity<List<ExamDTO>> getActiveExams(){
        List<ExamDTO> exams = examService.getActiveExams();
        return new ResponseEntity<>(exams, HttpStatus.OK);
    }

}
