package environments;

import hostactivator.HostActivator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import org.osgi.framework.BundleContext;

import environments.service.EnvironmentsService;
import environments.variable.ForestCell;

public class EnvironmentsServiceImp implements EnvironmentsService{

	ArrayList cellList;
	private double monitoringDistance = 100;
	private BundleContext context;

	public EnvironmentsServiceImp(BundleContext context, ArrayList cellList) {

		this.context = context;
		this.cellList = cellList;

	}

	public ArrayList getCellList() {
		// TODO Auto-generated method stub
		return cellList;
	}
	public void changeRandomEnvironment()
	{
		for(int i =0;i<cellList.size();i++)
		{
			ForestCell fc = (ForestCell) cellList.get(i);
			//change weather condition
			int n = new Random().nextInt(4);
			if(n==0)
			{
				fc.getWeather().setCondition("Cloudy");
				//fc.getWeather().setVisibility(10);
			}
			else if(n==1)
			{
				fc.getWeather().setCondition("Rainy");
				//fc.getWeather().setVisibility(10);
			}
			else if(n==2)
			{
				fc.getWeather().setCondition("Sunny");
				//foggy after rain
				//fc.getWeather().setVisibility(3);
				//fc.getWeather().setVisibility(10);
			}

			int v = new Random().nextInt(10);

			if(v == 1)
			{
				fc.getWeather().setVisibility(3);
			}
			else
				fc.getWeather().setVisibility(10);

			int w = new Random().nextInt(10);

			if(w == 1)
			{
				fc.getWeather().getWind().setVelocity(25);
			}
			else
				fc.getWeather().getWind().setVelocity(5);


			//		MainView.drawEnvironment();
			drawEnvironment(i, fc);
		}

	}

	public void initEnvironment() {

		String cellSpecs[] = new String[] {
				"0	0	Field	Cloudy	10	N	5	Low",
				"0	1	Field	Cloudy	8	N	8	High",
				"0	2	Mountain	Rainy	7	N	15	Low",
				"1	0	Field	Cloudy	11	N	5	Low",
				"1	1	Lake	Cloudy	4	N	18	Medium",
				"1	2	Mountain	Rainy	3	N	21	Medium",
				"2	0	River	Sunny	13	N	13	Low",
				"2	1	River	Sunny	16	N	16	Medium",
				"2	2	Field	Cloudy	11	N	11	High"
		};

		for(int i=0;i<cellSpecs.length;i++) {
			String s=  cellSpecs[i];
			String[] cellSpec;
			cellSpec = s.split("\t");

			// int x, int y, String feature, String condition, double
			// windVelocity, String windDirection, String density
			ForestCell fc = new ForestCell(Integer.parseInt(cellSpec[0]),
					Integer.parseInt(cellSpec[1]), cellSpec[2],
					cellSpec[3], Double.parseDouble(cellSpec[4]),
					cellSpec[5], Double.parseDouble(cellSpec[6]),
					cellSpec[7]
					);
			cellList.add(fc);

			
		}

		for(int i=0; i<9;i++)
		{
			drawEnvironment(i, (ForestCell)cellList.get(i));
		}




		//		URL configURL = context.getBundle().getResource("/cellSpec.txt");
		//
		//		if (configURL != null) {
		//			InputStream input = null;
		//			try {
		//				input = configURL.openStream();
		//
		//				BufferedReader br = new BufferedReader(new InputStreamReader(input));
		//
		//				String s;
		//				while ((s = br.readLine()) != null) {
		//					String[] cellSpec;
		//					cellSpec = s.split("\t");
		//
		//					// int x, int y, String feature, String condition, double
		//					// windVelocity, String windDirection, String density
		//					ForestCell fc = new ForestCell(Integer.parseInt(cellSpec[0]),
		//							Integer.parseInt(cellSpec[1]), cellSpec[2],
		//							cellSpec[3], Double.parseDouble(cellSpec[4]),
		//							cellSpec[5], Double.parseDouble(cellSpec[6]),
		//							cellSpec[7]
		//							);
		//					cellList.add(fc);
		//
		//					for(int i=0; i<9;i++)
		//					{
		//						drawEnvironment(i, (ForestCell)cellList.get(i));
		//					}
		//
		//				}
		//			} catch (Exception e) {
		//				e.printStackTrace();
		//			}
		//
		//
		//			try {
		//				input.close();
		//			} catch (IOException e) {
		//				e.printStackTrace();
		//			}

	}


	private void drawEnvironment (int i,ForestCell fc) {

		Object drawUI = HostActivator.getService("selab.dev.unmannedforestmonitor.uiservice.DrawService");
		Class[] parameterDrawEnv = {Integer.class,Object.class};
		Object[] parameterDrawEnvObj = {new Integer(i),fc};

		try {
			Method drawEnvironments = drawUI.getClass().getDeclaredMethod("drawEnvironments", parameterDrawEnv);

			try {
				drawEnvironments.invoke(drawUI, parameterDrawEnvObj);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
	public double getMonitoringDistance() {
		return monitoringDistance;
	}
}
