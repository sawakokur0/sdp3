package main.java;

/**
 * Main demonstration class
 * Shows Adapter pattern usage for data format conversion
 */
public class Main {
    public static void main(String[] args) {
        // Initialize parsers and adapter
        JsonParser jsonParser = new JsonParserImpl();
        XmlParser xmlParser = new XmlParserImpl();
        DataParserAdapter adapter = new DataParserAdapter(jsonParser, xmlParser);

        System.out.println("=== DATA PARSER ADAPTER DEMONSTRATION ===\n");

        // Demonstration 1: XML to JSON conversion
        String xmlData = "<user><name>John Doe</name><age>30</age><email>john@example.com</email></user>";
        String jsonResult = adapter.xmlToJson(xmlData);

        System.out.println("Demonstration 1 - XML to JSON Conversion:");
        System.out.println("Input XML: " + xmlData);
        System.out.println("Output JSON: " + jsonResult);
        System.out.println();

        // Demonstration 2: JSON to XML conversion
        String jsonData = "{\"product\":\"Laptop\",\"price\":\"999.99\",\"brand\":\"Dell\"}";
        String xmlResult = adapter.jsonToXml(jsonData);

        System.out.println("Demonstration 2 - JSON to XML Conversion:");
        System.out.println("Input JSON: " + jsonData);
        System.out.println("Output XML: " + xmlResult);
        System.out.println();

        // Demonstration 3: Round-trip conversion
        System.out.println("Demonstration 3 - Round-trip Conversion:");
        String originalData = "<data><id>123</id><status>active</status></data>";
        System.out.println("Original XML: " + originalData);

        String intermediateJson = adapter.xmlToJson(originalData);
        System.out.println("Intermediate JSON: " + intermediateJson);

        String finalXml = adapter.jsonToXml(intermediateJson);
        System.out.println("Final XML: " + finalXml);

        System.out.println("\n=== RUNNING AUTOMATED TESTS ===");
        // Run automated tests
        ParserTester tester = new ParserTester(adapter);
        tester.runAllTests();
    }
}