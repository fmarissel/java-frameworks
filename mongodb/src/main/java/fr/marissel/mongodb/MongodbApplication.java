package fr.marissel.mongodb;

import fr.marissel.mongodb.domain.Lesson;
import fr.marissel.mongodb.domain.Student;
import fr.marissel.mongodb.domain.Subject;
import fr.marissel.mongodb.domain.Teacher;
import fr.marissel.mongodb.repository.LessonRepository;
import fr.marissel.mongodb.repository.RegistrationRepository;
import fr.marissel.mongodb.repository.StudentRepository;
import fr.marissel.mongodb.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.time.Duration;
import java.time.LocalDateTime;

@AllArgsConstructor
@SpringBootApplication
public class MongodbApplication {

    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final LessonRepository lessonRepository;
    private final RegistrationRepository registrationRepository;

    public static void main(String[] args) {
        SpringApplication.run(MongodbApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void populateDatabase() {

        // clear all datas
        registrationRepository.deleteAll();
        lessonRepository.deleteAll();
        studentRepository.deleteAll();
        teacherRepository.deleteAll();

        // insert new datas
        // teachers
        var teacherH = Teacher.builder()
                .firstName("Jean-Michel")
                .lastName("History")
                .email("jean-michel.history@yopmail.com")
                .build();
        teacherH = teacherRepository.save(teacherH);

        var teacherP = Teacher.builder()
                .firstName("Jean-Michel")
                .lastName("Philosophy")
                .email("jean-michel.philosophy@yopmail.com")
                .build();
        teacherP = teacherRepository.save(teacherP);

        var teacherM = Teacher.builder()
                .firstName("Jean-Michel")
                .lastName("Mathematics")
                .email("jean-michel.mathematics@yopmail.com")
                .build();
        teacherM = teacherRepository.save(teacherM);

        var teacherE = Teacher.builder()
                .firstName("Jean-Michel")
                .lastName("English")
                .email("jean-michel.english@yopmail.com")
                .build();
        teacherE = teacherRepository.save(teacherE);

        // students
        var jc = Student.builder()
                .firstName("Joe")
                .lastName("Chip")
                .email("joe.chip@yopmail.com")
                .build();
        jc = studentRepository.save(jc);

        var ad = Student.builder()
                .firstName("Arthur")
                .lastName("Dent")
                .email("arthur.dent@yopmail.com")
                .build();
        ad = studentRepository.save(ad);

        var hs = Student.builder()
                .firstName("Hari")
                .lastName("Seldon")
                .email("hari.seldon@yopmail.com")
                .build();
        hs = studentRepository.save(hs);

        // lessons
        var lessonH1 = Lesson.builder()
                .name("Introduction to history")
                .subject(Subject.HISTORY)
                .teacher(teacherH)
                .startAt(LocalDateTime.of(2019, 9, 16, 10, 0))
                .duration(Duration.ofMinutes(120))
                .build();
        lessonH1 = lessonRepository.save(lessonH1);

        var lessonM1 = Lesson.builder()
                .name("Mathematics level 1")
                .subject(Subject.MATHEMATICS)
                .teacher(teacherM)
                .startAt(LocalDateTime.of(2019, 9, 16, 15, 0))
                .duration(Duration.ofMinutes(150))
                .build();
        lessonM1 = lessonRepository.save(lessonM1);

        var lessonM2 = Lesson.builder()
                .name("Mathematics level 2")
                .subject(Subject.MATHEMATICS)
                .teacher(teacherM)
                .startAt(LocalDateTime.of(2019, 9, 23, 15, 0))
                .duration(Duration.ofMinutes(150))
                .build();
        lessonM2 = lessonRepository.save(lessonM2);

        var lessonM3 = Lesson.builder()
                .name("Mathematics level 3")
                .subject(Subject.MATHEMATICS)
                .teacher(null)
                .startAt(LocalDateTime.of(2019, 9, 30, 10, 0))
                .duration(Duration.ofMinutes(150))
                .build();
        lessonM3 = lessonRepository.save(lessonM3);

        var lessonE1 = Lesson.builder()
                .name("English for newbie")
                .subject(Subject.ENGLISH)
                .teacher(teacherE)
                .startAt(LocalDateTime.of(2019, 9, 30, 8, 0))
                .duration(Duration.ofMinutes(180))
                .build();
        lessonE1 = lessonRepository.save(lessonE1);
    }
}
