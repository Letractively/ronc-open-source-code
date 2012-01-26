package com.rcc.opensourcecodesamplesronc.android.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;

/*
 * Allows a choice of an internal web browser or full external web browser based on
 * which is chosen from an alert dialog
 */
public class WebSampleActivity extends Activity{

	private final String TAG = WebSampleActivity.class.getSimpleName();
	private final String WEBSITE = "http://www.google.com";
	private String[] webType;
	WebView wv_webSample;
	Button b_webReset;
	
	/*
	 * Brings up an alert dialog to allow a user to decide for an internal browser upon
	 * an external browser
	 * Called when activity is first created.
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.websample);
		
		webType = getResources().getStringArray(R.array.strarray_webtype);
		b_webReset = (Button) findViewById(R.id.b_webreset);
		b_webReset.setOnClickListener(new OnClickListener() {
			/*
			 * Allows button to re-show alert for choice of internal/external web browsing
			 * (non-Javadoc)
			 * @see android.view.View.OnClickListener#onClick(android.view.View)
			 */
			@Override
			public void onClick(View v) {
				alertMulti();
			}
		});
		
		alertMulti();
	}
	
	/*
	 * Builds an alert dialog allowing the user to choose from an internal or external(full)
	 * browser
	 */
	public void alertMulti(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Choose Web Access Type");
		builder.setSingleChoiceItems(webType, -1, new DialogInterface.OnClickListener(){
			
			public void onClick(DialogInterface dialog, int item){
				//Internal Web
				if (item == 0){
					wv_webSample = (WebView) findViewById(R.id.wv_websample);
					wv_webSample.getSettings().setJavaScriptEnabled(true);
					wv_webSample.loadUrl(WEBSITE);
				}else{
					//External full Web
					Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(WEBSITE));
					startActivity(intent);
				}
				
				dialog.dismiss();
			}
		});
		
		AlertDialog alert = builder.create();
		alert.show();
		
	}

	/*
	 * Allows for browsing to previous pages with back buttons
	 * (non-Javadoc)
	 * @see android.app.Activity#onKeyDown(int, android.view.KeyEvent)
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (wv_webSample == null){
			Log.d(TAG, "onKeyDown() wv_webSample == null");
			wv_webSample = (WebView) findViewById(R.id.wv_websample);
		}
			
		if ((keyCode == KeyEvent.KEYCODE_BACK) && wv_webSample.canGoBack()){
			Log.d(TAG, "onKeyDown() inside");
			wv_webSample.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	/*
	 * inflates menu for help screen
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.websamplehelpmenu, menu);
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
		case R.id.websamplehelp:
			intent = new Intent("com.rcc.opensourcecodesamplesronc.WEBHELP");
			startActivity(intent);
			break;
		}
		return false;
	}
}
