package fr.marissel.mongodb.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "registration")
public class Registration {

    @Id
    private BigInteger id;

    @DBRef
    private Lesson lesson;

    @DBRef
    private Student student;

    private LocalDateTime registeredAt;

    private Grade grade;
}
