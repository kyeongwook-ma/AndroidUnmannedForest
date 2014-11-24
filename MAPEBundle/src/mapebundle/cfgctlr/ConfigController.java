package mapebundle.cfgctlr;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import mapebundle.services.IConfigController;

import org.osgi.framework.Bundle;

import parameterobjects.objectclass.Streatgy;

/**
 * Explanation : 
 * @version : 2014. 5. 14.
 * @author : se
 * ConfigController / package configcontroller;
 */

public class ConfigController implements IConfigController {

	private static ConfigController instance;
	
	static {
		instance = new ConfigController();
	}
	
	public static ConfigController getInstance() {
		if(instance == null) {
			return new ConfigController();
		} else {
			return instance;
		}
	}

	public void reconfigure(ArrayList strategies,List currModel) 
	{
		System.out.println("ConfigController start:");
		execute(strategies, currModel);
		
	}
	private void execute(ArrayList strategies,List currModel)
	{
//		System.out.println("ConfigController : in execute");
//		for(int i=0;i<strategies.size();i++)
//		{
//			System.out.println("ConfigController : in for");
//			Streatgy streatgy = (Streatgy) strategies.get(i);
//			System.out.println("ConfigController : "+streatgy.getStreatgyType() + streatgy.getImplObj().toString()+streatgy.getMethod().toString());
//			if(streatgy.getStreatgyType() ==1)
//			{
//				Object[] parameters = {currModel};
//				try {
//					System.out.println("ConfigController : in try curUBM:" + currModel.toString());
//					streatgy.getMethod().invoke(streatgy.getImplObj(), parameters);
//				} catch (IllegalAccessException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IllegalArgumentException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (InvocationTargetException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
	}
	
	private Bundle searchComponent() {
		return null;
	}
	
	
	
}
