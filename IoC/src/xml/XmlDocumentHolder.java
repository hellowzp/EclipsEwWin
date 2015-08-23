package xml;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import xml.exception.DocumentException;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;


public class XmlDocumentHolder implements DocumentHolder {

	private Map<String, Document> docs = new HashMap<String, Document>();
	
	public Document getDocument(String filePath) {
		Document doc = this.docs.get(filePath);
		if (doc == null) {
			//ʹ��SAXReader����ȡxml�ļ�
			this.docs.put(filePath, readDocument(filePath));
		}
		return this.docs.get(filePath);
	}
	
	
	private Document readDocument(String filePath) {
		try {
			SAXReader reader = new SAXReader(true);
			reader.setEntityResolver(new IoCEntityResolver());
			File xmlFile = new File(filePath);
			Document doc = reader.read(xmlFile);
			return doc;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DocumentException(e.getMessage());
		}
	}

}
