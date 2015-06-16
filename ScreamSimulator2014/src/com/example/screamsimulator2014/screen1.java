package com.example.screamsimulator2014;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

//first screen with instructions
public class screen1 extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.screen1);
		//initialize button
		final Button start = (Button) findViewById(R.id.button1);				
		start.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//start app, go to next screen
				Intent intent = new Intent(getApplicationContext(), screen2.class);
				startActivity(intent);	
				finish();			
			}
		});
	}
	
}
