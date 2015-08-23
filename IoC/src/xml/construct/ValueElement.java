package xml.construct;


public class ValueElement implements DataElement {

	private Object value;
		
	
	public ValueElement(Object value) {
		this.value = value;
	}
	
	public String getType() {
		return "value";
	}

	public Object getValue() {
		return this.value;
	}
	

}
