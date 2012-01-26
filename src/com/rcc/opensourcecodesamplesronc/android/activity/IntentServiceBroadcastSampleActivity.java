package com.rcc.opensourcecodesamplesronc.android.activity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.rcc.opensourcecodesamplesronc.android.receiver.MyBroadcastReceiver;
import com.rcc.opensourcecodesamplesronc.android.service.MyIntentService;

/**
 * Pressing Start runs the IntentService beginning the alarm service and
 * creating a notification. Pressing Stop stops the alarm service and initial
 * notification, beginning a second notification.
 * 
 * @author Ron Currier
 * 
 */
public class IntentServiceBroadcastSampleActivity extends Activity {

	private String TAG = IntentServiceBroadcastSampleActivity.class
			.getSimpleName();
	private Button b_StartService, b_StopService;
	private Intent intent;
	private Context sbsaContext = this;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.intentservice);

		b_StartService = (Button) findViewById(R.id.b_serviceStart);
		b_StopService = (Button) findViewById(R.id.b_serviceStop);

		intent = new Intent(this, MyIntentService.class);

		b_StartService.setOnClickListener(new OnClickListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see android.view.View.OnClickListener#onClick(android.view.View)
			 */
			@Override
			public void onClick(View paramView) {
				sbsaContext.startService(intent);
				
				//Cancel Notification
				NotificationManager nManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
				//cancel notification of canceled alarm, if it exists
				nManager.cancel(1);
				
				Log.d(TAG, "after startService");
			}
		});

		b_StopService.setOnClickListener(new OnClickListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see android.view.View.OnClickListener#onClick(android.view.View)
			 */
			@Override
			public void onClick(View paramView) {

				//Cancel Alarm
				AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
				Intent broadcastIntent = new Intent(sbsaContext,
						MyBroadcastReceiver.class);
				PendingIntent pi = PendingIntent.getBroadcast(sbsaContext, 1,
						broadcastIntent, 0);
				//cancel alarm
				am.cancel(pi);

				//Cancel Notification
				NotificationManager nManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
				//cancel notification
				nManager.cancel(0);
				
				//Create new notification that alarm has ended
				Notification notification = new Notification(
						R.drawable.owl_image3, "Alarm has ended", System
								.currentTimeMillis());
				PendingIntent activityIntent = PendingIntent.getActivity(
						sbsaContext, 0, new Intent(sbsaContext,
								IntentServiceBroadcastSampleActivity.class), 0);
				notification.setLatestEventInfo(sbsaContext, "Alarm Ended",
						"Alarm Ended", activityIntent);
				nManager.notify(1, notification);

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
		inflater.inflate(R.menu.intentservicehelpmenu, menu);
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
		case R.id.intentservicehelp:
			intent = new Intent(
					"com.rcc.opensourcecodesamplesronc.INTENTSERVICEBROADCASTHELP");
			startActivity(intent);
			break;
		}
		return false;
	}
}