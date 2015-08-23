package context;



public class XmlApplicationContext extends AbstractApplicationContext {
		
	
	public XmlApplicationContext(String[] xmlPaths) {
		//��ʼ���ĵ���Ԫ��
		setUpElements(xmlPaths);
		//����beanʵ��
		createBeans();
	}
}
