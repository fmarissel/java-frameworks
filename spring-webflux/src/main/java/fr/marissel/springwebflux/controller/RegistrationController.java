package fr.marissel.springwebflux.controller;

import fr.marissel.springwebflux.domain.Registration;
import fr.marissel.springwebflux.repository.RegistrationRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationRepository registrationRepository;

    @GetMapping
    public ResponseEntity<Flux<Registration>> findAll() {
        final var registrations = registrationRepository.findAll();
        return ResponseEntity.ok().body(registrations);
    }

    @PutMapping("/grade")
    public Mono<ResponseEntity<String>> gradeStudent(@RequestParam("student_id") final Integer studentId,
                                                     @RequestParam("lesson_id") final Integer lessonId,
                                                     @RequestParam final String grade) {

        return registrationRepository.findByStudentIdAndLessonId(studentId, lessonId)
                .flatMap(registration -> {
                    registration.setGrade(grade);
                    return registrationRepository.save(registration);
                })
                .flatMap(registration -> Mono.just(ResponseEntity.ok().body("Registration id=" + registration.getId() + " has been successfully updated")))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }
}
