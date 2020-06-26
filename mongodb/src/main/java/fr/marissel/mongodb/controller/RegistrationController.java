package fr.marissel.mongodb.controller;

import fr.marissel.mongodb.domain.Lesson;
import fr.marissel.mongodb.repository.LessonRepository;
import fr.marissel.mongodb.repository.RegistrationRepository;
import fr.marissel.mongodb.repository.StudentRepository;
import fr.marissel.mongodb.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("registration")
public class RegistrationController {

    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final LessonRepository lessonRepository;
    private final RegistrationRepository registrationRepository;

    @GetMapping("/teacher")
    public ResponseEntity<List<Lesson>> getLessonByTeacher(@RequestParam final String email) {

        var teacher = teacherRepository.findByEmail(email);
        if (teacher.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var lessons = lessonRepository.findByTeacher(teacher.get());
        return ResponseEntity.ok().body(lessons);

    }
}
