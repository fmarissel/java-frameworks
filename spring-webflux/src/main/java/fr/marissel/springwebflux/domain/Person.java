package fr.marissel.springwebflux.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    private Integer id;
    private String personType;
    private String firstName;
    private String lastName;
}
