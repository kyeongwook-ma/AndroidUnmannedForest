package selab.dev.unmannedforestmonitor.activity;

import selab.dev.unmannedforestmonitor.osgi.BundleController;
import kr.ac.sogang.unmannedforestmonitor.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	Button changeEvn;
	ImageView weathers[];
	//LinearLayout panels[];
	ImageView environments[][];
	ImageView agents[];
	ImageView lands[];
	ImageView densitys[];
	TextView agentNames[];
	CellPanel cellpanels[];


	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		LinearLayout wholeLayout =(LinearLayout)getLayoutInflater().inflate (R.layout.activity_main, null);
		CellPanels.wholeLayout = wholeLayout;

		initUI();

	}
	private void initUI()
	{

		//날씨 이미지 연결
		weathers = new ImageView[9];
		weathers[0] = (ImageView)findViewById(R.id.environment1_1);
		weathers[1] = (ImageView)findViewById(R.id.environment2_1);
		weathers[2] = (ImageView)findViewById(R.id.environment3_1);
		weathers[3] = (ImageView)findViewById(R.id.environment4_1);
		weathers[4] = (ImageView)findViewById(R.id.environment5_1);
		weathers[5] = (ImageView)findViewById(R.id.environment6_1);
		weathers[6] = (ImageView)findViewById(R.id.environment7_1);
		weathers[7] = (ImageView)findViewById(R.id.environment8_1);
		weathers[8] = (ImageView)findViewById(R.id.environment9_1);

		//Agent 이미지 연결
		agents  = new ImageView[9];
		agents[0] = (ImageView) findViewById(R.id.agent1);
		agents[1] = (ImageView) findViewById(R.id.agent2);
		agents[2] = (ImageView) findViewById(R.id.agent3);
		agents[3] = (ImageView) findViewById(R.id.agent4);
		agents[4] = (ImageView) findViewById(R.id.agent5);
		agents[5] = (ImageView) findViewById(R.id.agent6);
		agents[6] = (ImageView) findViewById(R.id.agent7);
		agents[7] = (ImageView) findViewById(R.id.agent8);
		agents[8] = (ImageView) findViewById(R.id.agent9);

		//AgentName연결

		agentNames = new TextView[9];
		agentNames[0] = (TextView)findViewById(R.id.agent_name1);
		agentNames[1] = (TextView)findViewById(R.id.agent_name2);
		agentNames[2] = (TextView)findViewById(R.id.agent_name3);
		agentNames[3] = (TextView)findViewById(R.id.agent_name4);
		agentNames[4] = (TextView)findViewById(R.id.agent_name5);
		agentNames[5] = (TextView)findViewById(R.id.agent_name6);
		agentNames[6] = (TextView)findViewById(R.id.agent_name7);
		agentNames[7] = (TextView)findViewById(R.id.agent_name8);
		agentNames[8] = (TextView)findViewById(R.id.agent_name9);

		//환경 이미지 연결
		environments = new ImageView[9][3];
		environments[0][0] = (ImageView)findViewById(R.id.environment1_2);
		environments[0][1] = (ImageView)findViewById(R.id.environment1_3);
		environments[1][0] = (ImageView)findViewById(R.id.environment2_2);
		environments[1][1] = (ImageView)findViewById(R.id.environment2_3);
		environments[2][0] = (ImageView)findViewById(R.id.environment3_2);
		environments[2][1] = (ImageView)findViewById(R.id.environment3_3);
		environments[3][0] = (ImageView)findViewById(R.id.environment4_2);
		environments[3][1] = (ImageView)findViewById(R.id.environment4_3);
		environments[4][0] = (ImageView)findViewById(R.id.environment5_2);
		environments[4][1] = (ImageView)findViewById(R.id.environment5_3);
		environments[5][0] = (ImageView)findViewById(R.id.environment6_2);
		environments[5][1] = (ImageView)findViewById(R.id.environment6_3);
		environments[6][0] = (ImageView)findViewById(R.id.environment7_2);
		environments[6][1] = (ImageView)findViewById(R.id.environment7_3);
		environments[7][0] = (ImageView)findViewById(R.id.environment8_2);
		environments[7][1] = (ImageView)findViewById(R.id.environment8_3);
		environments[8][0] = (ImageView)findViewById(R.id.environment9_2);
		environments[8][1] = (ImageView)findViewById(R.id.environment9_3);

		//Land 연결
		lands = new ImageView[9];
		lands[0]  = (ImageView)findViewById(R.id.land1);
		lands[1]  = (ImageView)findViewById(R.id.land2);
		lands[2]  = (ImageView)findViewById(R.id.land3);
		lands[3]  = (ImageView)findViewById(R.id.land4);
		lands[4]  = (ImageView)findViewById(R.id.land5);
		lands[5]  = (ImageView)findViewById(R.id.land6);
		lands[6]  = (ImageView)findViewById(R.id.land7);
		lands[7]  = (ImageView)findViewById(R.id.land8);
		lands[8]  = (ImageView)findViewById(R.id.land9);

		//숲 밀도 연결
		densitys = new ImageView[9];
		densitys[0] = (ImageView)findViewById(R.id.environment1_4);
		densitys[1] = (ImageView)findViewById(R.id.environment2_4);
		densitys[2] = (ImageView)findViewById(R.id.environment3_4);
		densitys[3] = (ImageView)findViewById(R.id.environment4_4);
		densitys[4] = (ImageView)findViewById(R.id.environment5_4);
		densitys[5] = (ImageView)findViewById(R.id.environment6_4);
		densitys[6] = (ImageView)findViewById(R.id.environment7_4);
		densitys[7] = (ImageView)findViewById(R.id.environment8_4);
		densitys[8] = (ImageView)findViewById(R.id.environment9_4);
		// set Cellpanels
		setCellPanel();
		//ChangeEvn 버튼 연결
		
		final BundleController bundleController= new BundleController(getFilesDir().getAbsolutePath(), getResources(), null);
		
		changeEvn = (Button)findViewById(R.id.change_evn);
		

		changeEvn.setOnClickListener(new OnClickListener() {


			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				try {
					bundleController.installAndStartBundle(R.raw.mapebundle, "mapebundle");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		// TextView
		TextView[] selcted = new TextView[2];
		selcted[0] = (TextView)findViewById(R.id.selected_agent);
		selcted[1] = (TextView)findViewById(R.id.selected_score);
		CellPanels.selectedSet = selcted;
		CellPanels.mainActivity = this;
	}
	private void setCellPanel()
	{
		cellpanels = new CellPanel[9];
		for(int i=0; i<9;i++)
		{
			cellpanels[i] = new CellPanel(agents[i], agentNames[i], weathers[i], environments[i], densitys[i], lands[i]);
		}
		CellPanels.cellPanels = cellpanels;

	}


}
