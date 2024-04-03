package com.example.bootshelf.common;

import org.joda.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;

@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime locDateTime) {
        return locDateTime == null ? null : new Timestamp(locDateTime.toDate().getTime());
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp sqlTimestamp) {
        return sqlTimestamp == null ? null : new LocalDateTime(sqlTimestamp.getTime());
    }
}
