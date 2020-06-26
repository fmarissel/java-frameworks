package fr.marissel.mongodb.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.time.Duration;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "lesson")
public class Lesson {

    @Id
    private BigInteger id;

    @Indexed(unique = true)
    private String code;

    private String name;

    private Subject subject;

    @DBRef
    private Teacher teacher;

    private LocalDateTime startAt;

    private Duration duration;
}
