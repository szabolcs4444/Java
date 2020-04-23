package task;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XmlWriter {
	public static void xmlWriter(String xml, DramaTableDesign dramaTableModel) {
		Document dom;

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		try {

			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

			dom = documentBuilder.newDocument();

			Element rootElement = dom.createElement("problems");
			dom.appendChild(rootElement);
			int rowCount = dramaTableModel.getRowCount();
			int columnCount = dramaTableModel.getColumnCount();
			for (int i = 0; i < rowCount; i++) {
				for (int j = 1; j < columnCount - 1; j += 5) {
					String identifier = dramaTableModel.getValueAt(i, 1).toString();
					String title = dramaTableModel.getValueAt(i, 2).toString();
					String director = dramaTableModel.getValueAt(i, 3).toString();
					String performanceDate = dramaTableModel.getValueAt(i, 4).toString();
					String ticketPrice = dramaTableModel.getValueAt(i, 5).toString();

					Element identifierElement = dom.createElement("Identifier");
					identifierElement.appendChild(dom.createTextNode(identifier));
					rootElement.appendChild(identifierElement);

					Element titleElement = dom.createElement("Title");
					titleElement.appendChild(dom.createTextNode(title));
					identifierElement.appendChild(titleElement);

					Element directorElement = dom.createElement("Director");
					directorElement.appendChild(dom.createTextNode(director));
					identifierElement.appendChild(directorElement);

					Element performanceDateElement = dom.createElement("PerformanceDate");
					performanceDateElement.appendChild(dom.createTextNode(performanceDate));
					identifierElement.appendChild(performanceDateElement);

					Element ticketPriceElement = dom.createElement("TicketPrice");
					ticketPriceElement.appendChild(dom.createTextNode(ticketPrice));
					identifierElement.appendChild(ticketPriceElement);
				}
			}

			try {
				Transformer transformer = TransformerFactory.newInstance().newTransformer();
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				transformer.setOutputProperty(OutputKeys.METHOD, "xml");
				transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
				transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "roles.dtd");
				transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

				transformer.transform(new DOMSource(dom), new StreamResult(new FileOutputStream(xml + ".xml")));
				JOptionPane.showMessageDialog(null, "Successful write to xml!", "Program message", 1);

			} catch (TransformerException te) {
				JOptionPane.showMessageDialog(null, te.getMessage(), "Program message", 0);

			}
		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(null, ioe.getMessage(), "Program message", 0);

		} catch (ParserConfigurationException pce) {
			JOptionPane.showMessageDialog(null, "UsersXML: Error trying to instantiate DocumentBuilder",
					"Program message", 0);
		}
	}

}
