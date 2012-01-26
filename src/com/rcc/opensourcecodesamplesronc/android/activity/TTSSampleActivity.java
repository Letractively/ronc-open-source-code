package com.rcc.opensourcecodesamplesronc.android.activity;

import java.util.Locale;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
//import android.widget.Button;
import android.widget.EditText;

/**
 * @author Ron Currier
 * 
 * Activity allows user to enter text and have it spoken through Android's Text To Speech 
 * API.
 *
 */
public class TTSSampleActivity extends Activity implements OnInitListener {
	
	private final String TAG = TTSSampleActivity.class.getSimpleName();
	private TextToSpeech mTts;
	private final static int MY_DATA_CHECK_CODE = 9999;
	private Intent checkTTS;
//	private Button b_tts;
	private EditText et_textToSpeak;
	private String ttSpeakString;
	
	
    /* (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ttssample);        
//        b_tts = (Button) findViewById(R.id.b_tts); 
        
        //Create an intent to contact the TTS engine.  This will check whether or not 
        //the language specific resource files exist.  MY_DATA_CHECK_CODE is an activity 
        //specific variable to verify the activity checked in onActivityResult().
        checkTTS = new Intent();
        checkTTS.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkTTS, MY_DATA_CHECK_CODE);       
    }

	/* (non-Javadoc)
	 * @see android.app.Activity#onActivityResult(int, int, android.content.Intent)
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		//If the request code is the one supplied in startActivityForResult()...
		if (requestCode == MY_DATA_CHECK_CODE){
			Log.d(TAG, "In check code...");
			//If the necessary resources are found to use TTS
			if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS){
				Log.d(TAG, "resultCode Passed!!!!");
				//success create TTS instance
				mTts = new TextToSpeech(this, this);
				
			}else{
				Log.d(TAG, "resultCode did not pass");
				//else... missing resource data, install the TTS resource data
				Intent installIntent = new Intent();
				installIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
				startActivity(installIntent);
			}
		}
	}

	/* (non-Javadoc)
	 * @see android.speech.tts.TextToSpeech.OnInitListener#onInit(int)
	 */
	@Override
	public void onInit(int arg0) {
		
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onDestroy()
	 */
	@Override
	protected void onDestroy() {
		//Make sure to shutdown the TTS resources
		mTts.shutdown();
		super.onDestroy();
	}
	
	/**
	 * 
	 * @param v
	 * The method is called directly from the button pressed
	 */
	public void doSpeakRequest(View v){
		et_textToSpeak = (EditText) findViewById(R.id.et_texttospeak);        
        ttSpeakString = et_textToSpeak.getText().toString();
        
		if (mTts != null){
			//set the language type for the 
        	mTts.setLanguage(Locale.US);
			//set voice to a lower pitch.  Default pitch is 1.0f
			mTts.setPitch(.8f);
			//Speak the string entered, dropping any other speech for the application
			//already in the TTS speech queue.
        	mTts.speak(ttSpeakString, TextToSpeech.QUEUE_FLUSH, null);
        	Log.d(TAG, "Should have spoken");
        }
	}	
	
	/*
	 * inflates menu for help screen
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.ttssamplehelpmenu, menu);
		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent;
		switch (item.getItemId()) {
		case R.id.ttssamplehelp:
			intent = new Intent("com.rcc.opensourcecodesamplesronc.TTSHELP");
			startActivity(intent);
			break;
		}
		return false;
	}
	
}