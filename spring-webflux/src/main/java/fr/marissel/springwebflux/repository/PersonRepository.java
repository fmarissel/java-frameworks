package fr.marissel.springwebflux.repository;

import fr.marissel.springwebflux.domain.Person;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface PersonRepository extends ReactiveCrudRepository<Person, Integer> {

    Mono<Person> findByIdAndPersonType(final Integer id, final String personType);
}
