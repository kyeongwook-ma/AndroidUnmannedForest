package environments.variable;

import java.util.ArrayList;

public class ForestCell {
	private int x;
	private int y;
	private ArrayList featureList;
	private Weather weather;
	private String density;
	
	public ForestCell(int x, int y, String feature,	String condition, double visibility, String windDirection, double windVelocity, String density) {
		featureList = new ArrayList();
		this.x = x;
		this.y = y;
		featureList.add(feature);
		weather = new Weather(condition, windVelocity, windDirection, visibility);
		this.density = density;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public ArrayList getFeatureList() {
		return featureList;
	}

	public void setFeatureList(ArrayList featureList) {
		this.featureList = featureList;
	}

	public Weather getWeather() {
		return weather;
	}

	public void setWeather(Weather weather) {
		this.weather = weather;
	}

	public String getDensity() {
		return density;
	}

	public void setDensity(String density) {
		this.density = density;
	}
	

	
	
}




