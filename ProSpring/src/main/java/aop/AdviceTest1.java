package aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import aop.advice.Waiter;

public class AdviceTest1 {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"advice-beans-1.xml");

		Waiter waiter = (Waiter) ctx.getBean("waiterProxy");
		waiter.greetTo("XiWang");

		((AbstractApplicationContext) ctx).close();
	}

}
