package mapebundle.services;

import java.lang.reflect.InvocationTargetException;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;


public interface IContextMonitor {
	public void activate();
	public void deActivate();
}
