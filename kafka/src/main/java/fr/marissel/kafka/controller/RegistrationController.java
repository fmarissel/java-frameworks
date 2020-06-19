package fr.marissel.kafka.controller;

import fr.marissel.kafka.domain.Grade;
import fr.marissel.kafka.repository.RegistrationRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {


    private static final String TOPIC = "registration";

    private final RegistrationRepository registrationRepository;
    private final KafkaTemplate kafkaTemplate;

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

        kafkaTemplate.send(TOPIC, "The student "
                + registration.get().getStudent().getFirstName() + " " + registration.get().getStudent().getLastName()
                + " obtained " + grade.name() + " in " + registration.get().getLesson().getName());

        return ResponseEntity.ok().build();
    }
}
