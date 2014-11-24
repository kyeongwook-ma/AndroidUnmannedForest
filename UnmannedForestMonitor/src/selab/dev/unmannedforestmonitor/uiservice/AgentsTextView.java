package selab.dev.unmannedforestmonitor.uiservice;

import selab.dev.unmannedforestmonitor.activity.CellPanels;
import android.widget.TextView;

public class AgentsTextView extends TextView {

	public AgentsTextView(String agentList)
	{
		super(CellPanels.mainActivity);
		this.setTextSize(16);
		this.setHeight(30);
		this.setWidth(CellPanels.wholeLayout.getWidth());
	}

}
