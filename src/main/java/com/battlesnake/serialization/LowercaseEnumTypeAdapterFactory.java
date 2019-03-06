package com.battlesnake.serialization;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

/**
 * Maps Enums to lowercase strings.
 *
 * <p>Adapted from: <a href=
 * "https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/TypeAdapterFactory.html"
 * >TypeAdapterFactory</a>
 */
public final class LowercaseEnumTypeAdapterFactory
        implements TypeAdapterFactory {

    @SuppressWarnings("unchecked")
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        Class<T> rawType = (Class<T>) type.getRawType();
        if (!rawType.isEnum()) return null;
        final Map<String, T> constants = new HashMap<String, T>();
        for (T constant : rawType.getEnumConstants()) {
            constants.put(toLowercase(constant), constant);
        }

        return new TypeAdapter<T>() {
            public void write(JsonWriter out, T value) throws IOException {
                if (value == null) {
                    out.nullValue();
                }
                else {
                    out.value(toLowercase(value));
                }
            }

            public T read(JsonReader reader) throws IOException {
                if (reader.peek() == JsonToken.NULL) {
                    reader.nextNull();
                    return null;
                }
                else {
                    return constants.get(reader.nextString());
                }
            }
        };
    }

    private String toLowercase(Object object) {
        return object.toString().toLowerCase(Locale.US);
    }
}
