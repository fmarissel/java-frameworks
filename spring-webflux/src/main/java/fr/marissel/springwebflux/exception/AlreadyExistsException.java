package fr.marissel.springwebflux.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AlreadyExistsException extends RuntimeException {
}
