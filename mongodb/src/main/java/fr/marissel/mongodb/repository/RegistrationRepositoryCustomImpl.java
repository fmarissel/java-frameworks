package fr.marissel.mongodb.repository;

import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;

@AllArgsConstructor
public class RegistrationRepositoryCustomImpl implements RegistrationRepositoryCustom {

    private final MongoTemplate mongoTemplate;
}
