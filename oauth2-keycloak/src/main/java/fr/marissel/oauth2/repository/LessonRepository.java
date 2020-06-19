package fr.marissel.oauth2.repository;

import fr.marissel.oauth2.domain.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {

    List<Lesson> findByTeacherId(final Integer teacherId);
}
