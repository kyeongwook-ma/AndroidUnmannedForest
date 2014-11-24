package hostactivator;

import org.osgi.framework.Bundle;
import org.osgi.framework.ServiceReference;

public class BundleRepository implements Lookup {

	private Bundle felix;

	public void setFelix(Bundle felix){
		System.out.println("BundleRepo setFelix");
		this.felix = felix;
	}

	public Bundle getFelix() {
		return felix;
	}

	public Object lookupService(String serviceName) {
		ServiceReference sr = felix.getBundleContext().getServiceReference(serviceName);
		return felix.getBundleContext().getService(sr);
	}


}
