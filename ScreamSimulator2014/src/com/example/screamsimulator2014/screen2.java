package com.example.screamsimulator2014;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.TextView;

public class screen2 extends Activity {
	//initialize public variables
	int counter = 0;
	int record = 0;
	String fileName = "record";
	boolean satanMode = false;
	MediaPlayer mp;
	protected void onCreate(Bundle savedInstanceState) {
		
		//read save file
		read();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.screen2);
		
		//initialize and set values for components
		final TextView countDisplay = (TextView) findViewById(R.id.textView1);
		//start at 0 clicks
		countDisplay.setText("0");
		final TextView recordDisplay = (TextView) findViewById(R.id.textView2);
		//display current record
		recordDisplay.setText("Record: " + record);
		final ImageView image1 = (ImageView) findViewById(R.id.imageView1);
		final ImageView image2 = (ImageView) findViewById(R.id.imageView2);	
		mp = MediaPlayer.create(this, R.raw.yolo);
		mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
		//set value of Satan mode background colour
		final int darkRed = Color.parseColor("#320000");
		final View view = this.findViewById(android.R.id.content);		
		image2.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				//on press
				if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
					//change frame and restart sound file
					image1.setVisibility(ImageView.INVISIBLE);					
					mp.seekTo(0);
					mp.start();
				//on release
				} else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
					//change frame
					image1.setVisibility(ImageView.VISIBLE);
					//increment click count
					counter++;					
					countDisplay.setText(String.valueOf(counter));
					//new record
					if (counter > record) {
						//change record value
						record = counter;						
						recordDisplay.setText("Record: "
								+ String.valueOf(record));
						save();

					}
					//trigger Satan mode at 66 clicks
					if (satanMode == false&& counter == 66){
						//change background colour, text colour, sound file, and frames
						view.setBackgroundColor(darkRed);
						image1.setImageResource(R.drawable.satan1);
						image2.setImageResource(R.drawable.satan2);
						countDisplay.setTextColor(Color.WHITE);
						recordDisplay.setTextColor(Color.WHITE);						
						mp = MediaPlayer.create(getApplicationContext(), R.raw.satanyolo);
						//Satan mode is on, so this block will not be revisited						
						satanMode = true;
					}
				}

				return true;

			}

		});

	}


	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	//This method writes to file to save the current record
	private void save() {
		try {
			//open file
			FileOutputStream outputStream = openFileOutput(fileName,
					Context.MODE_PRIVATE);
			//write value
			outputStream.write(String.valueOf(record).getBytes());
			//close file
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void read() {

		try {
			//open file
			FileInputStream inputStream = openFileInput(fileName);
			BufferedReader r = new BufferedReader(new InputStreamReader(
					inputStream));
			//read file
			String text = r.readLine();
			//close file
			r.close();			
			inputStream.close();
			//convert text into integer and store
			record = Integer.parseInt(text);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
