package fr.marissel.h2.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@NoArgsConstructor
@Entity
@DiscriminatorValue("STUDENT")
public class Student extends Person {
}
