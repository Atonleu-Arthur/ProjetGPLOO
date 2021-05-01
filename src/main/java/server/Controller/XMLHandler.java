package server.Controller;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.SAXException;

import org.w3c.dom.*;
import java.io.IOException;
import java.io.File;



public class XMLHandler {
	TransformerFactory transformerFactory;
	Transformer transformer;
	DocumentBuilderFactory documentFactory;
	DocumentBuilder documentBuilder;
	/**
	 * Création d'un objet log pour
	 * l'ecriture des erreurs dans
	 * fichier horodatés
	 */
	Logs logs = new Logs();

	public XMLHandler() {
		try {
			transformerFactory = TransformerFactory.newInstance();
			transformer = transformerFactory.newTransformer();
			documentFactory = DocumentBuilderFactory.newInstance();
			documentBuilder = documentFactory.newDocumentBuilder();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		}
	}

	public void createXMLFile(Document document, String filePath)
	{
		try {
			// create the xml file
			//transform the DOM Object to an XML File
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(new File(filePath));

			// If you use
			// StreamResult result = new StreamResult(System.out);
			// the output will be pushed to the standard output ...
			// You can use that for debugging

			transformer.transform(domSource, streamResult);

		} catch (TransformerException tfe) {
			tfe.printStackTrace();
			/**
			 * logWrite error
			 */
			logs.writeError("[SERVER]: "+tfe.getMessage());
		}
	}

	public Document createXMLDocument()
	{
		return documentBuilder.newDocument();
	}

	public NodeList parseXMLFile (String filePath) {
		NodeList elementNodes = null;
		try {
			Document document= documentBuilder.parse(new File(filePath));
			Element root = document.getDocumentElement();

			elementNodes = root.getChildNodes();
		}
		catch (SAXException e)
		{
			e.printStackTrace();
			/**
			 * logWrite error
			 */
			logs.writeError("[SERVER]: "+e.getMessage());
		}
		catch (IOException e)
		{
			e.printStackTrace();
			/**
			 * logWrite error
			 */
			logs.writeError("[SERVER]: "+e.getMessage());
		}
		return elementNodes;
	}



}