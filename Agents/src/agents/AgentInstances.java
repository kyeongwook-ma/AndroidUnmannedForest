package agents;

import java.util.ArrayList;

import agents.service.AgentInstancesService;
import agents.variable.BaseAgent;
import agents.variable.Jeep;


public class AgentInstances implements AgentInstancesService{
	private  ArrayList  agentList;
	private  double maxCost = 0.0;
	private  double minCost = 0.0;


	public AgentInstances(ArrayList agentList)
	{
		this.agentList = agentList;
		makeAgent();
	}

	public  ArrayList  getAgentList() {
		return agentList;
	}

	public  void setAgentList(ArrayList  agentList) {
		this.agentList = agentList;
	}

	public  void printAgents() {
		//		for(BaseAgent agent : agentList)
		//		{
		//			LoggerUtil.write(LoggerUtil.LOGPANE, agent.getAgentType().name());
		//		}
	}

	public  ArrayList getAgentIDList() {
		ArrayList idList = new ArrayList();
		//		for(BaseAgent a : agentList)
		//		{
		//			idList.add(a.getAgentID());
		//		}
		for(int i=0;i<agentList.size();i++)
		{
			BaseAgent a = (BaseAgent) agentList.get(i);
			idList.add(a.getAgentID());
		}

		//idList.add(null);

		return idList;
	}

	public  Object getAgent(String id)
	{

		//		for(BaseAgent a : agentList)
		//		{
		//			if(a.getAgentID().equals(id))
		//			{
		//				return a;
		//			}
		//		}
		//		
		for(int i=0;i<agentList.size();i++)
		{
			BaseAgent a = (BaseAgent) agentList.get(i);

			if(a.getAgentID().equals(id))
			{
				return a;
			}
		}
		return null;
	}

	public  double getMaxFuelCost()
	{
		double retVal = 0.0;
		//		for(BaseAgent a : agentList)
		for(int i=0;i<agentList.size();i++)
		{
			BaseAgent a = (BaseAgent) agentList.get(i);
			if(retVal < a.getFuelCost())
				retVal = a.getFuelCost();
		}

		return retVal;
	}

	public  double getMinFuelCost()
	{
		double retVal = 1000.0;
		//		for(BaseAgent a : agentList)
		for(int i=0;i<agentList.size();i++)
		{
			BaseAgent a = (BaseAgent) agentList.get(i);
			if(retVal > a.getFuelCost())
				retVal = a.getFuelCost();
		}

		return retVal;
	}

	public  double getMaxDeprecationCost()
	{
		double retVal = 0.0;
		for(int i=0;i<agentList.size();i++)
		{
			BaseAgent a = (BaseAgent) agentList.get(i);
			if(retVal < a.getDeprecationCost())
				retVal = a.getDeprecationCost();
		}

		return retVal;
	}

	public  double getMinDeprecationCost()
	{
		double retVal = 1000.0;
		//		for(BaseAgent a : agentList)
		for(int i=0;i<agentList.size();i++)
		{
			BaseAgent a = (BaseAgent) agentList.get(i);

			if(retVal > a.getDeprecationCost())
				retVal = a.getDeprecationCost();
		}

		return retVal;
	}

	public  double getMaxDeviceDeprecationCost()
	{
		double retVal = 0.0;
		for(int i=0;i<agentList.size();i++)
		{
			BaseAgent a = (BaseAgent) agentList.get(i);
			if(retVal < a.getSumOfDeviceDeprecation())
				retVal = a.getSumOfDeviceDeprecation();
		}

		return retVal;
	}

	public  double getMinDeviceDeprecationCost()
	{
		double retVal = 1000.0;
		for(int i=0;i<agentList.size();i++)
		{
			BaseAgent a = (BaseAgent) agentList.get(i);
			if(retVal > a.getDeprecationCost())
				retVal = a.getDeprecationCost();
		}

		return retVal;
	}

	public  double getMaxOperatorCost()
	{
		double retVal = 0.0;
		for(int i=0;i<agentList.size();i++)
		{
			BaseAgent a = (BaseAgent) agentList.get(i);
			if(retVal < a.getOperatorCost())
				retVal = a.getOperatorCost();
		}

		return retVal;
	}

	public  double getMinOperatorCost()
	{
		double retVal = 1000.0;
		for(int i=0;i<agentList.size();i++)
		{
			BaseAgent a = (BaseAgent) agentList.get(i);
			if(retVal > a.getOperatorCost())
				retVal = a.getOperatorCost();
		}

		return retVal;
	}

	public  double getMaxCost()
	{
		if(maxCost == 0.0)
			return getMaxFuelCost()+getMaxOperatorCost()+getMaxDeviceDeprecationCost()+getMaxDeprecationCost();
		else
			return maxCost;
	}

	public  double getMinCost()
	{
		if(minCost == 0.0)
			return getMinFuelCost()+getMinOperatorCost()+getMinDeviceDeprecationCost()+getMinDeprecationCost();
		else
			return minCost;
	}


	private void makeAgent()
	{
		String agentSpec[] = new String[] {
				"1	Jeep	0.4	0.4	0.028	0.7	0.6	0.64	0.268	0.375	0	100	87	74	46	30	3",
				"2	Jeep	0.4	0.4	0.042	0.59	0.35	0.73	0	0	0.273	100	78	54	54	25	3",
				"3	Jeep	0.4	0.4	0	0.98	0.64	0.78	0.951	0.438	0.364	100	96	57	47	27	2",
				"4	Jeep	0.4	0.4	0.014	1	0.76	0.7	1	0.625	0.182	100	86	58	46	37	3",
				"5	Jeep	0.4	0.4	0.056	0.8	0.45	0.75	0.512	0.156	0.364	100	78	68	38	25	3",
				"6	Jeep	0.4	0.4	0.056	0.93	0.85	0.93	0.829	0.781	0.818	100	84	72	51	31	3",
				"7	UAV	0	1	0.817	0.9	0.86	1	0.756	0.781	1	100	100	100	100	102	14",
				"8	UAV	0	1	0.887	0.95	1	1	0.878	1	1	100	100	100	100	86	15",
				"9	Helicopter	0.8	0.7	0.775	0.67	0.75	0.68	0.195	0.625	0.091	100	87	100	74	120	13",
				"10	Helicopter	0.8	0.7	0.62	0.87	0.6	0.85	0.683	0.375	0.636	100	87	100	78	116	11",
				"11	AirPlane	1	0.8	0.958	0.62	0.63	0.74	0.073	0.438	0.273	100	87	100	86	170	16"
		};


		for(int i=0;i<agentSpec.length;i++) {
			String s = agentSpec[i];
			if(s.startsWith("//"))
				continue;
			String[] spec;
			spec = s.split("\t");

			if(spec[1].equals("Jeep"))
			{
				Jeep ag = new Jeep(spec[0], spec[1], Double.parseDouble(spec[2]),
						Double.parseDouble(spec[3]),Double.parseDouble(spec[4])
						,Double.parseDouble(spec[5]),Double.parseDouble(spec[6]),Double.parseDouble(spec[7]),
						Double.parseDouble(spec[8]),Double.parseDouble(spec[9]),Double.parseDouble(spec[10])
						,Double.parseDouble(spec[11]),
						Double.parseDouble(spec[12]),Double.parseDouble(spec[13]),
						Double.parseDouble(spec[14]),
						Integer.parseInt(spec[15]), Integer.parseInt(spec[16]));
				agentList.add(ag);
			}

			else
			{
				BaseAgent ag = new BaseAgent(spec[0], spec[1], Double.parseDouble(spec[2]),
						Double.parseDouble(spec[3]),Double.parseDouble(spec[4])
						,Double.parseDouble(spec[5]),Double.parseDouble(spec[6]),Double.parseDouble(spec[7]),
						Double.parseDouble(spec[8]),Double.parseDouble(spec[9]),Double.parseDouble(spec[10])
						,Double.parseDouble(spec[11]),
						Double.parseDouble(spec[12]),Double.parseDouble(spec[13]),
						Double.parseDouble(spec[14]),
						Integer.parseInt(spec[15]), Integer.parseInt(spec[16]));
				agentList.add(ag);
			}
		}


		//		URL configURL = context.getBundle().getResource("/agentSpec.txt");
		//
		//		if (configURL != null) {
		//			InputStream input = null;
		//			try {
		//				input = configURL.openStream();
		//
		//				BufferedReader br = new BufferedReader(new InputStreamReader(input));
		//
		//				String s;
		//				while ((s = br.readLine()) != null) {
		//
		//					if(s.startsWith("//"))
		//						continue;
		//					String[] spec;
		//					spec = s.split("\t");
		//
		//					if(spec[1].equals("Jeep"))
		//					{
		//						Jeep ag = new Jeep(spec[0], spec[1], Double.parseDouble(spec[2]),
		//								Double.parseDouble(spec[3]),Double.parseDouble(spec[4])
		//								,Double.parseDouble(spec[5]),Double.parseDouble(spec[6]),Double.parseDouble(spec[7]),
		//								Double.parseDouble(spec[8]),Double.parseDouble(spec[9]),Double.parseDouble(spec[10])
		//								,Double.parseDouble(spec[11]),
		//								Double.parseDouble(spec[12]),Double.parseDouble(spec[13]),
		//								Double.parseDouble(spec[14]),
		//								Integer.parseInt(spec[15]), Integer.parseInt(spec[16]));
		//						agentList.add(ag);
		//					}
		//
		//					else
		//					{
		//						BaseAgent ag = new BaseAgent(spec[0], spec[1], Double.parseDouble(spec[2]),
		//								Double.parseDouble(spec[3]),Double.parseDouble(spec[4])
		//								,Double.parseDouble(spec[5]),Double.parseDouble(spec[6]),Double.parseDouble(spec[7]),
		//								Double.parseDouble(spec[8]),Double.parseDouble(spec[9]),Double.parseDouble(spec[10])
		//								,Double.parseDouble(spec[11]),
		//								Double.parseDouble(spec[12]),Double.parseDouble(spec[13]),
		//								Double.parseDouble(spec[14]),
		//								Integer.parseInt(spec[15]), Integer.parseInt(spec[16]));
		//						agentList.add(ag);
		//					}
		//				}
		//			} catch (Exception e) {
		//				// TODO Auto-generated catch block
		//				e.printStackTrace();
		//			}
	}


}


