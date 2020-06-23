package fr.marissel.graphql.converter;

import org.h2.api.Interval;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.Duration;

@Converter(autoApply = true)
public class DurationConverter implements AttributeConverter<Duration, Interval> {

    @Override
    public Interval convertToDatabaseColumn(Duration duration) {

        long hours = duration.toHours();
        long minutes = duration.minusHours(hours).toMinutes();

        return Interval.ofHoursMinutes(hours, (int) minutes);
    }

    @Override
    public Duration convertToEntityAttribute(Interval interval) {

        return Duration.ofHours(interval.getHours()).plusMinutes(interval.getMinutes());
    }
}
