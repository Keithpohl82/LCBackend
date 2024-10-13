package com.launchcode.LCBackend.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class CustomLocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
    // Define your date-time pattern here. Adjust the pattern to match your specific needs.
    private static final DateTimeFormatter FORMATTER = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern("MMMM d, yyyy 'at' h:mma 'UTC'")
            .toFormatter(Locale.ENGLISH);

    private static final DateTimeFormatter FORMATTER_EXTENDED = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern("MMMM d, yyyy 'at'h:mma 'UTC'")
            .toFormatter(Locale.ENGLISH);

    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String date = jsonParser.getText();
        LocalDateTime parsedDateTime = null;
        try {
            parsedDateTime = LocalDateTime.parse(date, FORMATTER);
        } catch (DateTimeParseException e) {
            parsedDateTime = LocalDateTime.parse(date, FORMATTER_EXTENDED);
        }

        return parsedDateTime;
    }
}