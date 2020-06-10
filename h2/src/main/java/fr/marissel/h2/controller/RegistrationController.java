package fr.marissel.h2.controller;

import fr.marissel.h2.domain.Grade;
import fr.marissel.h2.domain.Lesson;
import fr.marissel.h2.domain.Registration;
import fr.marissel.h2.repository.LessonRepository;
import fr.marissel.h2.repository.RegistrationRepository;
import fr.marissel.h2.repository.StudentRepository;
import fr.marissel.h2.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    public ResponseEntity<List<Registration>> getRegistrationsByStudent(@PathVariable final Integer studentId) {

        if (studentRepository.findById(studentId).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var registrations = registrationRepository.findByStudentId(studentId);
        return ResponseEntity.ok().body(registrations);

    }

    @PostMapping("/register")
    public ResponseEntity<Void> registerStudent(@RequestParam("student_id") final Integer studentId,
                                                @RequestParam("lesson_id") final Integer lessonId) {

        var student = studentRepository.findById(studentId);
        var lesson = lessonRepository.findById(lessonId);

        if (student.isEmpty() || lesson.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var registration = registrationRepository.findByStudentIdAndLessonId(studentId, lessonId);
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
    public ResponseEntity<Void> gradeStudent(@RequestParam("student_id") final Integer studentId,
                                             @RequestParam("lesson_id") final Integer lessonId,
                                             @RequestParam final Grade grade) {

        var registration = registrationRepository.findByStudentIdAndLessonId(studentId, lessonId);

        if (registration.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        registration.get().setGrade(grade);
        registrationRepository.saveAndFlush(registration.get());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/overlap/{studentId}")
    public ResponseEntity<Set<Registration>> getOverlap(@PathVariable Integer studentId) {

        final Set<Registration> overlaps = new HashSet<>();

        if (studentRepository.findById(studentId).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var registrations = registrationRepository.findByStudentId(studentId);

        for (final Registration registration : registrations) {
            final List<Registration> overlappingRegs = registrations.stream().filter(reg -> isOverlapping(registration, reg)).collect(Collectors.toList());
            if (!overlappingRegs.isEmpty()) {
                overlaps.add(registration);
                overlaps.addAll(overlappingRegs);
            }
        }

        return ResponseEntity.ok().body(overlaps);
    }

    private boolean isOverlapping(final Registration registration1, final Registration registration2) {

        if (registration1.equals(registration2)) {
            return false;
        }

        final LocalDateTime start1 = registration1.getLesson().getStartAt();
        final LocalDateTime end1 = start1.plus(registration1.getLesson().getDuration());

        final LocalDateTime start2 = registration2.getLesson().getStartAt();
        final LocalDateTime end2 = start2.plus(registration2.getLesson().getDuration());

        return start1.isBefore(end2) && start2.isBefore(end1);
    }
}
