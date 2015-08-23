package context;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import context.exception.BeanCreateException;
import xml.DocumentHolder;
import xml.ElementLoader;
import xml.ElementLoaderImpl;
import xml.ElementReader;
import xml.ElementReaderImpl;
import xml.XmlDocumentHolder;
import xml.autowire.Autowire;
import xml.autowire.ByNameAutowire;
import xml.autowire.NoAutowire;
import xml.construct.DataElement;
import xml.construct.RefElement;
import xml.construct.ValueElement;
import xml.property.PropertyElement;

import org.dom4j.Document;
import org.dom4j.Element;


public abstract class AbstractApplicationContext implements ApplicationContext {

	protected ElementLoader elementLoader = new ElementLoaderImpl();
	
	protected DocumentHolder documentHolder = new XmlDocumentHolder();
	
	protected Map<String, Object> beans = new HashMap<String, Object>();
	
	protected PropertyHandler propertyHandler = new PropertyHandlerImpl();
		
	protected BeanCreator beanCreator = new BeanCreatorImpl();

	protected ElementReader elementReader = new ElementReaderImpl();
	
	protected void setUpElements(String[] xmlPaths) {
		try {			
			URL classPathUrl = AbstractApplicationContext.class.getClassLoader().getResource(".");
			String classPath = java.net.URLDecoder.decode(classPathUrl.getPath(),"utf-8");
			for (String path : xmlPaths) {
				Document doc = documentHolder.getDocument(classPath + path);
				elementLoader.addElements(doc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void createBeans() {
		Collection<Element> elements = elementLoader.getElements();
		for (Element e : elements) {
			boolean lazy = elementReader.isLazy(e);		
			if (!lazy) {
				String id = e.attributeValue("id");
				Object bean = this.getBean(id);
				if (bean == null) {				
					handleSingleton(id);
				}
			}
		}
	}
	
	protected Object handleSingleton(String id) {
		Object bean = createBean(id);;
		if (isSingleton(id)) {			
			this.beans.put(id, bean);
		}
		return bean;
	}
	
	protected Object createBean(String id) {
		Element e = elementLoader.getElement(id);
		if (e == null)
			throw new BeanCreateException("element not found " + id);
		
		Object result = instance(e);
		System.out.println("create new bean with id : " + id);
		
		Autowire autowire = elementReader.getAutowire(e);
		if (autowire instanceof ByNameAutowire) {		
			autowireByName(result);
		} else if (autowire instanceof NoAutowire) {			
			setterInject(result, e);
		}
		return result;
	}

	protected Object instance(Element e) {
		String className = elementReader.getAttribute(e, "class");	
		List<Element> constructorElements = elementReader.getConstructorElements(e);		
		if (constructorElements.size() == 0) {
			return beanCreator.createBeanUseDefaultConstruct(className);
		} else {			
			List<Object> args = getConstructArgs(e);
			return beanCreator.createBeanUseDefineConstruce(className, args);
		}
	}

	protected void setterInject(Object obj, Element e) {
		List<PropertyElement> properties = elementReader.getPropertyValue(e);
		Map<String, Object> propertiesMap = getPropertyArgs(properties);
		propertyHandler.setProperties(obj, propertiesMap);
	}
	
	protected Map<String, Object> getPropertyArgs(List<PropertyElement> properties) {
		Map<String, Object> result = new HashMap<String, Object>();
		for (PropertyElement p : properties) {
			DataElement de = p.getDataElement();
			if (de instanceof RefElement) {
				
				result.put(p.getName(), this.getBean((String)de.getValue()));
			} else if (de instanceof ValueElement) {
				result.put(p.getName(), de.getValue());
			}
		}
		return result;
	}
	
	protected List<Object> getConstructArgs(Element e) {
		List<DataElement> datas = elementReader.getConstructorValue(e);
		List<Object> result = new ArrayList<Object>();
		for (DataElement d : datas) {
			if (d instanceof ValueElement) {
				d = (ValueElement)d;
				result.add(d.getValue());
			} else if (d instanceof RefElement) {				
				d = (RefElement)d;
				String refId = (String)d.getValue();
				result.add(this.getBean(refId));
			}
		}
		return result;
	}
	
	protected void autowireByName(Object obj) {
		Map<String, Method> methods = propertyHandler.getSetterMethodsMap(obj);
		for (String s : methods.keySet()) {		
			Element e = elementLoader.getElement(s);			
			if (e == null) continue;			
			Object bean = this.getBean(s);		
			Method method = methods.get(s);
			propertyHandler.executeMethod(obj, bean, method);
		}
	}
	
	public boolean containsBean(String id) {		
		Element e = elementLoader.getElement(id);
		return (e == null) ? false : true;
	}

	public Object getBean(String id) {
		Object bean = this.beans.get(id);		
		if (bean == null) {
			bean = handleSingleton(id);
		}
		return bean;
	}

	public boolean isSingleton(String id) {
		Element e = elementLoader.getElement(id);
		return elementReader.isSingleton(e);
	}

	public Object getBeanIgnoreCreate(String id) {
		return this.beans.get(id);
	}
}
