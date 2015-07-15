package ua.edu.sumdu.j2ee.maxim.laba4.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.apache.log4j.Logger;


public class LoggerBeanInterceptor {
	//private static final Logger logger = Logger.getLogger("EJB");
	
	@AroundInvoke
	public Object logMethod(InvocationContext invocationContext) throws Exception {
		
		//logger.info("Invocation method " + invocationContext.getMethod().getName() + " " + invocationContext.getContextData());
		System.out.println("Invocation method " + invocationContext.getMethod().getName() + " of class " + invocationContext.getMethod().getDeclaringClass().getSimpleName()+".");
		return invocationContext.proceed();
	}

}
