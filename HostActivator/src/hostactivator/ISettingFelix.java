package hostactivator;

import org.osgi.framework.Bundle;


public interface ISettingFelix {
	String effectorService = "selab.dev.uiselfadaptive.services.Effector";
	String prjFilePath = "/data/data/selab.dev.uiselfadaptive/files";
	
	public void setFelix(Bundle felix);
}
