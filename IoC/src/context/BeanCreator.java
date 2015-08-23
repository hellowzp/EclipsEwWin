package context;

import java.lang.reflect.Constructor;
import java.util.List;

import xml.construct.DataElement;


public interface BeanCreator {

	
	Object createBeanUseDefaultConstruct(String className);
	
	
	Object createBeanUseDefineConstruce(String className, List<Object> args);

}
