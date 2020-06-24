package fr.marissel.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import fr.marissel.graphql.domain.Lesson;
import fr.marissel.graphql.domain.Registration;
import fr.marissel.graphql.repository.LessonRepository;
import fr.marissel.graphql.repository.RegistrationRepository;
import fr.marissel.graphql.repository.StudentRepository;
import fr.marissel.graphql.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class RegistrationQuery implements GraphQLQueryResolver {

    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final LessonRepository lessonRepository;
    private final RegistrationRepository registrationRepository;

    public List<Lesson> getLessonsByTeacher(final Integer teacherId) {
        return lessonRepository.findByTeacherId(teacherId);
    }

    public List<Registration> getRegistrationsByStudent(final Integer studentId) {
        return registrationRepository.findByStudentId(studentId);
    }
}
