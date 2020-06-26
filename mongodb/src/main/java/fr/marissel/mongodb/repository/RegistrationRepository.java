package fr.marissel.mongodb.repository;

import fr.marissel.mongodb.domain.Registration;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;

public interface RegistrationRepository extends MongoRepository<Registration, BigInteger>, RegistrationRepositoryCustom {
}
