package fr.marissel.graphql.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
public class DataNotFoundException extends RuntimeException implements GraphQLError {

    private final String dataType;
    private final Map<String, Object> identifiers;

    @Override
    public String getMessage() {
        return dataType + " with "
                + identifiers.keySet().stream().map(key -> key + "=" + identifiers.get(key)).collect(Collectors.joining(", ", "{", "}"))
                + " could not be found";
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.ValidationError;
    }

    @Override
    public Map<String, Object> getExtensions() {
        return identifiers;
    }

    @Override
    @JsonIgnore
    public StackTraceElement[] getStackTrace() {
        return super.getStackTrace();
    }
}
