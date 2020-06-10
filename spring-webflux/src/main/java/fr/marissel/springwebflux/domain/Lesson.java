package fr.marissel.springwebflux.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.time.Duration;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lesson {

    @Id
    private Integer id;
    private String name;
    private String subject;
    private Integer teacherId;
    private LocalDateTime startAt;

    // FIXME : Duration type doesn't work with h2 interval
    @Transient
    @JsonIgnore
    private Duration duration;
}
