package com.backendPDG.backend_Hurtado_Garaicoa_PDG.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "exams")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "max_score")
    private String maxScore;

    @Column(name = "num_questions")
    private String numberQuestions;

    @Column(name = "state")
    private boolean active = false;

    @Column(name = "first_video")
    private String firstVideo;

    @Column(name = "second_video")
    private String secondVideo;

    @OneToMany(mappedBy = "exams",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Question> questions = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;


}
