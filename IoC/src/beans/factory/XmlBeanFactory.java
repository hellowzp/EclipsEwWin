package beans.factory;

import context.AbstractApplicationContext;

public class XmlBeanFactory extends AbstractApplicationContext {

	public XmlBeanFactory(String[] xmlPaths) {
		super.setUpElements(xmlPaths);
	}
}
