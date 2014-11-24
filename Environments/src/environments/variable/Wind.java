package environments.variable;

public class Wind {
	private String direction;
	private double velocity;
	
	public Wind(double windVelocity, String windDirection) {
		this.direction = windDirection;
		this.velocity = windVelocity;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public double getVelocity() {
		return velocity;
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}

}
