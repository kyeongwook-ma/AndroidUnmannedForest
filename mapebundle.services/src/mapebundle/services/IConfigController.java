package mapebundle.services;

import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.Bundle;


/**
 * Explanation : 
 * @version : 2014. 5. 14.
 * @author : se
 * ConfigController / package configcontroller;
 */

public interface IConfigController {
	public void reconfigure(ArrayList strategies,List curUBM);
}
