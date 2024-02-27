package commons;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class XMLParser {

    // Método para obtener la cantidad desde un archivo XML
    public static int getQuantityFromXML(File xmlFile) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);

            Element root = document.getDocumentElement();
            NodeList quantityNodeList = root.getElementsByTagName("quantity");
            if (quantityNodeList.getLength() > 0) {
                Node quantityNode = quantityNodeList.item(0);
                return Integer.parseInt(quantityNode.getTextContent());
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Método para actualizar la cantidad en un archivo XML
    public static void updateQuantityInXML(File xmlFile, int newQuantity) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);

            Element root = document.getDocumentElement();
            NodeList quantityNodeList = root.getElementsByTagName("quantity");
            if (quantityNodeList.getLength() > 0) {
                Node quantityNode = quantityNodeList.item(0);
                quantityNode.setTextContent(Integer.toString(newQuantity));

                // Escribir los cambios de vuelta al archivo XML
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(document);

                StreamResult result = new StreamResult(xmlFile);
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.transform(source, result);
            }
        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
            e.printStackTrace();
        }
    }
}

