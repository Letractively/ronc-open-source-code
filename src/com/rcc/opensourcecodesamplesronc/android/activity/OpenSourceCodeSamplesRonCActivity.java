package com.rcc.opensourcecodesamplesronc.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/*
 * Activity giving the various sample options for the application
 */
public class OpenSourceCodeSamplesRonCActivity extends Activity {

	Button b_spinner, b_cpSample, b_thumbnail, b_asyncTaskSample,
			b_dateTimeSample, b_notification, b_webSample,
			b_intentServiceBroadcastSample, b_ttsSample, b_osCodeHelp;

	/*
	 * Allows user to choose an activity sample button on the screen Layout
	 * main.xml Called when the activity is first created. (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		b_spinner = (Button) findViewById(R.id.b_spinnersample);
		b_cpSample = (Button) findViewById(R.id.b_cpsample);
		b_thumbnail = (Button) findViewById(R.id.b_thumbnailsample);
		b_asyncTaskSample = (Button) findViewById(R.id.b_asynctasksample);
		b_dateTimeSample = (Button) findViewById(R.id.b_datetimesample);
		b_notification = (Button) findViewById(R.id.b_notification);
		b_webSample = (Button) findViewById(R.id.b_websample);
		b_intentServiceBroadcastSample = (Button) findViewById(R.id.b_intentservicebroadcastsample);
		b_ttsSample = (Button) findViewById(R.id.b_ttssample);
		b_osCodeHelp = (Button) findViewById(R.id.b_oscodehelp);
		b_spinner.setOnClickListener(new OnClickListener() {

			/*
			 * Calls Spinner Sample (non-Javadoc)
			 * 
			 * @see android.view.View.OnClickListener#onClick(android.view.View)
			 */
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(
						"com.rcc.opensourcecodesamplesronc.SPINNER_SAMPLE");
				startActivity(intent);
			}
		});

		b_cpSample.setOnClickListener(new OnClickListener() {

			/*
			 * Calls Content Provider Sample (non-Javadoc)
			 * 
			 * @see android.view.View.OnClickListener#onClick(android.view.View)
			 */
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(
						"com.rcc.opensourcecodesamplesronc.CONTENTPROVIDER_SAMPLE");
				startActivity(intent);
			}
		});

		b_thumbnail.setOnClickListener(new OnClickListener() {

			/*
			 * Calls Thumbnail Sample (non-Javadoc)
			 * 
			 * @see android.view.View.OnClickListener#onClick(android.view.View)
			 */
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(
						"com.rcc.opensourcecodesamplesronc.THUMBNAIL_SAMPLE");
				startActivity(intent);
			}
		});

		b_asyncTaskSample.setOnClickListener(new OnClickListener() {

			/*
			 * Calls Async Sample (non-Javadoc)
			 * 
			 * @see android.view.View.OnClickListener#onClick(android.view.View)
			 */
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(
						"com.rcc.opensourcecodesamplesronc.ASYNCTASK_SAMPLE");
				startActivity(intent);
			}
		});

		b_dateTimeSample.setOnClickListener(new OnClickListener() {

			/*
			 * Calls DateTime Picker (non-Javadoc)
			 * 
			 * @see android.view.View.OnClickListener#onClick(android.view.View)
			 */
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(
						"com.rcc.opensourcecodesamplesronc.DATETIMEPICKER");
				startActivity(intent);
			}
		});

		b_notification.setOnClickListener(new OnClickListener() {

			/*
			 * Calls Notification Activity Sample (non-Javadoc)
			 * 
			 * @see android.view.View.OnClickListener#onClick(android.view.View)
			 */
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(
						"com.rcc.opensourcecodesamplesronc.NOTIFICATION");
				startActivity(intent);
			}
		});

		b_webSample.setOnClickListener(new OnClickListener() {

			/*
			 * Calls Web Sample (non-Javadoc)
			 * 
			 * @see android.view.View.OnClickListener#onClick(android.view.View)
			 */
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(
						"com.rcc.opensourcecodesamplesronc.WEBSAMPLE");
				startActivity(intent);
			}
		});
		
		b_intentServiceBroadcastSample.setOnClickListener(new OnClickListener() {

			/*
			 * Calls Intent Service Broadcast Sample (non-Javadoc)
			 * 
			 * @see android.view.View.OnClickListener#onClick(android.view.View)
			 */
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(
						"com.rcc.opensourcecodesamplesronc.INTENTSERVICEBROADCAST");
				startActivity(intent);
			}
		});
		
		b_ttsSample.setOnClickListener(new OnClickListener() {

			/*
			 * Calls Web Sample (non-Javadoc)
			 * 
			 * @see android.view.View.OnClickListener#onClick(android.view.View)
			 */
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(
						"com.rcc.opensourcecodesamplesronc.TTS");
				startActivity(intent);
			}
		});

		b_osCodeHelp.setOnClickListener(new OnClickListener() {

			/*
			 * Calls About/Help Menus (non-Javadoc)
			 * 
			 * @see android.view.View.OnClickListener#onClick(android.view.View)
			 */
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(
						"com.rcc.opensourcecodesamplesronc.OSCODEHELP");
				startActivity(intent);
			}
		});
	}

	/*
	 * inflates menu for help screen (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.oschelpmenu, menu);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent;
		switch (item.getItemId()) {
		case R.id.oscasynchelp:
			intent = new Intent("com.rcc.opensourcecodesamplesronc.ASYNCHELP");
			startActivity(intent);
			break;
		case R.id.osccpsampleshelp:
			intent = new Intent(
					"com.rcc.opensourcecodesamplesronc.CPSAMPLESHELP");
			startActivity(intent);
			break;
		case R.id.oscdtphelp:
			intent = new Intent("com.rcc.opensourcecodesamplesronc.DTPHELP");
			startActivity(intent);
			break;
		case R.id.oscnotificationhelp:
			intent = new Intent(
					"com.rcc.opensourcecodesamplesronc.NOTIFICATIONHELP");
			startActivity(intent);
			break;
		case R.id.oscspinnerhelp:
			intent = new Intent("com.rcc.opensourcecodesamplesronc.SPINNERHELP");
			startActivity(intent);
			break;
		case R.id.oscthumbnailhelp:
			intent = new Intent(
					"com.rcc.opensourcecodesamplesronc.THUMBNAILHELP");
			startActivity(intent);
			break;
		case R.id.oscwebsamplehelp:
			intent = new Intent("com.rcc.opensourcecodesamplesronc.WEBHELP");
			startActivity(intent);
			break;
		case R.id.oscisbsamplehelp:
			intent = new Intent("com.rcc.opensourcecodesamplesronc.INTENTSERVICEBROADCASTHELP");
			startActivity(intent);
			break;
		case R.id.oscttssamplehelp:
			intent = new Intent("com.rcc.opensourcecodesamplesronc.TTSHELP");
			startActivity(intent);
			break;

		}
		return false;
	}
}