package fr.marissel.graphql.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import fr.marissel.graphql.domain.Grade;
import fr.marissel.graphql.domain.Registration;
import fr.marissel.graphql.exception.DataAlreadyExistException;
import fr.marissel.graphql.exception.DataNotFoundException;
import fr.marissel.graphql.repository.LessonRepository;
import fr.marissel.graphql.repository.RegistrationRepository;
import fr.marissel.graphql.repository.StudentRepository;
import fr.marissel.graphql.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Component
public class RegistrationMutation implements GraphQLMutationResolver {

    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final LessonRepository lessonRepository;
    private final RegistrationRepository registrationRepository;

    public Registration register(final Integer studentId, final Integer lessonId) {

        var student = studentRepository.findById(studentId);
        if (student.isEmpty()) {
            throw new DataNotFoundException("Student", Collections.singletonMap("studentId", studentId));
        }
        var lesson = lessonRepository.findById(lessonId);
        if (student.isEmpty()) {
            throw new DataNotFoundException("Lesson", Collections.singletonMap("lessonId", lessonId));
        }

        var registration = registrationRepository.findByStudentIdAndLessonId(studentId, lessonId);
        if (registration.isPresent()) {
            final Map<String, Object> identifiers = new HashMap<>(2);
            identifiers.put("studentId", studentId);
            identifiers.put("lessonId", lessonId);
            throw new DataAlreadyExistException("Registration", identifiers);
        }

        final Registration registrationNew = new Registration();
        registrationNew.setLesson(lesson.get());
        registrationNew.setStudent(student.get());
        registrationNew.setRegisteredAt(OffsetDateTime.now());
        return registrationRepository.saveAndFlush(registrationNew);
    }

    public Registration grade(final Integer studentId, final Integer lessonId, final Grade grade) {

        var registration = registrationRepository.findByStudentIdAndLessonId(studentId, lessonId);

        if (registration.isEmpty()) {
            final Map<String, Object> identifiers = new HashMap<>(2);
            identifiers.put("studentId", studentId);
            identifiers.put("lessonId", lessonId);
            throw new DataNotFoundException("Registration", identifiers);
        }

        registration.get().setGrade(grade);
        return registrationRepository.saveAndFlush(registration.get());
    }
}
