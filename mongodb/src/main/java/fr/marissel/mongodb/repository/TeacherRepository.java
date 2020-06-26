package fr.marissel.mongodb.repository;

import fr.marissel.mongodb.domain.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface TeacherRepository extends MongoRepository<Teacher, BigInteger> {

    Optional<Teacher> findByEmail(final String email);
}
