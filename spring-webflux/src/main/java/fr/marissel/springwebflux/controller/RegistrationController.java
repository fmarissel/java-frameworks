package fr.marissel.springwebflux.controller;

import fr.marissel.springwebflux.domain.Lesson;
import fr.marissel.springwebflux.domain.Registration;
import fr.marissel.springwebflux.exception.AlreadyExistsException;
import fr.marissel.springwebflux.exception.NotFoundException;
import fr.marissel.springwebflux.repository.LessonRepository;
import fr.marissel.springwebflux.repository.PersonRepository;
import fr.marissel.springwebflux.repository.RegistrationRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationRepository registrationRepository;
    private final LessonRepository lessonRepository;
    private final PersonRepository personRepository;

    @GetMapping
    public Flux<Registration> findAll() {
        return registrationRepository.findAll();
    }

    @GetMapping("/teacher/{teacherId}")
    public Flux<Lesson> getLessonByTeacher(@PathVariable final Integer teacherId) {

        return personRepository.findByIdAndPersonType(teacherId, "TEACHER")
                .flatMapMany(teacher -> lessonRepository.findByTeacherId(teacherId))
                .switchIfEmpty(Flux.error(NotFoundException::new));
    }

    @GetMapping("/student/{studentId}")
    public Flux<Registration> getRegistrationsByStudent(@PathVariable final Integer studentId) {

        return personRepository.findByIdAndPersonType(studentId, "STUDENT")
                .flatMapMany(teacher -> registrationRepository.findByStudentId(studentId))
                .switchIfEmpty(Flux.error(NotFoundException::new));
    }

    @PostMapping("/register")
    public Mono<String> registerStudent(@RequestParam("student_id") final Integer studentId,
                                        @RequestParam("lesson_id") final Integer lessonId) {

        return Mono.zip(personRepository.findByIdAndPersonType(studentId, "STUDENT")
                        .switchIfEmpty(Mono.error(NotFoundException::new)),
                lessonRepository.findById(lessonId)
                        .switchIfEmpty(Mono.error(NotFoundException::new)),
                registrationRepository.findByStudentIdAndLessonId(studentId, lessonId)
                        .flatMap(reg -> Mono.error(AlreadyExistsException::new))
                        .switchIfEmpty(Mono.just("")))
                .then(registrationRepository.save(new Registration(null, lessonId, studentId, LocalDateTime.now(), null)))
                .flatMap(registration -> Mono.just("Registration id=" + ((Registration) registration).getId() + " has been successfully created"));
    }

    @PutMapping("/grade")
    public Mono<String> gradeStudent(@RequestParam("student_id") final Integer studentId,
                                     @RequestParam("lesson_id") final Integer lessonId,
                                     @RequestParam final String grade) {

        return registrationRepository.findByStudentIdAndLessonId(studentId, lessonId)
                .flatMap(registration -> {
                    registration.setGrade(grade);
                    return registrationRepository.save(registration);
                })
                .flatMap(registration -> Mono.just("Registration id=" + registration.getId() + " has been successfully updated"))
                .switchIfEmpty(Mono.error(NotFoundException::new));
    }

    @ResponseStatus(
            value = HttpStatus.NOT_FOUND,
            reason = "Not Found")
    @ExceptionHandler(NotFoundException.class)
    public void notFoundHandler() {
        //
    }

    @ResponseStatus(
            value = HttpStatus.CONFLICT,
            reason = "Already Exists")
    @ExceptionHandler(AlreadyExistsException.class)
    public void alreadyExistsHandler() {
        //
    }
}
