package com.rcc.opensourcecodesamplesronc.android.service;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;

import com.rcc.opensourcecodesamplesronc.android.activity.IntentServiceBroadcastSampleActivity;
import com.rcc.opensourcecodesamplesronc.android.activity.R;
import com.rcc.opensourcecodesamplesronc.android.receiver.MyBroadcastReceiver;

/**
 * Creates an alarm and notifies the user of the alarm. IntentServices are used
 * for long running services.
 * 
 * @author Ron Currier
 * 
 */
public class MyIntentService extends IntentService {

	private static final String TAG = MyIntentService.class.getSimpleName();
	private NotificationManager nManager;
	private AlarmManager am;
	private PendingIntent activityIntent;
	private Notification notification;
	private final int milliSeconds = 20000;

	/**
	 * Constructor needed for an IntentService
	 */
	public MyIntentService() {
		super(TAG);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.IntentService#onCreate()
	 */
	@Override
	public void onCreate() {
		super.onCreate();
		Log.d(TAG, "in onCreate()");

		// Get NotificationManager from System Service
		nManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		// Create a new notification
		notification = new Notification(R.drawable.owl_image10,
				"MyIntentService running", System.currentTimeMillis());

		// Create a notification the user won't generally delete
		notification.flags = Notification.FLAG_NO_CLEAR;

		// Create an PendingIntent to be used for the notification allowing the
		// user to click the notification and return to the activity, from the
		// home screen for instance.
		activityIntent = PendingIntent.getActivity(this, 0, new Intent(this,
				IntentServiceBroadcastSampleActivity.class), 0);

		Log.d(TAG, "end onCreate()");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.IntentService#onStart(android.content.Intent, int)
	 */
	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		Log.d(TAG, "end onStart()");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.IntentService#onStartCommand(android.content.Intent,
	 * int, int)
	 */
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d(TAG, "in onStartCommand()");
		return super.onStartCommand(intent, flags, startId);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.IntentService#onDestroy()
	 */
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "in onDestroy()");
	}

	// @Override
	// public IBinder onBind(Intent arg0) {
	// return null;
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.IntentService#onHandleIntent(android.content.Intent)
	 */
	@Override
	protected void onHandleIntent(Intent intent) {

		Log.d(TAG, "in onHandleIntent()");

		// Create an intent to access the Broadcast Receiver and an an extra
		Intent broadcastIntent = new Intent(this, MyBroadcastReceiver.class);
		broadcastIntent.putExtra(TAG, "The time is:");

		// Access AlarmManager, System Service
		am = (AlarmManager) getSystemService(ALARM_SERVICE);

		// Create a PendingIntent appropriate for a BroadcastReceiver
		PendingIntent pendI = PendingIntent.getBroadcast(this, 1,
				broadcastIntent, 0);

		// Create Repeating alarm. RTC_WAKEUP is used to partially wake up the
		// device, if necessary
		am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),
				milliSeconds, pendI);

		// Notify user
		notification.setLatestEventInfo(this, TAG, "Repeated Alarm created in "
				+ TAG, activityIntent);
		nManager.notify(0, notification);

	}
}
