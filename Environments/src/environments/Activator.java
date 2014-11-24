package environments;

import java.util.ArrayList;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import environments.service.EnvironmentsService;

public class Activator implements BundleActivator {

	private static BundleContext context;
	private static ArrayList cellList = new ArrayList();

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		EnvironmentsService environmentsService = new EnvironmentsServiceImp(context,cellList);
		Activator.context = bundleContext;
				context.registerService(EnvironmentsService.class.getName(),environmentsService , null);
		environmentsService.initEnvironment();
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}
	
	// 
	public ArrayList getCellList() {
		return cellList;
	}

}
