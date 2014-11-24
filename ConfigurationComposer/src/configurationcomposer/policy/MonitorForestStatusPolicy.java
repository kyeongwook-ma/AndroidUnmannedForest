package configurationcomposer.policy;

import agents.variable.BaseAgent;
import environments.variable.ForestCell;

public class MonitorForestStatusPolicy {
	public static boolean checkValidation(ForestCell c, BaseAgent a, String confType)
	{
		if(a == null)
			return true;
		
		//���ðŸ��� 5km ���ϸ� �︮���ʹ� ��ġ�� �� ����.
		if(c.getWeather().getVisibility() < 5.0 && a.getAgentType().equals("Helicopter"))
		{
			return false;
		}
		
		// �ٶ��� 20m/s �̻��̸� �︮���Ϳ� UAV, ������ ��ġ�� �� ����.
		if(c.getWeather().getWind().getVelocity() > 20)
		{
			if(a.getAgentType().equals("Helicopter") || a.getAgentType().equals("UAV") || a.getAgentType().equals("AirPlane"))
			{
				return false;
			}
		}
		
		//������ ���� JEEP ��ġ �Ұ�
		if(c.getWeather().getCondition().equals("Snowy") && a.getAgentType().equals("Jeep"))
		{
			return false;
		}
		
		//ȣ���� ���� JEEP��ġ �Ұ�
		if(c.getFeatureList().contains("Lake") || c.getFeatureList().contains("River"))
		{
			if(a.getAgentType().equals("Jeep"))
				return false;
		}
		
		//���ᰡ ������ agent�� �����Ѵ�.
		if(confType.equals("OPT"))
		{
			if(a.getCurrentOptFuel() < a.getFuelEfficiency())
			{
				return false;
			}
		}
		
		else if(confType.equals("HUR"))
		{
			if(a.getCurrentHurFuel() < a.getFuelEfficiency())
			{
				return false;
			}
		}
		
		
		return true;
	}

}
