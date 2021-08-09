package vn.seven.stc.core.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Converter
public class ListStringConverter implements AttributeConverter<List<String>,String>{
    private static final Logger log = LoggerFactory.getLogger(ListStringConverter.class);
    private ObjectMapper mapper = new ObjectMapper();
    @Override
    public String convertToDatabaseColumn(List<String> stringObjectMap) {
        try {
            return mapper.writeValueAsString(stringObjectMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public List<String> convertToEntityAttribute(String s) {
        List<String> res = new LinkedList<>();
        if(!StringUtils.isEmpty(s)) {
            try {
                TypeReference<List<String>> typeRef = new TypeReference<List<String>>() {
                };
                res = mapper.readValue(s,typeRef);
            } catch (IOException e) {
                log.error("Error converting attribute",e);
            }
        }
        return res;

    }
}
