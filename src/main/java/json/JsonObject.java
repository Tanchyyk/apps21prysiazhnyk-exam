package json;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    private final HashMap<String, Json> obj;

    public JsonObject(JsonPair... jsonPairs) {
        obj = new HashMap<>();

        for (JsonPair pair : jsonPairs) {
            obj.put(pair.key, pair.value);
        }

    }

    @Override
    public String toJson() {
        StringBuilder result = new StringBuilder("{");

        for (String key : obj.keySet()) {
            result.append(key).append(": ").append(obj.get(key).toJson()).append(",");
        }

        StringBuilder sb = new StringBuilder(result.toString());
        sb.deleteCharAt(sb.length() - 1);

        if (result.length() < 2) {
            return "{}";
        }
        return sb.toString() + "}";
    }

    public void add(JsonPair jsonPair) {
        obj.put(jsonPair.key, jsonPair.value);
    }

    public Json find(String name) {
        for (String key : obj.keySet()) {
            if (key.equals(name)) {
                return obj.get(key);
            }

        }
        return null;
    }

    public JsonObject projection(String... names) {
        JsonObject newObj = new JsonObject();
        for (String name : names) {
            if (obj.containsKey(name)) {
                JsonPair pair = new JsonPair(name, obj.get(name));
                newObj.add(pair);
            }
        }
        return newObj;
    }
}
