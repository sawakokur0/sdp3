package main.java;

import java.util.*;

/**
 * JSON Parser interface
 * Target interface for the Adapter pattern
 */
public interface JsonParser {
    Map<String, String> parseJson(String json);
    String toJson(Map<String, String> data);
}