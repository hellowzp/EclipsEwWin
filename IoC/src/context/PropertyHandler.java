package context;

import java.lang.reflect.Method;
import java.util.Map;

public interface PropertyHandler {

	Object setProperties(Object obj, Map<String, Object> properties);	
	
	Map<String, Method> getSetterMethodsMap(Object obj);
		
	void executeMethod(Object object, Object argBean, Method method);	
}
