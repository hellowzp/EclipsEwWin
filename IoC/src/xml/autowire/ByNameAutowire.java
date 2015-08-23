package xml.autowire;


public class ByNameAutowire implements Autowire {
	private String value;
	public ByNameAutowire(String value) {
		this.value = value;
	}
	public String getValue() {
		return value;
	}
}
