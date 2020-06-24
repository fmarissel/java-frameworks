package fr.marissel.graphql.config;

import graphql.language.IntValue;
import graphql.language.StringValue;
import graphql.scalars.ExtendedScalars;
import graphql.scalars.util.Kit;
import graphql.schema.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class GraphQLConfig {

    @Bean
    public GraphQLScalarType dateTimeType() {
        return ExtendedScalars.DateTime;
    }

    @Bean
    public GraphQLScalarType durationType() {
        return GraphQLScalarType.newScalar().name("Duration").description("java.time.Duration mapping").coercing(new Coercing<Duration, String>() {
            @Override
            public String serialize(final Object input) throws CoercingSerializeException {
                if (input == null) return null;
                else if (input instanceof Duration) {
                    return Long.toString(((Duration) input).toMinutes());
                } else {
                    throw new CoercingSerializeException("Expected something we can convert to 'java.time.Duration' but was '" + Kit.typeName(input) + "'.");
                }
            }

            @Override
            public Duration parseValue(final Object input) throws CoercingParseValueException {
                if (input == null) return null;
                else if (input instanceof String) {
                    return Duration.ofMinutes(Long.valueOf((String) input));
                } else if (input instanceof Long) {
                    return Duration.ofMinutes((Long) input);
                } else {
                    throw new CoercingParseValueException("Expected a 'String' but was '" + Kit.typeName(input) + "'.");
                }
            }

            @Override
            public Duration parseLiteral(final Object input) throws CoercingParseLiteralException {
                if (input == null) return null;
                if (input instanceof StringValue) {
                    return Duration.ofMinutes(Long.valueOf(((StringValue) input).getValue()));
                } else if (input instanceof IntValue) {
                    return Duration.ofMinutes(((IntValue) input).getValue().longValue());
                } else {
                    throw new CoercingParseLiteralException("Expected AST type 'StringValue' but was '" + Kit.typeName(input) + "'.");
                }
            }
        }).build();
    }
}
