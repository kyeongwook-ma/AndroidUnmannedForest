package environments.service;

import java.util.ArrayList;

import org.osgi.framework.Bundle;

public interface EnvironmentsService {

	public ArrayList getCellList();
	public void changeRandomEnvironment();
	public void initEnvironment();
	public double getMonitoringDistance();
}
