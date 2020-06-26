package fr.marissel.mongodb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Person {

    @Id
    private BigInteger id;

    private String firstName;

    private String lastName;

    @Indexed(unique = true)
    private String email;
}
