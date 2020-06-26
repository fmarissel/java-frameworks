package fr.marissel.mongodb.controller;

import fr.marissel.mongodb.domain.Grade;
import fr.marissel.mongodb.domain.Lesson;
import fr.marissel.mongodb.domain.Registration;
import fr.marissel.mongodb.repository.LessonRepository;
import fr.marissel.mongodb.repository.RegistrationRepository;
import fr.marissel.mongodb.repository.StudentRepository;
import fr.marissel.mongodb.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    @GetMapping("/student")
    public ResponseEntity<List<Registration>> getRegistrationsByStudent(@RequestParam final String email) {

        var student = studentRepository.findByEmail(email);
        if (student.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var registrations = registrationRepository.findByStudent(student.get());
        return ResponseEntity.ok().body(registrations);
    }

    @PostMapping("/register")
    public ResponseEntity<Registration> registerStudent(@RequestParam("student_email") final String email,
                                                        @RequestParam("lesson_code") final String code) {

        var student = studentRepository.findByEmail(email);
        var lesson = lessonRepository.findByCode(code);

        if (student.isEmpty() || lesson.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var registration = registrationRepository.findByStudentAndLesson(student.get(), lesson.get());
        if (registration.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        var registrationNew = Registration.builder()
                .lesson(lesson.get())
                .student(student.get())
                .registeredAt(LocalDateTime.now())
                .build();
        registrationNew = registrationRepository.save(registrationNew);

        return ResponseEntity.ok().body(registrationNew);
    }

    @PutMapping("/grade")
    public ResponseEntity<Void> gradeStudent(@RequestParam("student_email") final String email,
                                             @RequestParam("lesson_code") final String code,
                                             @RequestParam final Grade grade) {

        var student = studentRepository.findByEmail(email);
        var lesson = lessonRepository.findByCode(code);

        if (student.isEmpty() || lesson.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        int count = registrationRepository.updateRegistration(lesson.get(), student.get(), grade);

        if (count == 0) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }
}
