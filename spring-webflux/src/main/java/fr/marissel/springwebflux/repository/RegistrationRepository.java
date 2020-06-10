package fr.marissel.springwebflux.repository;

import fr.marissel.springwebflux.domain.Registration;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RegistrationRepository extends ReactiveCrudRepository<Registration, Integer> {

    Mono<Registration> findByStudentIdAndLessonId(final Integer studentId, final Integer lessonId);

    Flux<Registration> findByStudentId(final Integer studentId);
}
