package utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.xpath.*;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import static javax.xml.xpath.XPathConstants.NODE;
import static javax.xml.xpath.XPathConstants.NODESET;

public class XMLWorker {

    private static void multiplyPrice (NodeList list, int factor){

        for(int i = 0; i < list.getLength(); i++){
            Element element = (Element) list.item(i);

            element.setTextContent(String.valueOf( Integer.parseInt( element.getTextContent().toString()) * factor ));
        }
    }
    public static void gradePrice( ){
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse("input.xml");
            NodeList mins = document.getElementsByTagName("Min");
            NodeList maxs = document.getElementsByTagName("Max");
            multiplyPrice(mins, 10);
            multiplyPrice(maxs, 10);
            writeDocument(document);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Data getData() {
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse("other.xml");

            XPathFactory xpathFactory = XPathFactory.newInstance();
            XPath xpath = xpathFactory.newXPath();
            Node max = (Node) xpath.compile("//Price/Max").evaluate(document, NODE);
            NodeList names = (NodeList) xpath
                    .compile("//Manufacturer[contains(@products, 'notebook')]/Name")
                    .evaluate(document, NODESET);
            NodeList mins = (NodeList) xpath
                    .compile("//Manufacturer[contains(@products, 'notebook')]/PriceLimit/Min")
                    .evaluate(document, NODESET);

            NodeList maxs = (NodeList) xpath
                    .compile("//Manufacturer[contains(@products, 'notebook')]/PriceLimit/Max")
                    .evaluate(document, NODESET);
            ArrayList<UtilNoteBook> noteBooks = new ArrayList<>();
            for(int i = 0; i < mins.getLength(); i++ ){
                UtilNoteBook noteBook = new UtilNoteBook(
                        ((Element)names.item(i)).getTextContent().toString(),
                        Integer.parseInt( ((Element)mins.item(i)).getTextContent().toString() ),
                        Integer.parseInt( ((Element)maxs.item(i)).getTextContent().toString() )
                );
                System.out.println(noteBook.toString());
                noteBooks.add(noteBook);
            }

            return new Data( Integer.parseInt(((Element)max).getTextContent().toString() ), noteBooks);

        }
        catch (ParserConfigurationException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static void writeDocument(Document document) throws TransformerFactoryConfigurationError {
        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            FileOutputStream fos = new FileOutputStream("other.xml");
            StreamResult result = new StreamResult(fos);
            tr.transform(source, result);
        } catch (TransformerException | IOException e) {
            e.printStackTrace(System.out);
        }
    }


}
