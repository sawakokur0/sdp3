package main.java;

/**
 * Automated tester for Data Parser Adapter
 * Comprehensive testing of all adapter functionality
 */
public class ParserTester {
    private final DataParserAdapter adapter;

    public ParserTester(DataParserAdapter adapter) {
        this.adapter = adapter;
    }

    /**
     * Run all test cases
     */
    public void runAllTests() {
        System.out.println("=== DATA PARSER ADAPTER TEST SUITE ===\n");

        testXmlToJsonConversion();
        testJsonToXmlConversion();
        testEmptyDataHandling();
        testInvalidDataHandling();
        testRoundTripConversion();

        System.out.println("=== TEST SUITE COMPLETED ===");
    }

    private void testXmlToJsonConversion() {
        System.out.println("Test 1: XML to JSON Conversion");

        String xmlInput = "<user><name>Alice</name><age>25</age><city>London</city></user>";
        String expectedJson = "{\"name\":\"Alice\",\"age\":\"25\",\"city\":\"London\"}";

        String actualJson = adapter.xmlToJson(xmlInput);

        System.out.println("Input XML: " + xmlInput);
        System.out.println("Expected JSON: " + expectedJson);
        System.out.println("Actual JSON: " + actualJson);
        System.out.println("Test Result: " + (actualJson.equals(expectedJson) ? "PASSED" : "FAILED"));
        System.out.println();
    }

    private void testJsonToXmlConversion() {
        System.out.println("Test 2: JSON to XML Conversion");

        String jsonInput = "{\"product\":\"Phone\",\"price\":\"499\",\"color\":\"blue\"}";
        String expectedXml = "<root><product>Phone</product><price>499</price><color>blue</color></root>";

        String actualXml = adapter.jsonToXml(jsonInput);

        System.out.println("Input JSON: " + jsonInput);
        System.out.println("Expected XML: " + expectedXml);
        System.out.println("Actual XML: " + actualXml);
        System.out.println("Test Result: " + (actualXml.equals(expectedXml) ? "PASSED" : "FAILED"));
        System.out.println();
    }

    private void testEmptyDataHandling() {
        System.out.println("Test 3: Empty Data Handling");

        String emptyJson = adapter.xmlToJson("");
        String emptyXml = adapter.jsonToXml("");

        System.out.println("Empty XML to JSON: " + emptyJson);
        System.out.println("Empty JSON to XML: " + emptyXml);

        boolean testPassed = emptyJson.equals("{}") && emptyXml.equals("<root></root>");
        System.out.println("Test Result: " + (testPassed ? "PASSED" : "FAILED"));
        System.out.println();
    }

    private void testInvalidDataHandling() {
        System.out.println("Test 4: Invalid Data Handling");

        String invalidXml = "this is not valid xml";
        String invalidJson = "this is not valid json";

        String result1 = adapter.xmlToJson(invalidXml);
        String result2 = adapter.jsonToXml(invalidJson);

        System.out.println("Invalid XML result: " + result1);
        System.out.println("Invalid JSON result: " + result2);
        System.out.println("Test Result: " + (!result1.isEmpty() && !result2.isEmpty() ? "PASSED" : "FAILED"));
        System.out.println();
    }

    private void testRoundTripConversion() {
        System.out.println("Test 5: Round-trip Conversion");

        String originalXml = "<data><id>1001</id><name>Test Item</name><active>true</active></data>";

        String jsonIntermediate = adapter.xmlToJson(originalXml);
        String finalXml = adapter.jsonToXml(jsonIntermediate);

        System.out.println("Original XML: " + originalXml);
        System.out.println("Intermediate JSON: " + jsonIntermediate);
        System.out.println("Final XML: " + finalXml);

        // Check if essential data is preserved
        boolean dataPreserved = finalXml.contains("1001") &&
                finalXml.contains("Test Item") &&
                finalXml.contains("true");

        System.out.println("Test Result: " + (dataPreserved ? "PASSED" : "FAILED"));
        System.out.println();
    }

    /**
     * Main method to run tests independently
     */
    public static void main(String[] args) {
        JsonParser jsonParser = new JsonParserImpl();
        XmlParser xmlParser = new XmlParserImpl();
        DataParserAdapter adapter = new DataParserAdapter(jsonParser, xmlParser);

        ParserTester tester = new ParserTester(adapter);
        tester.runAllTests();
    }
}