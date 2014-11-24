package hostactivator;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class HostActivator implements BundleActivator, ISettingFelix {

	private static BundleContext context;
	private static BundleRepository bundleRepository;
	
	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		HostActivator.context = bundleContext;
		context.registerService
			(ISettingFelix.class.getName(), this, null);
		bundleRepository = new BundleRepository();
	}
	
	public void stop(BundleContext bundleContext) throws Exception {
		HostActivator.context = null;
	}

	public void setFelix(Bundle felix) {
		bundleRepository.setFelix(felix);
	}
	
	public static Bundle getFelix() {
		return bundleRepository.getFelix();
	}
	
	public static Object getService(String serviceName) {
		return bundleRepository.lookupService(serviceName);		
	}

}
