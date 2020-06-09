package fr.marissel.h2.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class Registration {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Column
    private LocalDateTime registeredAt;

    @Column(length = 1)
    @Enumerated(EnumType.STRING)
    private Grade grade;
}
