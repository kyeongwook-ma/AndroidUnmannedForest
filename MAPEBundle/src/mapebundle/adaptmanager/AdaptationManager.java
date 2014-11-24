package mapebundle.adaptmanager;

import hostactivator.HostActivator;

import java.util.ArrayList;
import java.util.List;

import mapebundle.cfgctlr.ConfigController;
import usabilityimprover.service.UsabilityImprover;


public class AdaptationManager implements IAdaptationManager {

	private static AdaptationManager instance;

	static {
		instance = new AdaptationManager();
	}


	public static AdaptationManager getInstance() {
		if(instance == null) {
			return new AdaptationManager();
		} else {
			return instance;
		}
	}



	public void adapt(ArrayList diagnosis,List currModel){		

		UsabilityImprover usabilityService = (UsabilityImprover) 
				HostActivator.getService(UsabilityImprover.class.getName());

		ArrayList streatgies = null;

		try {
			streatgies = usabilityService.adapt(diagnosis);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		ConfigController.getInstance().reconfigure(streatgies,currModel);
	}

}
