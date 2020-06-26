package fr.marissel.mongodb.repository;

import fr.marissel.mongodb.domain.Lesson;
import fr.marissel.mongodb.domain.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface LessonRepository extends MongoRepository<Lesson, BigInteger> {

    List<Lesson> findByTeacher(final Teacher teacher);

    Optional<Lesson> findByCode(final String code);
}
