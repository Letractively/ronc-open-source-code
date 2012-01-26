package com.rcc.opensourcecodesamplesronc.android.activity;

import com.rcc.opensourcecodesamplesronc.android.util.AsyncTaskSample;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
//import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

/*
 * AsyncSampleActivity gives a sample of executing a task asynchronously.  
 * This is important when the task at hand takes more than a few seconds, as 
 * the user is otherwise waiting and the Activity may timeout.
 */
public class AsyncSampleActivity extends Activity {

//	private static String TAG = AsyncSampleActivity.class.getSimpleName();

	private AsyncTaskSample ats;

	/*
	 * Called when the activity is first created.
	 * Layout asyncsample.xml
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.asyncsample);

	}

	/*
	 * Method called when pressing the button
	 * @param View v
	 */
	public void doClickAsync(View v) {
		if (ats != null) {
			// If task exists and not finished, wait for it to finish.
			// Do not want extra threads due to button pressed too many times
			if (ats.getStatus() != AsyncTask.Status.FINISHED) {
				return;
			}
		}
		ats = new AsyncTaskSample(this);
		ats.execute();
	}
	
	/*
	 * inflates menu for help screen
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.asynchelpmenu, menu);
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
		case R.id.asynchelp:
			intent = new Intent("com.rcc.opensourcecodesamplesronc.ASYNCHELP");
			startActivity(intent);
			break;
		}
		return false;
	}
}
