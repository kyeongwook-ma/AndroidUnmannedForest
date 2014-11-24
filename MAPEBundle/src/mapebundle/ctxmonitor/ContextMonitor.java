package mapebundle.ctxmonitor;

import hostactivator.HostActivator;
import hostactivator.ISettingFelix;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

import mapebundle.adaptreasoner.AdaptationReasoner;
import mapebundle.services.IContextMonitor;
import userbehaviormodelgenerator.service.UBMGeneratorService;

/**
 * Explanation : 
 * @version : 2014. 5. 12.
 * @author : se
 * ConfigController / package configcontroller;
 */

public class ContextMonitor implements IContextMonitor {

	private boolean isRunning = false;

	public ContextMonitor() {

		activate();

		System.out.println("before running");

		//new Thread() {
		//	public void run() {

		List currModel;

		String designedModel = null;

		UBMGeneratorService ubmGenerator = 
				(UBMGeneratorService) HostActivator
				.getService(UBMGeneratorService.class.getName());

		// while(isRunning) {
		currModel = ubmGenerator.genCurBM("/data/");

		if(currModel != null) {
			/* metaData -> designModel : SharedPrefs */
			AdaptationReasoner.getInstance().reason(designedModel, currModel);
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("ContextMonitor: start");
		// }

		//	};
		//}.start();

	}

	private String getDesignedModel() {

		/* 파일로 designed model 저장   */
		String loadPath = ISettingFelix.prjFilePath + "/designed.txt";
		String content = null;	
		try {
			FileInputStream fis = new FileInputStream(loadPath);
			BufferedReader bufferReader = new BufferedReader(new InputStreamReader(fis));

			String temp="";

			while( (temp = bufferReader.readLine()) != null ) {
				content += temp;
			}

			bufferReader.close();
			fis.close();
		} catch (Exception e) {e.printStackTrace();}

		return content;
	}

	public void activate() {
		isRunning = true;
	}

	public void deActivate() {
		isRunning = false;
	}

}
