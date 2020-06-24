package fr.marissel.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import fr.marissel.graphql.domain.Lesson;
import fr.marissel.graphql.domain.Registration;
import fr.marissel.graphql.exception.DataNotFoundException;
import fr.marissel.graphql.repository.LessonRepository;
import fr.marissel.graphql.repository.RegistrationRepository;
import fr.marissel.graphql.repository.StudentRepository;
import fr.marissel.graphql.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Component
public class RegistrationQuery implements GraphQLQueryResolver {

    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final LessonRepository lessonRepository;
    private final RegistrationRepository registrationRepository;

    public List<Lesson> getLessonsByTeacher(final Integer teacherId) {

        var teacher = teacherRepository.findById(teacherId);
        if (teacher.isEmpty()) {
            throw new DataNotFoundException("Teacher", Collections.singletonMap("teacherId", teacherId));
        }

        return lessonRepository.findByTeacherId(teacherId);
    }

    public List<Registration> getRegistrationsByStudent(final Integer studentId) {

        var student = studentRepository.findById(studentId);
        if (student.isEmpty()) {
            throw new DataNotFoundException("Student", Collections.singletonMap("studentId", studentId));
        }

        return registrationRepository.findByStudentId(studentId);
    }
}
