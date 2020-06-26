package fr.marissel.mongodb.repository;

import fr.marissel.mongodb.domain.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;

public interface StudentRepository extends MongoRepository<Student, BigInteger> {
}
