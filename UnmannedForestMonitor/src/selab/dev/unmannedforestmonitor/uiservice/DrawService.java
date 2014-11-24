package selab.dev.unmannedforestmonitor.uiservice;

import java.util.List;

public interface DrawService {

	public void drawEnvironments(Integer panelNum, Object forestCell);
	public void drawAvailable(String agents,String scores);
	public void drawSelected(String agents,String scores);
	public void drawAgents(Integer cellNum,String agentID,String agentType);
}
