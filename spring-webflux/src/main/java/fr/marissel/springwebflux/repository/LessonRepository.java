package fr.marissel.springwebflux.repository;

import fr.marissel.springwebflux.domain.Lesson;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface LessonRepository extends ReactiveCrudRepository<Lesson, Integer> {
    
    Flux<Lesson> findByTeacherId(final Integer teacherId);
}
