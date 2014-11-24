package collabarationmonitor.calculater;

import hostactivator.HostActivator;

import java.util.ArrayList;

import agents.service.AgentInstancesService;
import agents.variable.BaseAgent;
import environments.service.EnvironmentsService;
import environments.variable.ForestCell;


public class CalculateCurrentScore {
	private int cellSize = 9;
	private AgentInstancesService agentInstances;
	private ArrayList cellList;
	private double monitoringDistance;
	
	public CalculateCurrentScore() {
		// TODO Auto-generated constructor stub
		agentInstances = (AgentInstancesService) HostActivator.getService(AgentInstancesService.class.getName());
		EnvironmentsService envService = (EnvironmentsService) HostActivator.getService(EnvironmentsService.class.getName());
		cellList = envService.getCellList();
		monitoringDistance = envService.getMonitoringDistance();
	}
	
	
	public double getScore() {
		double cost = 0.0;
		double benefit = 0.0;
		
		if(agentInstances.getAgentList().size() > 0)
		{
			for(int i=0;i<cellSize;i++)
			{
				cost = cost + getCost((ForestCell)cellList.get(i), (BaseAgent)agentInstances.getAgentList().get(i));
				benefit = benefit + getBenefit((ForestCell)cellList.get(i), (BaseAgent)agentInstances.getAgentList().get(i));
			}
			
			return getConfValue(cost,benefit);
		}
		return 0;
	}
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
