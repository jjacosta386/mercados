/**
 * 
 */
package co.com.bvc.portal.mercados.util;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.aop.MethodBeforeAdvice;

import co.com.bvc.portal.mercados.exception.SqlInjectionException;

/**
 * Interceptor para los daos, que chequea que los parametros que se pasan no
 * contengan caracteres raros para una inyeccion de sql
 * 
 * @author William Bernal
 * 
 */
public class CheckParamsDaoBeforeAdvice implements MethodBeforeAdvice {

	private static final String regex = "[(a-z)(A-Z)(0-9)(·ÈÌÛ˙)(¡…Õ”⁄)  %:-]*";

	public static void main(String[] args) {
		String o = "OpciÛn";
		System.out.println(o.matches(regex));
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.aop.MethodBeforeAdvice#before(java.lang.reflect.Method
	 * , java.lang.Object[], java.lang.Object)
	 */
	public void before(Method arg0, Object[] arg1, Object arg2)
			throws Throwable {
		for (int i = 0; i < arg1.length; i++) {
			hasObjectInjection(arg1[i]);
		}
	}

	private void hasObjectInjection(Object pObj) throws Exception {
		if (pObj instanceof String) {
			isStringInjection((String) pObj);
		}
		if (pObj != null && pObj.getClass().getPackage() != null){
			if (pObj.getClass().getPackage().getName().startsWith("java")) {
				return;
			} else {
				Map<?, ?> props = PropertyUtils.describe(pObj);
				for (Iterator<?> iterator = props.keySet().iterator(); iterator
						.hasNext();) {
					String prp = (String) iterator.next();
					hasObjectInjection(props.get(prp));
				}
			}
		}
	}

	private void isStringInjection(String pString) throws SqlInjectionException {
		if (!pString.matches(regex)) {
			//SqlInjectionException sqlex = new SqlInjectionException(pString);
			//throw sqlex;
		}
	}
}