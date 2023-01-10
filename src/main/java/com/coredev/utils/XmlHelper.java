package com.coredev.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XmlHelper {
    public static void dump(Document document,OutputStream out) throws TransformerException {
		TransformerFactory factory=TransformerFactory.newInstance();
		Transformer transformer=factory.newTransformer();
		DOMSource source=new DOMSource(document);
		StreamResult result=new StreamResult(out);
		transformer.transform(source, result);
	}

	public static Document parse(InputStream in) throws IOException, ParserConfigurationException, SAXException {
		DocumentBuilderFactory factory=DocumentBuilderFactory.newDefaultInstance();
		DocumentBuilder builder=factory.newDocumentBuilder();
		Document document=builder.parse(in);
		return document;
	}

    public static Document createDocument() throws ParserConfigurationException {
        DocumentBuilder builder = DocumentBuilderFactory.newDefaultInstance().newDocumentBuilder();
		Document document = builder.newDocument();
		return document;
	}
}
