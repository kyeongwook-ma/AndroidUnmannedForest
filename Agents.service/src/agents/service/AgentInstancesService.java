package agents.service;

import java.util.ArrayList;


public interface AgentInstancesService {

	public ArrayList getAgentList();
	public void setAgentList(ArrayList agentList);
	public void printAgents();
	public ArrayList getAgentIDList();
	public Object getAgent(String id);
	public double getMaxFuelCost();
	public double getMinFuelCost();
	public double getMaxDeprecationCost();
	public double getMinDeprecationCost();
	public double getMaxDeviceDeprecationCost();
	public double getMinDeviceDeprecationCost();
	public double getMaxOperatorCost();
	public double getMinOperatorCost();
	public double getMaxCost();
	public double getMinCost();
}
