package es.carlos.monguering.util.sql;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
 
@Converter(autoApply = true)
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, Date> {
     
    @Override
    public Date convertToDatabaseColumn(LocalDateTime localDateTime) {
        return localDateTime == null ? null : Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
 
    @Override
    public LocalDateTime convertToEntityAttribute(Date date) {
        return date == null ? null : date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}