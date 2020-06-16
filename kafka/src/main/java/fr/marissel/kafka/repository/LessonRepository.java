package fr.marissel.kafka.repository;

import fr.marissel.kafka.domain.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {

    List<Lesson> findByTeacherId(final Integer teacherId);
}
