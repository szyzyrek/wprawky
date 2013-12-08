package edu.szyrek.wprawki;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.util.List;

public class MainActivity extends Activity
{
	private LinearLayout mainLayout;
    private TextView label;
	private ListTester lt = new ListTester();
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		mainLayout = new LinearLayout(this);
		
		label = new TextView(this);
		label.setText("cmon click him already");
		
		Button helloButton = new Button(this);
		helloButton.setText("click me!");
		helloButton.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View view){
				label.setText(lt.getResult());
			}
		});
		mainLayout.addView(label);
    	mainLayout.addView(helloButton);
		//setContentView(mainLayout);
	}
}
