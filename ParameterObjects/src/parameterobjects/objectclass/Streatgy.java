package parameterobjects.objectclass;

import java.lang.reflect.Method;

public class Streatgy {
	private Method method;
	private Object implObj;
	private int streatgyType;
	
	public Streatgy(Method method,Object implObj,int streatgyType) {
		this.method = method;
		this.implObj = implObj;
		this.streatgyType = streatgyType;
	}
	public Object getImplObj() {
		return implObj;
	}
	public Method getMethod() {
		return method;
	}
	public int getStreatgyType() {
		return streatgyType;
	}

}
