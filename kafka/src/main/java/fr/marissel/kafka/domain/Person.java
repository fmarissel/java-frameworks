package fr.marissel.kafka.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "person_type",
        discriminatorType = DiscriminatorType.STRING)
public abstract class Person {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "person_type", nullable = false, insertable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private PersonType personType;

    @Column
    private String firstName;

    @Column
    private String lastName;
}
