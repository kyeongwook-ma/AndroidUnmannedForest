package configurationcomposer;

import java.util.ArrayList;

import configurationcomposer.taskplaner.ConfigurationScoreCalculator;
import usabilityimprover.service.UsabilityImprover;

public class ConfigurationComposer implements UsabilityImprover{

	public ArrayList adapt(ArrayList diagnosis) {
		// TODO Auto-generated method stub
		ConfigurationScoreCalculator configurationScoreCalculator = new ConfigurationScoreCalculator();
		
		return configurationScoreCalculator.getOptimalConfiguration();
	}

}
