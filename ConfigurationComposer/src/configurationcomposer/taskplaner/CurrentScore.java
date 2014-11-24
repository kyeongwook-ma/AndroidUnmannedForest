package configurationcomposer.taskplaner;

import java.util.ArrayList;


public class CurrentScore {
	
	public static ArrayList optimalAgentList = new ArrayList();
	public static Double OptimalScore = new Double(0.0);
	public static ScoreSet OptimalSet;
	//public static double OptimalBenefit;
	
	public static ArrayList huristicAgentList = new ArrayList();
	public static double HuristicScore = 0.0;
	public static ScoreSet HuristicSet;
	//public static double HuristicBenefit;
}
