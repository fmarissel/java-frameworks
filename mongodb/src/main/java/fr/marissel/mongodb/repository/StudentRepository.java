package fr.marissel.mongodb.repository;

import fr.marissel.mongodb.domain.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface StudentRepository extends MongoRepository<Student, BigInteger> {

    Optional<Student> findByEmail(final String email);
}
