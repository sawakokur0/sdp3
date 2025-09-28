package main.java;

import java.util.*;

/**
 * JSON Parser implementation
 * Simple JSON parser for key-value pairs
 */
public class JsonParserImpl implements JsonParser {

    @Override
    public Map<String, String> parseJson(String json) {
        Map<String, String> result = new HashMap<>();

        if (json == null || json.trim().isEmpty()) {
            return result;
        }

        // Remove braces and quotes, then split into key-value pairs
        String cleanJson = json.replace("{", "").replace("}", "").replace("\"", "").trim();
        String[] pairs = cleanJson.split(",");

        for (String pair : pairs) {
            String[] keyValue = pair.split(":");
            if (keyValue.length == 2) {
                result.put(keyValue[0].trim(), keyValue[1].trim());
            }
        }

        return result;
    }

    @Override
    public String toJson(Map<String, String> data) {
        if (data == null || data.isEmpty()) {
            return "{}";
        }

        StringBuilder jsonBuilder = new StringBuilder("{");

        for (Map.Entry<String, String> entry : data.entrySet()) {
            jsonBuilder.append("\"")
                    .append(entry.getKey())
                    .append("\":\"")
                    .append(entry.getValue())
                    .append("\",");
        }

        // Remove last comma and close JSON
        jsonBuilder.deleteCharAt(jsonBuilder.length() - 1).append("}");

        return jsonBuilder.toString();
    }
}