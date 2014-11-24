package agents.variable;

public class Jeep extends BaseAgent{
	
	public Jeep(String agentID, String agentType, double operatorCost,
			double deprecationCost, double fuelCost, double visionSensitivity,
			double infraredSensitivity, double GPSSensitibity,
			double visionDeprecation, double infraredDeprecation,
			double GPSDeprecation, double normalMovingDistance,
			double rainMovingDistance, double mountainMovingDistance,
			double mountainRainMovingDistance,
			int initialFuel, int fuelEfficiency) {
		super(agentID, agentType, operatorCost, deprecationCost, fuelCost,
				visionSensitivity, infraredSensitivity, GPSSensitibity,
				visionDeprecation, infraredDeprecation, GPSDeprecation,
				normalMovingDistance, rainMovingDistance, mountainMovingDistance,
				mountainRainMovingDistance, initialFuel, fuelEfficiency);
		
	}

}
