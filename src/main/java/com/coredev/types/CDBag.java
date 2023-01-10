package com.coredev.types;

import java.util.HashMap;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;


public class CDBag extends HashMap<String, Object> {

	public Document toDocument() throws Exception{
		return this.toDocument("root");
	}

	public Document toDocument(String rootKey) throws Exception {
        Document doc = DocumentBuilderFactory.newInstance()
                            .newDocumentBuilder()
                            .newDocument();
        Element root = doc.createElement(rootKey);
        doc.appendChild(root);
        addElement(doc, root, this);
        return doc;
    }

    private void addElement(Document doc, Element parent, CDBag bag) {
        for (Entry<String, Object> entry : bag.entrySet()) {
            Element child = doc.createElement(entry.getKey());
            parent.appendChild(child);
            if (entry.getValue() instanceof CDBag) {
                addElement(doc, child, (CDBag) entry.getValue());
            } else {
                child.setTextContent(entry.getValue().toString());
            }
        }
    }
    
    public static CDBag fromDocument(Document doc) {
        CDBag bag = new CDBag();
        Element root = doc.getDocumentElement();
        addElements(bag, root);
        return bag;
    }

    private static void addElements(CDBag bag, Element parent) {
        NodeList children = parent.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            Element childElement = (Element) child;
            String key = childElement.getTagName();
            NodeList grandChildren = childElement.getChildNodes();
            if (grandChildren.getLength() == 1 && grandChildren.item(0).getNodeType() == Node.TEXT_NODE) {
                String value = grandChildren.item(0).getNodeValue();
                bag.put(key, value);
            } else {
                CDBag nestedBag = new CDBag();
                addElements(nestedBag, childElement);
                bag.put(key, nestedBag);
            }
        }
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CDBag [");
		for(String key : this.keySet()) {
			builder.append(key);
			builder.append("=");
			builder.append(this.get(key));
			builder.append(", ");
		}
		builder.append("]");

		return builder.toString();

	}

}