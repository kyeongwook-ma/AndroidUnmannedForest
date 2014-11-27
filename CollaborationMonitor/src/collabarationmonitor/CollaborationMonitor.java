package collabarationmonitor;

import java.util.ArrayList;
import java.util.List;

import collabarationmonitor.calculater.CalculateCurrentScore;
import userbehaviormodelgenerator.service.UBMGeneratorService;

public class CollaborationMonitor implements UBMGeneratorService{

	public List genCurBM(String dirPath) {
		// TODO Auto-generated method stub
		CalculateCurrentScore calculateCurrentScore = new CalculateCurrentScore();
		double currentScore = calculateCurrentScore.getScore();
		
//		if(currentScore < 0.46)
			return new ArrayList();
//		return null;
	}

	
}
