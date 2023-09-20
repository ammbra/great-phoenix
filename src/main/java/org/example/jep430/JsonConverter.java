package org.example.jep430;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;

public class JsonConverter implements AttributeConverter<Person, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public String convertToDatabaseColumn(Person person) {
        String json;
        try {
            json = objectMapper.writeValueAsString(person);
        } catch (final JsonProcessingException e) {
            throw new RuntimeException("JSON writing error", e);
        }

        return json;
    }

    @Override
    public Person convertToEntityAttribute(String s) {
        Person person;
        try {
            person = objectMapper.readValue(s, new TypeReference<>() {});
        } catch(Exception ex) {
            throw new RuntimeException("Error while converting JSON string to Person object.", ex);
        }
        return person;
    }
}