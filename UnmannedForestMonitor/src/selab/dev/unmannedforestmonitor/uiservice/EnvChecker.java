package selab.dev.unmannedforestmonitor.uiservice;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import kr.ac.sogang.unmannedforestmonitor.R;
import selab.dev.unmannedforestmonitor.activity.CellPanel;
import selab.dev.unmannedforestmonitor.activity.CellPanels;

public class EnvChecker {

	public interface ICheckEnvironment {
		public void checkCellEnv(int panelNum, Object c) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;
	}

	public static ArrayList<ICheckEnvironment> getCheckEnvList() {

		ArrayList<ICheckEnvironment> checkEnvList = new ArrayList<ICheckEnvironment>();
		checkEnvList.add((ICheckEnvironment) new LowVisiblityCondition());
		checkEnvList.add((ICheckEnvironment) new HasWaterCondition());
		checkEnvList.add((ICheckEnvironment) new HighDenstCondtion());
		checkEnvList.add((ICheckEnvironment) new WeatherCheckCondition());
		checkEnvList.add((ICheckEnvironment) new StrongWindCondition());
		checkEnvList.add((ICheckEnvironment) new FeaturePresenter());
		return checkEnvList;
	}

	public static class FeaturePresenter implements ICheckEnvironment{

		@Override
		public void checkCellEnv(int panelNum,Object c) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

			CellPanel panel = CellPanels.cellPanels[panelNum];
			Method getFeatureList = c.getClass().getDeclaredMethod("getFeatureList", null);
			
			ArrayList<String> featureList = (ArrayList<String>) getFeatureList.invoke(c, null);

			if(featureList != null) {

				for(String feature : featureList) {
					
					if(feature.equals("Field"))
					{
						panel.getLand().setImageResource(R.drawable.field);
					}
					else if(feature.equals("Lake"))
					{
						panel.getLand().setImageResource(R.drawable.lake);
					}
					else if(feature.equals("Mountain"))
					{
						panel.getLand().setImageResource(R.drawable.mountain);
					}
					else if(feature.equals("River"))
					{
						panel.getLand().setImageResource(R.drawable.river);
					}
					
				} 
			}
			Method getDensity = c.getClass().getDeclaredMethod("getDensity",null);
			
				
			String density = (String)getDensity.invoke(c, null); 

			if(density.equals("Low"))
			{
				panel.getDensity().setImageResource(R.drawable.lowdensity);
			}
			else if(density.equals("Medium"))
			{
				panel.getDensity().setImageResource(R.drawable.midiumdensity);
			}
		}

	}



	public static class HasWaterCondition implements ICheckEnvironment{
//
//		@Override
//		public void checkCellEnv(CellPanel panel,ForestCell c) {
//
//			ArrayList<GeoFeature> featureList = c.getFeatureList();
//
//			if( featureList.contains(GeoFeature.Lake) || featureList.contains(GeoFeature.River) ) {
//
//			}			
//		}

		@Override
		public void checkCellEnv(int panelNum, Object c)
				throws NoSuchMethodException, IllegalAccessException,
				IllegalArgumentException, InvocationTargetException {
			// TODO Auto-generated method stub
		}
	}

	public static class HighDenstCondtion implements ICheckEnvironment{
//
//		@Override
//		public void checkCellEnv(CellPanel panel,ForestCell c) {
//			String imgPath = null;
//			
//			if(c.getDensity().equals(ForestCell.ForestDensity.High)) {
//				imgPath = "highDensity.png";
//			}
//			ImageUtil.drawIcon(panel.getDensity(), imgPath);
//		}

		@Override
		public void checkCellEnv(int panelNum, Object c)
				throws NoSuchMethodException, IllegalAccessException,
				IllegalArgumentException, InvocationTargetException {
			// TODO Auto-generated method stub
			CellPanel panel = CellPanels.cellPanels[panelNum];
			Method getDensity = c.getClass().getDeclaredMethod("getDensity",null);
			
			
			String density = (String)getDensity.invoke(c, null); 
			
			if(density.equals("High"))
			{
				panel.getDensity().setImageResource(R.drawable.highdensity);
			}
		}
			
		

	}
	public static class LowVisiblityCondition implements ICheckEnvironment{

//		@Override
//		public void checkCellEnv(CellPanel panel,ForestCell c) {
//			String imgPath = null;
//
//			if( c.getWeather().getVisibility() < 5) {	
//				imgPath =  "lowVisivility.png";
//			} else {
//				ImageUtil.removeIcon(panel.getFog());
//			}
//			
//			ImageUtil.drawIcon(panel.getFog(), imgPath);
//		}
		

		@Override
		public void checkCellEnv(int panelNum, Object c)
				throws NoSuchMethodException, IllegalAccessException,
				IllegalArgumentException, InvocationTargetException {
			// TODO Auto-generated method stub
			if(panelNum< 9)
			{
				CellPanel panel = CellPanels.cellPanels[panelNum];
				Method getWeather = c.getClass().getDeclaredMethod("getWeather", null);
				Object weatherObject = (Object) getWeather.invoke(c, null);
				
				Method getVisibility = weatherObject.getClass().getDeclaredMethod("getVisibility",null);
				double visibility = (Double) getVisibility.invoke(weatherObject, null);
				if(visibility<5)
				{
					//안개 칸은 0 
					panel.getEnvirements()[0].setImageResource(R.drawable.lowvisivility);
				}
//				else
//					panel.getEnvirements()[0].setImageResource(R.drawable.uipanel);
			}
			
		}

	}

	public static class WeatherCheckCondition implements ICheckEnvironment {

//		@Override
//		public void checkCellEnv(CellPanel panel,ForestCell c) {
//
//			String imgPath = null;
//
//			switch(c.getWeather().getCondition()) {
//			case Snowy:
//				imgPath = "snowy.png";
//				break;
//			case Sunny:
//				imgPath = "sunny.png";
//				break;
//			case Cloudy:
//				imgPath = "cloudy.png";
//				break;
//			case Rainy:
//				imgPath = "rainy.png";
//				break;
//		
//			}
//
//			ImageUtil.drawIcon(panel.getWeather(), imgPath);
//
//
//		}

		@Override
		public void checkCellEnv(int panelNum, Object c)
				throws NoSuchMethodException, IllegalAccessException,
				IllegalArgumentException, InvocationTargetException {
			// TODO Auto-generated method stub
			CellPanel panel = CellPanels.cellPanels[panelNum];
			Method getWeather = c.getClass().getDeclaredMethod("getWeather", null);
			Object weatherObject = (Object) getWeather.invoke(c, null);
			
			Method getCondition = weatherObject.getClass().getDeclaredMethod("getCondition", null);
			String weatherCondition = (String) getCondition.invoke(weatherObject, null);
			
			if(weatherCondition.equals("Snowy"))
			{
				panel.getWeather().setImageResource(R.drawable.snowy);
			}
			else if(weatherCondition.equals("Sunny"))
			{
				panel.getWeather().setImageResource(R.drawable.sunny);
			}
			else if(weatherCondition.equals("Cloudy"))
			{
				panel.getWeather().setImageResource(R.drawable.cloudy);
			}
			else if(weatherCondition.equals("Rainy"))
			{
				panel.getWeather().setImageResource(R.drawable.rainy);
			}
			
		}

	}


	public static class StrongWindCondition implements ICheckEnvironment {


//		@Override
//		public void checkCellEnv(CellPanel panel,ForestCell c) {
//			String imgPath = null;
//
//			if(c.getWeather().getWind().getVelocity() > 20) {
//				imgPath = "wind.png";
//			} else {
//				ImageUtil.removeIcon(panel.getWind());
//			}
//
//			ImageUtil.drawIcon(panel.getWind(), imgPath);
//
//		}

		@Override
		public void checkCellEnv(int panelNum, Object c)
				throws NoSuchMethodException, IllegalAccessException,
				IllegalArgumentException, InvocationTargetException {
			// TODO Auto-generated method stub
			CellPanel panel = CellPanels.cellPanels[panelNum];
			Method getWeather = c.getClass().getDeclaredMethod("getWeather", null);
			Object weatherObject = (Object) getWeather.invoke(c, null);
			Method getWind = weatherObject.getClass().getDeclaredMethod("getWind", null);
			Object wind =  getWind.invoke(weatherObject, null);
			Method getVelocity = wind.getClass().getDeclaredMethod("getVelocity", null);
			double velocity = (Double) getVelocity.invoke(wind, null);
			
			if(velocity>20)
			{
				panel.getEnvirements()[1].setImageResource(R.drawable.wind);
			}
			else
				panel.getEnvirements()[1].setImageResource(0);
			
		}

	}
}
