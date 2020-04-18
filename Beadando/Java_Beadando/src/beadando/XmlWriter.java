package beadando;

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
	public static void XmlWRITER(String xml,DramaTM drm) {
		Document dom;
		   
	    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    try {
	       
	        DocumentBuilder db = dbf.newDocumentBuilder();
	 
	        dom = db.newDocument();
	        
	        Element rootEle = dom.createElement("problemak");
	        dom.appendChild(rootEle);
	        int rdb = drm.getRowCount();
			int cdb = drm.getColumnCount();
			for (int i = 0; i < rdb; i++) {
				for (int j = 1; j < cdb - 1; j++) {				
					String azonosito=drm.getValueAt(i, 1).toString();
					j++;
					String cim=drm.getValueAt(i, 2).toString();
					j++;
					String rendezo=drm.getValueAt(i, 3).toString();
					j++;
					String elsobemutatas=drm.getValueAt(i, 4).toString();
					j++;
					String jegyar=drm.getValueAt(i, 5).toString();
					
					Element a = dom.createElement("azonosito");
				    a.appendChild(dom.createTextNode(azonosito));
				    rootEle.appendChild(a);

				    Element c = dom.createElement("cim");
				    c.appendChild(dom.createTextNode(cim));
				    a.appendChild(c);

				    Element r = dom.createElement("rendezo");
				    r.appendChild(dom.createTextNode(rendezo));
				    a.appendChild(r);

				    Element ee = dom.createElement("elsobemutatas");
				    ee.appendChild(dom.createTextNode(elsobemutatas));
				    a.appendChild(ee);
				       
				    Element k = dom.createElement("jegyar");
				    k.appendChild(dom.createTextNode(jegyar));
				    a.appendChild(k);			    
				}
			}
	       
	    
	        
	        
	        try {
	            Transformer tr = TransformerFactory.newInstance().newTransformer();
	            tr.setOutputProperty(OutputKeys.INDENT, "yes");
	            tr.setOutputProperty(OutputKeys.METHOD, "xml");
	            tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	            tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "roles.dtd");
	            tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

	          
	            tr.transform(new DOMSource(dom), 
	            new StreamResult(new FileOutputStream(xml+".xml")));
	            SM("Sikeres kiiras XML-be!",1);
	        } catch (TransformerException te) {
	            SM(te.getMessage(),0);
	        } }catch (IOException ioe) {
	            SM(ioe.getMessage(),0);
	        } catch (ParserConfigurationException pce) {
	        SM("UsersXML: Error trying to instantiate DocumentBuilder " + pce,0);
	    }
	}
	    public static void SM(String msg,int tipus) {
	    	JOptionPane.showMessageDialog(null, msg,"Program üzenet",tipus);
	    }
	 }

	


