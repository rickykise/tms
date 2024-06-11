package ai.fassto.tms.common.util;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateAdapter extends TypeAdapter<LocalDate> {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    @Override
    public void write(JsonWriter out, LocalDate value) throws IOException {
        if (value == null) {
            out.nullValue();
        } else {
            String formattedDate = value.format(formatter);
            out.value(formattedDate);
        }
    }

    @Override
    public LocalDate read(JsonReader in) throws IOException {
        if (in != null) {
            String dateStr = in.nextString();
            return LocalDate.parse(dateStr, formatter);
        }
        return null;
    }
}