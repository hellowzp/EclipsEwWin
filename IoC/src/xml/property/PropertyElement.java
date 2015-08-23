package xml.property;

import xml.construct.DataElement;


public class PropertyElement {

	//propertyԪ�ص�name����ֵ
	private String name;
	
	//propertyԪ���µ�ref����value���Զ���
	private DataElement dataElement;

	public PropertyElement(String name, DataElement dataElement) {
		this.name = name;
		this.dataElement = dataElement;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DataElement getDataElement() {
		return dataElement;
	}

	public void setDataElement(DataElement dataElement) {
		this.dataElement = dataElement;
	}

}
