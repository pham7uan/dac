package vn.seven.stc.logs.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Converter
public class ValueChangesConverter implements AttributeConverter<List<CustomValueChange>,String> {
    private static final Logger log = LoggerFactory.getLogger(ValueChangesConverter.class);
    private ObjectMapper objectMapper = new ObjectMapper();
    private Javers javers = JaversBuilder.javers().build();

    @Override
    public String convertToDatabaseColumn(List<CustomValueChange> valueChanges) {
        String json = "";
        try {
            json = objectMapper.writeValueAsString(valueChanges);
        } catch (JsonProcessingException e) {
            log.error("Cannot convert to db column",e);
        }
        return json;
    }

    @Override
    public List<CustomValueChange> convertToEntityAttribute(String s) {
        List<CustomValueChange> changes = new ArrayList<>();
        try {
            changes = objectMapper.readValue(s,new TypeReference<List<CustomValueChange>>(){});
        } catch (IOException e) {
            log.error("Cannot convert to entity attribue",e);
        }
        return changes;
    }
}
