package fr.marissel.graphql.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import fr.marissel.graphql.domain.Grade;
import fr.marissel.graphql.domain.Registration;
import fr.marissel.graphql.repository.LessonRepository;
import fr.marissel.graphql.repository.RegistrationRepository;
import fr.marissel.graphql.repository.StudentRepository;
import fr.marissel.graphql.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@AllArgsConstructor
@Component
public class RegistrationMutation implements GraphQLMutationResolver {

    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final LessonRepository lessonRepository;
    private final RegistrationRepository registrationRepository;

    public Registration register(final Integer studentId, final Integer lessonId) {

        var student = studentRepository.findById(studentId);
        var lesson = lessonRepository.findById(lessonId);

        final Registration registrationNew = new Registration();
        registrationNew.setLesson(lesson.get());
        registrationNew.setStudent(student.get());
        registrationNew.setRegisteredAt(OffsetDateTime.now());
        return registrationRepository.saveAndFlush(registrationNew);
    }

    public Registration grade(final Integer studentId, final Integer lessonId, final Grade grade) {

        var registration = registrationRepository.findByStudentIdAndLessonId(studentId, lessonId);

        registration.get().setGrade(grade);
        return registrationRepository.saveAndFlush(registration.get());
    }
}
