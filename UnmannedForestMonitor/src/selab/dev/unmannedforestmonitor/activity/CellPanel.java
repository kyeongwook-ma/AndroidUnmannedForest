package selab.dev.unmannedforestmonitor.activity;

import android.widget.ImageView;
import android.widget.TextView;

public class CellPanel {
	ImageView agent;
	ImageView weather;
	ImageView environments[];
	ImageView density;
	ImageView land;
	TextView agentName;
	
	public CellPanel(ImageView agent,TextView agentName, ImageView weather,ImageView[] environments,ImageView density,ImageView land) {
		// TODO Auto-generated constructor stub
		this.agent = agent;
		this.agentName = agentName;
		this.weather = weather;
		this.environments = environments;
		this.density = density;
		this.land = land;
	}
	
	public void setAgent(ImageView agent) {
		this.agent = agent;
	}
	public void setAgentName(TextView agentName) {
		this.agentName = agentName;
	}
	public void setDensity(ImageView density) {
		this.density = density;
	}
	public void setEnvirements(ImageView[] envirements) {
		this.environments = envirements;
	}
	public void setLand(ImageView land) {
		this.land = land;
	}
	public void setWeather(ImageView weather) {
		this.weather = weather;
	}
	public ImageView getAgent() {
		return agent;
	}
	public TextView getAgentName() {
		return agentName;
	}
	public ImageView getDensity() {
		return density;
	}
	public ImageView[] getEnvirements() {
		return environments;
	}
	public ImageView getLand() {
		return land;
	}
	public ImageView getWeather() {
		return weather;
	}
	

}
