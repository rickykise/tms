package ai.fassto.tms.common.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDate;

public class JsonUtil {
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .create();

    private JsonUtil() {
    }

    public static String toJson(Object target) {
        return gson.toJson(target);
    }

    public static <T> T toObject(String json, Class<T> valueType) {
        return gson.fromJson(json, valueType);
    }
}
