package fr.marissel.mongodb.repository;

import fr.marissel.mongodb.domain.Lesson;
import fr.marissel.mongodb.domain.Registration;
import fr.marissel.mongodb.domain.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface RegistrationRepository extends MongoRepository<Registration, BigInteger>, RegistrationRepositoryCustom {

    List<Registration> findByStudent(final Student student);

    Optional<Registration> findByStudentAndLesson(final Student student, final Lesson lesson);
}
