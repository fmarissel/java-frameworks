package fr.marissel.oauth2.repository;

import fr.marissel.oauth2.domain.Student;
import fr.marissel.oauth2.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    Optional<Teacher> findByEmail(String email);
}
