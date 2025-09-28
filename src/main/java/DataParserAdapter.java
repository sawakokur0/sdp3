package main.java;

import java.util.*;

/**
 * Data Parser Adapter
 * Adapter pattern implementation for converting between XML and JSON formats
 * Demonstrates Clean Code principles
 */
public class DataParserAdapter {
    private final JsonParser jsonParser;
    private final XmlParser xmlParser;

    /**
     * Constructor with dependency injection
     * Clean Code: Explicit dependencies
     */
    public DataParserAdapter(JsonParser jsonParser, XmlParser xmlParser) {
        this.jsonParser = jsonParser;
        this.xmlParser = xmlParser;
    }

    /**
     * Convert XML data to JSON format
     * Clean Code: Descriptive method names
     */
    public String xmlToJson(String xml) {
        Map<String, String> data = xmlParser.parseXml(xml);
        return jsonParser.toJson(data);
    }

    /**
     * Convert JSON data to XML format
     * Clean Code: Single responsibility
     */
    public String jsonToXml(String json) {
        Map<String, String> data = jsonParser.parseJson(json);
        return xmlParser.toXml(data);
    }

    /**
     * Universal data parsing method
     * Clean Code: Error handling with meaningful messages
     */
    public Map<String, String> parseData(String data, String format) {
        if (data == null || data.trim().isEmpty()) {
            return new HashMap<>();
        }

        switch (format.toLowerCase()) {
            case "json":
                return jsonParser.parseJson(data);
            case "xml":
                return xmlParser.parseXml(data);
            default:
                throw new IllegalArgumentException("Unsupported format: " + format);
        }
    }
}