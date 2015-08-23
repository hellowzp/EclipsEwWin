package xml;

import java.util.Collection;

import org.dom4j.Document;
import org.dom4j.Element;


public interface ElementLoader {
	
	
	void addElements(Document doc);
	
	
	Element getElement(String id);
	
	
	Collection<Element> getElements();
}
