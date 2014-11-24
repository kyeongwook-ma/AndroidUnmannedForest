package environments.variable;

public class Weather {
	
	private String condition;
	private Wind wind;
	private  double visibility;
	
	public Weather(String condition, double windVelocity, String windDirection, double visibility) {
		this.condition = condition;
		this.wind = new Wind(windVelocity, windDirection);
		this.visibility = visibility;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public Wind getWind() {
		return wind;
	}

	public void setWind(Wind wind) {
		this.wind = wind;
	}

	public double getVisibility() {
		return visibility;
	}

	public void setVisibility(double visibility) {
		this.visibility = visibility;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Velocity : " + wind.getVelocity() + " Direction : " + wind.getDirection().toString());
		
		return sb.toString();
	}
}


