package fr.marissel.oauth2.controller;

import fr.marissel.oauth2.domain.Grade;
import fr.marissel.oauth2.domain.Lesson;
import fr.marissel.oauth2.domain.Registration;
import fr.marissel.oauth2.repository.LessonRepository;
import fr.marissel.oauth2.repository.RegistrationRepository;
import fr.marissel.oauth2.repository.StudentRepository;
import fr.marissel.oauth2.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.keycloak.KeycloakPrincipal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/registration")
@AllArgsConstructor
public class RegistrationController {

    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final LessonRepository lessonRepository;
    private final RegistrationRepository registrationRepository;

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<Lesson>> getLessonByTeacher(@PathVariable final Integer teacherId) {

        if (teacherRepository.findById(teacherId).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var lessons = lessonRepository.findByTeacherId(teacherId);
        return ResponseEntity.ok().body(lessons);

    }

    @GetMapping("/student/{studentId}")
    @PreAuthorize("hasRole('ROLE_STUDENT') or hasRole('ROLE_TEACHER')")
    public ResponseEntity<List<Registration>> getRegistrationsByStudent(@PathVariable final Integer studentId) {

        if (studentRepository.findById(studentId).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var registrations = registrationRepository.findByStudentId(studentId);
        return ResponseEntity.ok().body(registrations);

    }

    @PostMapping("/register")
    @PreAuthorize("hasRole('ROLE_STUDENT') and #email == principal.name")
    public ResponseEntity<Void> registerStudent(@RequestParam("student_email") final String email,
                                                @RequestParam("lesson_id") final Integer lessonId) {

        var student = studentRepository.findByEmail(email);
        var lesson = lessonRepository.findById(lessonId);

        if (student.isEmpty() || lesson.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var registration = registrationRepository.findByStudentIdAndLessonId(student.get().getId(), lessonId);
        if (registration.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        final Registration registrationNew = new Registration();
        registrationNew.setLesson(lesson.get());
        registrationNew.setStudent(student.get());
        registrationNew.setRegisteredAt(LocalDateTime.now());
        registrationRepository.saveAndFlush(registrationNew);

        return ResponseEntity.ok().build();

    }

    @PutMapping("/grade")
    @PreAuthorize("hasRole('ROLE_TEACHER') and #email == principal.name")
    public ResponseEntity<Void> gradeStudent(@RequestParam("student_id") final Integer studentId,
                                             @RequestParam("lesson_id") final Integer lessonId,
                                             @RequestParam final Grade grade) {

        var registration = registrationRepository.findByStudentIdAndLessonId(studentId, lessonId);

        if (registration.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var email = ((KeycloakPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getName();
        if (!registration.get().getLesson().getTeacher().getEmail().equals(email)) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        registration.get().setGrade(grade);
        registrationRepository.saveAndFlush(registration.get());
        return ResponseEntity.ok().build();
    }
}
