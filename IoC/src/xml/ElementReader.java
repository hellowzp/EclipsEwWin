package xml;

import java.util.List;

import xml.autowire.Autowire;
import xml.construct.DataElement;
import xml.property.PropertyElement;
import org.dom4j.Element;


public interface ElementReader {

	
	boolean isLazy(Element element);
	
	
	List<Element> getConstructorElements(Element element);
	
	
	String getAttribute(Element element, String name);
	
	
	boolean isSingleton(Element element);
	
	
	List<Element> getPropertyElements(Element element);
	
	
	Autowire getAutowire(Element element);
	
	
	List<DataElement> getConstructorValue(Element element);
	
	
	List<PropertyElement> getPropertyValue(Element element);
}
