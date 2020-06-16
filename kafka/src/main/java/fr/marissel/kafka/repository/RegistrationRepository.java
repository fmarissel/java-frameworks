package fr.marissel.kafka.repository;

import fr.marissel.kafka.domain.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Integer> {

    List<Registration> findByStudentId(final Integer studentId);

    Optional<Registration> findByStudentIdAndLessonId(final Integer studentId, final Integer lessonId);
}
