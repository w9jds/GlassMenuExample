package com.example.glassmenuexample;

import com.google.android.glass.app.Card;
import com.google.android.glass.media.Sounds;
import com.google.android.glass.touchpad.Gesture;
import com.google.android.glass.touchpad.GestureDetector;

import android.media.AudioManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;

public class MainActivity extends Activity 
{
	private GestureDetector mGestureDetector;
	
	private AudioManager maManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		//initialize the audio manager
		maManager = (AudioManager) getSystemService(this.AUDIO_SERVICE);
		//create gesture listener
		mGestureDetector = createGestureDetector(this);
		
		//create a new card for the view
		Card cView = new Card(this);
		//set the text of the card to the hello world string
		cView.setText(R.string.hello_world);
		//set the card as the content view
		setContentView(cView.toView());
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	private GestureDetector createGestureDetector(Context context) 
	{
	    GestureDetector gdDetector = new GestureDetector(context);
        //Create a base listener for generic gestures
        gdDetector.setBaseListener( new GestureDetector.BaseListener() 
        {
            @Override
            public boolean onGesture(Gesture gesture) 
            {
                if (gesture == Gesture.TAP) 
                {
                	//play the tap sound
                	maManager.playSoundEffect(Sounds.TAP);
              		//open the menu
                	openOptionsMenu();
                    return true;
                } 
                
                return false;
            }
        });
        
        return gdDetector;
    }
	
    @Override
    public boolean onGenericMotionEvent(MotionEvent event) 
    {
        if (mGestureDetector != null) 
            return mGestureDetector.onMotionEvent(event);
        
        return false;
    }
	
    @Override
    public boolean onOptionsItemSelected(MenuItem item) 
    {
        // Handle item selection. Menu items typically start another
        // activity, start a service, or broadcast another intent.
        switch (item.getItemId()) 
        {
            case R.id.share_menu_item:
            	//do something on menu item click
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
