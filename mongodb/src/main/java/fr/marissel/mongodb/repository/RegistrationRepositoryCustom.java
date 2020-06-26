package fr.marissel.mongodb.repository;

import fr.marissel.mongodb.domain.Grade;
import fr.marissel.mongodb.domain.Lesson;
import fr.marissel.mongodb.domain.Student;

public interface RegistrationRepositoryCustom {

    int updateRegistration(final Lesson lesson, final Student student, final Grade grade);
}
