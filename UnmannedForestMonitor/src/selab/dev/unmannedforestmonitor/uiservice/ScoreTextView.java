package selab.dev.unmannedforestmonitor.uiservice;

import selab.dev.unmannedforestmonitor.activity.CellPanels;
import android.widget.TextView;

public class ScoreTextView extends TextView {
	
	public ScoreTextView(String agentList)
	{
		super(CellPanels.mainActivity);
		this.setTextSize(14);
		this.setHeight(30);
		this.setWidth(CellPanels.wholeLayout.getWidth());
		
	}

}
