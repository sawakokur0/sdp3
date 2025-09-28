package main.java;

import java.util.*;

/**
 * XML Parser interface
 * Adaptee interface for the Adapter pattern
 */
public interface XmlParser {
    Map<String, String> parseXml(String xml);
    String toXml(Map<String, String> data);
}