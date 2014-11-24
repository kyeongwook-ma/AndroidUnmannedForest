package mapebundle.adaptreasoner;

import hostactivator.HostActivator;

import java.util.ArrayList;
import java.util.List;

import mapebundle.adaptmanager.AdaptationManager;
import badsymptomchecker.services.BadSymptomService;

/**
 * Explanation : 
 * @version : 2014. 5. 12.
 * @author : se
 * AdaptationReasoner / package adaptationreasoner;
 */

public class AdaptationReasoner implements IAdaptationReasoner {

	private static AdaptationReasoner instance;

	static {
		instance = new AdaptationReasoner();
	}


	private AdaptationReasoner() {
	}

	public static AdaptationReasoner getInstance() {
		if(instance == null) {
			return new AdaptationReasoner();
		} else {
			return instance;
		}
	}


	public void reason(String desginedModel,List currModel)  {
		BadSymptomService badSymptomService = 
				(BadSymptomService) HostActivator
				.getService(BadSymptomService.class.getName());

		ArrayList diagnosis = badSymptomService.reason(currModel,desginedModel);
		//badSymptom call diagnosis 초기??		System.out.println("2. reasoning : diagnosis ?�출  : "+ diagnosis.getRuleSetList().get(0));

		AdaptationManager.getInstance().adapt(diagnosis,currModel);
		//결과�?리턴 �?manager 불러?�기
	}

}
