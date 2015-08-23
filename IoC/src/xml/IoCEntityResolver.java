package xml;

import java.io.IOException;
import java.io.InputStream;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


public class IoCEntityResolver implements EntityResolver {

	public InputSource resolveEntity(String publicId, String systemId)
			throws SAXException, IOException {
		//�ӱ���Ѱ��dtd
		if ("http://www.crazyit.org/beans.dtd".equals(systemId)) {
			InputStream stream = IoCEntityResolver.class.
			getResourceAsStream("/org/crazyit/ioc/beans/beans.dtd");
			return new InputSource(stream);
		} else {
			return null;
		}
	}

}
