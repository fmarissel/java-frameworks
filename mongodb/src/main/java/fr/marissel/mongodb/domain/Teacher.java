package fr.marissel.mongodb.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Document(collection = "teacher")
public class Teacher extends Person {

    @Builder
    public Teacher(final BigInteger id, final String firstName, final String lastName, final String email) {
        super(id, firstName, lastName, email);
    }
}
