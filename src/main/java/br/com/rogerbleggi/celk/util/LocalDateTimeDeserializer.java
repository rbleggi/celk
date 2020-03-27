package br.com.rogerbleggi.celk.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
    public LocalDateTimeDeserializer() {
    }

    public LocalDateTime deserialize(JsonParser jsonParse, DeserializationContext context) throws IOException {
        try {
            return LocalDateTime.parse(jsonParse.getText());
        } catch (DateTimeParseException var6) {
            String ISO_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            return LocalDateTime.parse(jsonParse.getText(), formatter);
        }
    }
}