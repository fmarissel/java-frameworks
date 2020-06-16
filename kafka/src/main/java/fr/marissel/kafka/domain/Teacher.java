package fr.marissel.kafka.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@NoArgsConstructor
@Entity
@DiscriminatorValue("TEACHER")
public class Teacher extends Person {
}
