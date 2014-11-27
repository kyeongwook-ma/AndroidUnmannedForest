package selab.dev.unmannedforestmonitor.uiservice;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import kr.ac.sogang.unmannedforestmonitor.R;
import selab.dev.unmannedforestmonitor.activity.CellPanels;
import selab.dev.unmannedforestmonitor.activity.MainActivity;
import selab.dev.unmannedforestmonitor.uiservice.EnvChecker.ICheckEnvironment;



public class DrawUI implements DrawService {

	Activity androidContext;
	
	public DrawUI(Activity androidContext) {
		// TODO Auto-generated constructor stub
		this.androidContext = androidContext;
	}

	@Override
	public void drawEnvironments(final Integer panelNum, final Object forestCell) {
		// TODO Auto-generated method stub
		androidContext.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(ICheckEnvironment check : EnvChecker.getCheckEnvList()) {
					try {
						check.checkCellEnv(panelNum, forestCell);
					} catch (NoSuchMethodException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		
	}

	@Override
	public void drawAvailable(final String agents, final String scores) {
		// TODO Auto-generated method stub
		androidContext.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				AgentsTextView agentsTextView = new AgentsTextView("("+agents+")");
				ScoreTextView scoreTextView = new ScoreTextView("    "+scores);
				CellPanels.wholeLayout.addView(agentsTextView);
				CellPanels.wholeLayout.addView(scoreTextView);
			}
		});
		
	}

	@Override
	public void drawSelected(final String agents, final String scores) {
		// TODO Auto-generated method stub
		
		androidContext.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				CellPanels.selectedSet[0].setText(agents);
				CellPanels.selectedSet[1].setText(scores);
			}
		});
		
	}

	@Override
	public void drawAgents(final Integer cellNum,final String agentID,final String agentType) {
		// TODO Auto-generated method stub
		androidContext.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(agentType.equals("Jeep"))
				{
					CellPanels.cellPanels[cellNum].getAgent().setImageResource(R.drawable.jeep);
				}
				
				else if(agentType.equals("UAV"))
				{
					CellPanels.cellPanels[cellNum].getAgent().setImageResource(R.drawable.uav);
				}
				else if (agentType.equals("Helicopter")) {
					CellPanels.cellPanels[cellNum].getAgent().setImageResource(R.drawable.helicopter);

				}
				CellPanels.cellPanels[cellNum].getAgentName().setText(agentID);
			}
		});
		
	}
	

}
