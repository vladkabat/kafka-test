package org.epam.vladkabat.serdes;

import com.google.gson.Gson;
import java.util.Map;
import org.apache.kafka.common.serialization.Deserializer;

public class JsonDeserializer<T> implements Deserializer<T> {

  private Gson gson = new Gson();
  private Class<T> deserializedClass;

  public JsonDeserializer(Class<T> deserializedClass) {
    this.deserializedClass = deserializedClass;
  }

  public JsonDeserializer() {
  }

  @Override
  @SuppressWarnings("unchecked")
  public void configure(Map<String, ?> configs, boolean isKey) {
    if (deserializedClass == null) {
      deserializedClass = (Class<T>) configs.get("serializedClass");
    }
  }

  @Override
  public T deserialize(String s, byte[] bytes) {
    if (bytes == null) {
      return null;
    }
    return gson.fromJson(new String(bytes), deserializedClass);

  }

  @Override
  public void close() {
  }
}
