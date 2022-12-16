package testresult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;

public class TestResultParser {
    public static TestResult parse(String fileName) {
        int summary = 0;
        int passed = 0;
        int failed = 0;
        int skipped = 0;
        LocalDateTime startTime = null;
        String name = "";

        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new File(fileName));

            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("testsuite");

            Node node = nodeList.item(0);
            name = node.getAttributes().getNamedItem("name").getTextContent();
            summary = Integer.parseInt(node.getAttributes().getNamedItem("tests").getTextContent());
            failed = Integer.parseInt(node.getAttributes().getNamedItem("failures").getTextContent());
            skipped = Integer.parseInt(node.getAttributes().getNamedItem("skipped").getTextContent());
            startTime = LocalDateTime.parse(node.getAttributes().getNamedItem("timestamp").getTextContent());
            passed = summary - failed - skipped;

        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }

        return new TestResult(summary, passed, failed, startTime, name);
    }

    public static TestResult parse(File file) {
        int summary = 0;
        int passed = 0;
        int failed = 0;
        int skipped = 0;
        LocalDateTime startTime = null;
        String name = "";

        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);

            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("testsuite");

            Node node = nodeList.item(0);
            name = node.getAttributes().getNamedItem("name").getTextContent();
            summary = Integer.parseInt(node.getAttributes().getNamedItem("tests").getTextContent());
            failed = Integer.parseInt(node.getAttributes().getNamedItem("failures").getTextContent());
            skipped = Integer.parseInt(node.getAttributes().getNamedItem("skipped").getTextContent());
            startTime = LocalDateTime.parse(node.getAttributes().getNamedItem("timestamp").getTextContent());
            passed = summary - failed - skipped;

        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }

        return new TestResult(summary, passed, failed, startTime, name);
    }

    public static TestResult parse(Path pathToFile) {
        int summary = 0;
        int passed = 0;
        int failed = 0;
        int skipped = 0;
        LocalDateTime startTime = null;
        String name = "";

        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new File(pathToFile.toUri()));

            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("testsuite");

            Node node = nodeList.item(0);
            name = node.getAttributes().getNamedItem("name").getTextContent();
            summary = Integer.parseInt(node.getAttributes().getNamedItem("tests").getTextContent());
            failed = Integer.parseInt(node.getAttributes().getNamedItem("failures").getTextContent());
            skipped = Integer.parseInt(node.getAttributes().getNamedItem("skipped").getTextContent());
            startTime = LocalDateTime.parse(node.getAttributes().getNamedItem("timestamp").getTextContent());
            passed = summary - failed - skipped;

        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }

        return new TestResult(summary, passed, failed, startTime, name);
    }
}
