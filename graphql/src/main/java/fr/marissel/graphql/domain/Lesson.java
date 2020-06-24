package fr.marissel.graphql.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Lesson {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @Column
    private OffsetDateTime startAt;

    @Column(columnDefinition = "INTERVAL HOUR TO MINUTE")
    private Duration duration;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "registration",
            joinColumns = @JoinColumn(name = "lesson_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> students;
}
