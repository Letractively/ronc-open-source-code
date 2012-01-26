package com.rcc.opensourcecodesamplesronc.android.util;

import java.util.Random;

import com.rcc.opensourcecodesamplesronc.android.activity.R;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

/*
 * Asynchronous Task interacting with calling activity to display a progress bar and to 
 * display the sum of time in a sleeping state.  It sleeps for up to 1 second 5 times with
 * the precise amount of time determined randomly
 */
public class AsyncTaskSample extends AsyncTask<String, Integer, String> {
	private final String TAG = AsyncTaskSample.class.getSimpleName();

	private Context callingContext; // allows interaction with calling activity
									// context
	ProgressBar pb;
	TextView tv;

	private int progress = -1;

	/*
	 * Constructor to accept context from calling activity
	 */
	public AsyncTaskSample(Context context) {
		Log.d(TAG, "In ATS constructor");
		callingContext = context;

	}

	/*
	 * Executed after doInBackground()
	 * (non-Javadoc)
	 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
	 */
	@Override
	protected void onPostExecute(String result) {
		Log.d(TAG, "In onPostExecute()");
		pb.setProgress(100); // set to 100 for any round off errors that may
								// have occurred
		tv.setVisibility(View.VISIBLE);
		tv.setText(callingContext.getResources().getText(R.string.totaltime)
				+ " " + result + " "
				+ callingContext.getResources().getText(R.string.seconds));
	}

	/*
	 * Executed before doInBackground().  Do initial work here.
	 * (non-Javadoc)
	 * @see android.os.AsyncTask#onPreExecute()
	 */
	@Override
	protected void onPreExecute() {
		// can do initial work here
		Log.d(TAG, "In onPreExecute()");
		pb = (ProgressBar) ((Activity) callingContext)
				.findViewById(R.id.progress);
		pb.setVisibility(View.VISIBLE);

		tv = (TextView) ((Activity) callingContext).findViewById(R.id.text1);
		tv.setVisibility(View.INVISIBLE);

	}

	/*
	 * Called by system by calling publishProgress()
	 * (non-Javadoc)
	 * @see android.os.AsyncTask#onProgressUpdate(Progress[])
	 */
	@Override
	protected void onProgressUpdate(Integer... values) {
		Log.d(TAG, "In onProgressUpdate()");
		pb.setProgress(values[0]);  //update progress bar

	}

	/*
	 * Method where work is done in background thread.  
	 * DO NOT access Android Widgets here.
	 * (non-Javadoc)
	 * @see android.os.AsyncTask#doInBackground(Params[])
	 */
	@Override
	protected String doInBackground(String... input) {
		Log.d(TAG, "In doInBackground()");
		Random generator = new Random();
		int totalSleep = 0;
		int sleepTime, times = 5;

		for (int i = 0; i < times; i++) {
			sleepTime = generator.nextInt(1000);
			totalSleep += sleepTime;
			sleeping(sleepTime);
			progress += (int) (100.0 / times);
			publishProgress(progress);  //calls onProgressUpdate()
		}
		return (totalSleep/1000.0 + "");

	}

	/*
	 * Puts thread to sleep for a number of millisecs
	 * @param int - number of milliseconds to sleep
	 */
	private void sleeping(int milliSeconds) {
		Log.d(TAG, "In sleeping()");
		try {
			Thread.sleep(milliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
			Log.d(TAG, "Thread interrupted while sleeping");
		}
	}

}
