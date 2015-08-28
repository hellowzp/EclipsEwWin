package aop;

import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import aop.advice.GreetingBeforeAdvice;
import aop.advice.Waiter;

public class AdviceTest {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
									"advice-beans.xml");
		
		Waiter waiter = (Waiter) ctx.getBean("waiterTarget");
		BeforeAdvice greetingAdvice = (GreetingBeforeAdvice) ctx.getBean("greetingBefore");
		
		ProxyFactory proxy = new ProxyFactory();
		// 指定对接口进行代理以使用JDK代理，otherwise CGLIB
		proxy.setInterfaces(waiter.getClass().getInterfaces());
		proxy.setTarget(waiter);
		proxy.addAdvice(greetingAdvice);
		// create an instance of the proxy target interface
		Waiter waiterProxy = (Waiter) proxy.getProxy();
		waiterProxy.greetTo("XiWang");
		
		((AbstractApplicationContext) ctx).close();
	}

}
