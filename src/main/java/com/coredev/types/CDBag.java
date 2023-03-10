package com.coredev.types;

import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.coredev.utils.XmlHelper;


public class CDBag extends HashMap<String, Object> {

	public Document toDocument() throws Exception{
		return this.toDocument("root");
	}

	public Document toDocument(String rootKey) throws Exception {
        Document doc = XmlHelper.createDocument();
        Element root = doc.createElement(rootKey);
        doc.appendChild(root);
        addElementsToDocument(doc, root, this);
        return doc;
    }

    private void addElementsToDocument(Document doc, Element parent, CDBag bag) {
        for (Entry<String, Object> entry : bag.entrySet()) {
            Element child = doc.createElement(entry.getKey());
            parent.appendChild(child);
            if (entry.getValue() instanceof CDBag) {
                addElementsToDocument(doc, child, (CDBag) entry.getValue());
            } else {
                child.setTextContent(entry.getValue().toString());
            }
        }
    }

    public static CDBag fromDocument(Document doc) {
        CDBag bag = new CDBag();
        Element root = doc.getDocumentElement();
        addElementsFromDocument(bag, root);
        return bag;
    }

    private static void addElementsFromDocument(CDBag bag, Element parent) {
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
                addElementsFromDocument(nestedBag, childElement);
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