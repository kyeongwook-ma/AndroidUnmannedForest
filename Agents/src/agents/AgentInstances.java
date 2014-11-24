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
				"1	Jeep	40	40	14	0.7	0.6	0.64	70	30	19	100	87	74	46	30	3",
				"2	Jeep	40	40	15	0.59	0.35	0.73	59	18	22	100	78	54	54	25	3",
				"3	Jeep	40	40	12	0.98	0.64	0.78	98	32	23	100	96	57	47	27	2",
				"4	Jeep	40	40	13	1	0.76	0.7	100	38	21	100	86	58	46	37	3",
				"5	Jeep	40	40	16	0.8	0.45	0.75	80	23	23	100	78	68	38	25	3",
				"6	Jeep	40	40	16	0.93	0.85	0.93	93	43	28	100	84	72	51	31	3",
				"7	UAV	0	100	70	0.9	0.86	1	90	43	30	100	100	100	100	102	14",
				"8	UAV	0	100	75	0.95	1	1	95	50	30	100	100	100	100	86	15",
				"9	Helicopter	80	70	67	0.67	0.75	0.68	67	38	20	100	87	100	74	120	13",
				"10	Helicopter	80	70	56	0.87	0.6	0.85	87	30	26	100	87	100	78	116	11",
				"11	AirPlane	100	80	80	0.62	0.63	0.74	62	32	22	100	87	100	86	170	16"
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


