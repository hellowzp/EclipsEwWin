package xml.construct;


public class RefElement implements DataElement {

	private Object value;
	
	public RefElement(Object value) {
		this.value = value;
	}
	
	public String getType() {
		// TODO Auto-generated method stub
		return "ref";
	}

	public Object getValue() {
		return this.value;
	}

}
