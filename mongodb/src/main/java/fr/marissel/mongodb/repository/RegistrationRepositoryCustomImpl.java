package fr.marissel.mongodb.repository;

import com.mongodb.client.result.UpdateResult;
import fr.marissel.mongodb.domain.Grade;
import fr.marissel.mongodb.domain.Lesson;
import fr.marissel.mongodb.domain.Registration;
import fr.marissel.mongodb.domain.Student;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

@AllArgsConstructor
public class RegistrationRepositoryCustomImpl implements RegistrationRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public int updateRegistration(final Lesson lesson, final Student student, final Grade grade) {

        Query query = new Query(Criteria.where("lesson").is(lesson).and("student").is(student));
        Update update = new Update();
        update.set("grade", grade);

        final UpdateResult result = mongoTemplate.updateFirst(query, update, Registration.class);

        if (result != null)
            return (int) result.getMatchedCount();
        else
            return 0;
    }
}
