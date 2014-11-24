package userbehaviormodelgenerator.service;


import java.io.File;
import java.util.TreeMap;

public interface UserBehaviorService {
	public TreeMap genCurrentBM(File dirPath);
}