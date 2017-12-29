import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class PhoneBook {
    private HashMap<String, ArrayList<String>> elements = new HashMap<>();

    public void add(String lastName, String phoneNumber) {
        ArrayList<String> phones = elements.get(lastName);
        if (phones != null) {
            phones.add(phoneNumber);
        } else {
            phones = new ArrayList<>();
            phones.add(phoneNumber);
            elements.put(lastName, phones);
        }
    }

    public String[] get(String lastName) {
        return elements.get(lastName).toArray(new String[elements.size()]);
    }

    public void exportToXML(String fileName) {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            //root elements
            Element rootElement = doc.createElement("PhoneNumbers");
            doc.appendChild(rootElement);


            for (Map.Entry<String, ArrayList<String>> entry : elements.entrySet()) {
                String lastName = entry.getKey();
                ArrayList<String> phones = entry.getValue();

                for (String phone : phones) {
                    Element person = doc.createElement("person");
                    rootElement.appendChild(person);

                    Element name = doc.createElement("name");
                    name.appendChild(doc.createTextNode(lastName));
                    person.appendChild(name);

                    Element number = doc.createElement("number");
                    number.appendChild(doc.createTextNode(phone));
                    person.appendChild(number);
                }
            }

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult file = new StreamResult(fileName);
            transformer.transform(source, file);
            System.out.println("Exported to XML");
            
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

    private Node getEntry(Document doc, String lastName, String phoneNumber) {
        Element phoneEntry = doc.createElement("key");
        phoneEntry.setAttribute(lastName, phoneNumber);
        return phoneEntry;
    }
}
