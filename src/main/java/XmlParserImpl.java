package main.java;

import java.util.*;

/**
 * XML Parser implementation
 * Simple XML parser for tag-based data
 */
public class XmlParserImpl implements XmlParser {

    @Override
    public Map<String, String> parseXml(String xml) {
        Map<String, String> result = new HashMap<>();

        if (xml == null || xml.trim().isEmpty()) {
            return result;
        }

        // Simple parsing by splitting tags
        String[] parts = xml.split("<[^>]+>");
        String[] tagParts = xml.split(">([^<]+)<");

        for (int i = 1; i < parts.length - 1; i += 2) {
            if (i + 1 < parts.length) {
                String tagName = extractTagName(tagParts[i]);
                String value = parts[i + 1].trim();
                if (!tagName.isEmpty() && !value.isEmpty()) {
                    result.put(tagName, value);
                }
            }
        }

        return result;
    }

    /**
     * Extract tag name from XML tag
     */
    private String extractTagName(String tag) {
        return tag.replace("<", "").replace(">", "").replace("/", "").trim();
    }

    @Override
    public String toXml(Map<String, String> data) {
        if (data == null || data.isEmpty()) {
            return "<root></root>";
        }

        StringBuilder xmlBuilder = new StringBuilder("<root>");

        for (Map.Entry<String, String> entry : data.entrySet()) {
            xmlBuilder.append("<")
                    .append(entry.getKey())
                    .append(">")
                    .append(entry.getValue())
                    .append("</")
                    .append(entry.getKey())
                    .append(">");
        }

        xmlBuilder.append("</root>");

        return xmlBuilder.toString();
    }
}