package configurationcomposer.taskplaner;

import hostactivator.HostActivator;







import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Iterator;
////import java.util.List;
//import java.util.Vector;







//import org.paukov.combinatorics.Factory;
//import org.paukov.combinatorics.Generator;
//import org.paukov.combinatorics.ICombinatoricsVector;
import configurationcomposer.policy.MonitorForestStatusPolicy;
import agents.service.AgentInstancesService;
import agents.variable.BaseAgent;
import environments.service.EnvironmentsService;
import environments.variable.ForestCell;

public class ConfigurationScoreCalculator {
	
	private AgentInstancesService agentInstances;
	private ArrayList cellList;	
//	private static final String MIN = "Min:";	
//	private HashMap valueSet = new HashMap();
//	private HashMap minScoreSet = new HashMap();
//	private Vector threadList = new Vector();
//	private int Threadcount;
	private static int cellSize = 9;
	private ArrayList iniList;
	private ArrayList curList;
	private ArrayList optList;
	// private ArrayList<BaseAgent> huristicDeploy;
	public ArrayList minList;
	public ArrayList maxList;
//	private double optBenefit;
//	private double optCost;
	private double optScore;
	private double prevScore;
	private double curScore;
	private double[][] costMatrix;
	private double[][] benefitMatrix;
	private double[][] scoreMatrix;
	private double monitoringDistance;

	boolean swit = true;
	
	public ConfigurationScoreCalculator() {
		// TODO Auto-generated constructor stub
		System.out.println("1");
		agentInstances = (AgentInstancesService) HostActivator.getService(AgentInstancesService.class.getName());
		System.out.println("2");
		EnvironmentsService envService = (EnvironmentsService) HostActivator.getService(EnvironmentsService.class.getName());
		System.out.println("3");
		cellList = envService.getCellList();
		System.out.println("4");
		monitoringDistance = envService.getMonitoringDistance();
//		System.out.println("5");
//		threadList = new Vector();
//		valueSet = new HashMap();
//		minScoreSet = new HashMap();
		
		optList = new ArrayList();
		minList = new ArrayList();
		maxList = new ArrayList();
		maxList = new ArrayList();
		minList = new ArrayList();
		System.out.println("6");
		iniList = agentInstances.getAgentList();
		System.out.println("7");
		curList = agentInstances.getAgentList();
		System.out.println("8");
		costMatrix = new double[9][11];
		benefitMatrix = new double[9][11];
		scoreMatrix = new double[9][11];
	}
	
	/////////////////////////////////////////////////////////////////
	
	
	public ArrayList getOptimalConfiguration(){
		System.out.println("getOptimalConfiguration start");	
		System.err.println("getOptimalConfiguration start");
	
		optScore = 0;
		prevScore = 0;
		curScore = 0;
		this.costMatrix = this.makeCostMatrix();
		this.benefitMatrix = this.makeBenefitMatrix();
		this.scoreMatrix = this.makeScoreMatrix(costMatrix, benefitMatrix);

		for (int i = 0; i < iniList.size(); i++) {
			curList.set(i, iniList.get(i));
			optList.add(iniList.get(i));
		}

		for (int i = 0; i < 181440; i++) {
			if (this.placementTest(curList)) {
				curScore = this.getScoreValueFromMatrix(curList);
//				System.out.println(curScore);
				if (prevScore < curScore) {
					for (int j = 0; j < curList.size(); j++) {
						optList.set(j, curList.get(j));
					}
					prevScore = curScore;
					optScore = curScore;
					CurrentScore.OptimalScore = new Double(optScore);
				}// end of inner if
			} else {
				curScore = 0;
			}
			curList = this.next_permutation(curList);
			curList = this.next_permutation(curList);
		}// end of for
		System.out.println("getOptimalConfiguration finish");
		System.err.println("getOptimalConfiguration finish");
		
		System.out.println(optScore);

		
		
		//draw Agents
		Object drawUI = HostActivator.getService("selab.dev.unmannedforestmonitor.uiservice.DrawService");
//		drawAgents(Integer cellNum,String agentID,String agentType)
		
		Class[] paramClassSelecetedText = {String.class,String.class};
		
		ArrayList optListNames = new ArrayList();
		
		for(int i=0;i<9;i++)
			optListNames.add(((BaseAgent)curList.get(i)).getAgentID());
		
		Object[] paramSelecetedText = {optListNames.toString(),String.valueOf(optScore)};
		
		Class[] paramClassDrawAgents = {Integer.class,String.class,String.class};
		
		Integer cellNum = new Integer(0);
		
		try {
			Method drawSelectedText = drawUI.getClass().getDeclaredMethod("drawSelected", paramClassSelecetedText);
			try {
				drawSelectedText.invoke(drawUI, paramSelecetedText);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Method drawAgents = drawUI.getClass().getDeclaredMethod("drawAgents", paramClassDrawAgents);
			
			for(int i=0;i<9;i++)
			{
				Object[] paramDrawAgents = {new Integer(i),((BaseAgent) (curList.get(i))).getAgentID(),((BaseAgent) (curList.get(i))).getAgentType()};
				try {
					drawAgents.invoke(drawUI, paramDrawAgents);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

		return optList;
		
	}
	
	public ArrayList next_permutation(ArrayList staticList) {
		System.out.println("next_permutation start");
		System.err.println("next_permutation start");
		int i = 0, j = 0;
		curList = staticList;

		for (i = curList.size() - 2; i >= 0; i--) {
			if (Integer.parseInt((String)((BaseAgent) curList.get(i)).getAgentID()) < Integer
					.parseInt((String)((BaseAgent) curList.get(i + 1)).getAgentID()))
				break;
		}
		if (i < 0) {
			System.out.println("end");
			return curList;
		}

		for (j = curList.size() - 1; j > i; j--) {
			if (Integer.parseInt((String)((BaseAgent) curList.get(j)).getAgentID()) > Integer
					.parseInt((String)((BaseAgent) curList.get(i)).getAgentID()))
				break;
		}
		swap(curList, i++, j);

		for (j = curList.size() - 1; j > i; i++, j--) {
			swap(curList, i, j);
		}
		System.out.println("next_permutation stop");
		System.err.println("next_permutation stop");
		return curList;
	}

	public void swap(ArrayList curList, int x, int y) {
		BaseAgent temp;
		temp = (BaseAgent) curList.get(x);
		curList.set(x, curList.get(y));
		curList.set(y, temp);

	}

	public boolean placementTest(ArrayList curList) {
		boolean isValidConfiguration = false;

		for (int i = 0; i < cellSize; i++) // 배치 가능성 check하는 부분
		{
			ForestCell cell = (ForestCell) cellList.get(i); //
			BaseAgent agent = (BaseAgent) curList.get(i);

			if (MonitorForestStatusPolicy.checkValidation(cell, agent, "OPT")) {
				isValidConfiguration = true;

			} else {
				isValidConfiguration = false;
				break;
			}

		}
		return isValidConfiguration;
	}
	
	
	public double[][] makeCostMatrix() {
		double cost = 0;
		double[][] costMatrix = new double[9][11];
		ArrayList agentList = iniList;
		ArrayList cellList = this.cellList;

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 11; j++) {
				
				System.out.println("fuel :"+((BaseAgent) agentList.get(j)).getFuelCost());
				System.out.println("Deprecation :"+((BaseAgent) agentList.get(j)).getDeprecationCost());
				System.out.println("DeviceDeprecation :"+((BaseAgent) agentList.get(j)).getSumOfDeviceDeprecation());
				cost = (((BaseAgent) agentList.get(j)).getFuelCost() * 0.3)
						+ (((BaseAgent) agentList.get(j)).getDeprecationCost() * 0.3)
						+ (((BaseAgent) agentList.get(j)).getSumOfDeviceDeprecation() * 0.1);

				if (((ForestCell) cellList.get(i)).getDensity().equals("High") // policy
						&& ((BaseAgent) agentList.get(j)).getAgentType()
								.equals("Jeep")) { 
					cost = cost + (((BaseAgent) agentList.get(j)).getOperatorCost() * 0.02);

				}

				// there must be operator in airplane
				if (((BaseAgent) agentList.get(j)).getAgentType().equals("AirPlane")) {
					cost = cost + (((BaseAgent) agentList.get(j)).getOperatorCost() * 0.02);
				}

				costMatrix[i][j] = cost;
				cost = 0;
			}
		}

		return costMatrix;
	}

	public double[][] makeBenefitMatrix() {
		System.out.println("makeBenefitMatrix start");
		System.err.println("makeBenefitMatrix start");
		double correctness = 0;
		double completeness = 0;
		double[][] benefitMatrix = new double[9][11];
		ArrayList agentList = iniList;
		ArrayList cellList = this.cellList;

		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 11; j++) {
				
				correctness = (((BaseAgent) agentList.get(j)).getAvgOfSensor() * 0.3);
				System.out.println("AvgOfSensor : " + ((BaseAgent) agentList.get(j)).getAvgOfSensor());
				System.out.println("MountainRainMoving : " + ((BaseAgent) agentList.get(j))
						.getMountainRainMovingDistance());
				System.out.println("MountainMoving : " + ((BaseAgent) agentList.get(j))
						.getMountainMovingDistance());
				System.out.println("RainMoving : " + ((BaseAgent) agentList.get(j))
						.getRainMovingDistance());
				System.out.println("NomalMoving : " + ((BaseAgent) agentList.get(j))
						.getNormalMovingDistance());

				if (((ForestCell) cellList.get(i)).getFeatureList()
						.contains("Mountain")) {

					if (((ForestCell) cellList.get(i)).getWeather().getCondition()
							.equals("Rainy")) {
						completeness = (((BaseAgent) agentList.get(j))
								.getMountainRainMovingDistance() / monitoringDistance) * 0.7;
					}

					else {
						completeness = (((BaseAgent) agentList.get(j))
								.getMountainMovingDistance() / monitoringDistance) * 0.7;
					}
				}

				else {

					if (((ForestCell) cellList.get(i)).getWeather().getCondition()
							.equals("Rainy")) {
						completeness = (((BaseAgent) agentList.get(j))
								.getRainMovingDistance() / monitoringDistance) * 0.7;
					}

					else {
						completeness = (((BaseAgent) agentList.get(j))
								.getNormalMovingDistance() / monitoringDistance) * 0.7;
					}
				}
				benefitMatrix[i][j] = correctness + completeness;
			}
		}
		System.out.println("makeBenefitMatrix finish");
		System.err.println("makeBenefitMatrix finish");
		
		return benefitMatrix;
	}

	public double[][] makeScoreMatrix(double[][] costMatrix,
			double[][] benefitMatrix) {
		double[][] scoreMatrix = new double[9][11];

		for (int i = 0; i < scoreMatrix.length; i++) {
			for (int j = 0; j < scoreMatrix[0].length; j++) {
				scoreMatrix[i][j] = ((benefitMatrix[i][j] - costMatrix[i][j] + 1) * 0.5);
			}
		}
		return scoreMatrix;
	}
	
	
	public double getScoreValueFromMatrix(ArrayList list) {

		double score = 0;
		for (int i = 0; i < cellSize; i++) {
			score += this.scoreMatrix[i][Integer.parseInt((String)((BaseAgent) list.get(i))
					.getAgentID()) - 1];
		}
		return score;

	}
	
	//////////////////////////////////////////////////////////////////////////////////////////
	
	

	
	///////////////////////////////////////////////////////////////////////////////////////////////////
	
	private double getCost(ForestCell cell, BaseAgent agent)
	{
		if(agent == null)
		{
			return 1;
		}
		double cost = 0.0;
		
		cost = cost + agent.getFuelCost() + agent.getDeprecationCost() + agent.getSumOfDeviceDeprecation();
		
		if(cell.getDensity().equals("High") && agent.getAgentType().equals("Jeep"))
		{
			cost = cost + agent.getOperatorCost();
		}
		
		//there must be operator in airplane
		if(agent.getAgentType().equals("AirPlane"))
		{
			cost = cost + agent.getOperatorCost();
		}
		
		
		double nomalizedCost = (cost - agentInstances.getMinCost()) / (agentInstances.getMaxCost() - agentInstances.getMinCost());
		
		//Logger.write("c:"+cost+" / nc:"+ nomalizedCost);
		
		return nomalizedCost;
	}
	
	private double getBenefit(ForestCell cell, BaseAgent agent)
	{
		if(agent == null)
		{
			return 0;
		}
		double correctness = agent.getAvgOfSensor();
		double completeness = 0.0;
		
		//占쏙옙占싹띰옙
		if(cell.getFeatureList().contains("Mountain"))
		{
			//占쏙옙철占�
			if(cell.getWeather().getCondition().equals("Rainy"))
			{
				completeness = agent.getMountainRainMovingDistance()/monitoringDistance;
			}

			else
			{
				completeness = agent.getMountainMovingDistance()/monitoringDistance;
			}
		}

		else
		{
			//占쏙옙철占�
			if(cell.getWeather().getCondition().equals("Rainy"))
			{
				completeness = agent.getRainMovingDistance()/monitoringDistance;
			}

			else
			{
				completeness = agent.getNormalMovingDistance()/monitoringDistance;
			}
		}
		
		double normalizedBenefit = 0.3 * correctness + 0.7 * completeness;
		//Logger.write(correctness+":"+completeness+":"+normalizedBenefit);
		
		return normalizedBenefit;
	}
	private double getConfValue(double cost, double benefit) {
		cost = cost / cellSize;//CellInstances.getCellList().size();
		benefit = benefit / cellSize;//CellInstances.getCellList().size();
		double colVal = benefit - cost;
		
		//Logger.write(cost +":"+benefit+":"+colVal);
		
		return colVal;
	}

}
