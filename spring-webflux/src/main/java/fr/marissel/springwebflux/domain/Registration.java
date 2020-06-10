package fr.marissel.springwebflux.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Registration {

    @Id
    private Integer id;
    private Integer lessonId;
    private Integer studentId;
    private LocalDateTime registeredAt;
    private String grade;
}
