package com.rcc.opensourcecodesamplesronc.android.activity;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/*
 * Creates a notification based on the user's choice of wording
 */
public class NotificationActivity extends Activity {

	private static final int NOTIFY_ID = 1000;

	EditText et_notification;
	Button b_not1;

	/*
	 * Gives screen to create user notification with user text
	 * Layout - notification.xml
	 * Called when the activity is first created.
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notification);

		et_notification = (EditText) findViewById(R.id.et_notification);
		b_not1 = (Button) findViewById(R.id.b_not1);

		b_not1.setOnClickListener(new OnClickListener() {

			/* Notifies user
			 * (non-Javadoc)
			 * @see android.view.View.OnClickListener#onClick(android.view.View)
			 */
			@Override
			public void onClick(View v) {
				notifyMe();
			}
		});
	}

	/*
	 * Notifies user with Notification
	 */
	public void notifyMe() {
		NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

		int icon = R.drawable.owl_image10;
		CharSequence tickerText = et_notification.getText();
		long when = System.currentTimeMillis();
		//tickerText is shown temporarily similar to Toast
		Notification notification = new Notification(icon, tickerText, when);  

		Context context = getApplicationContext();
		CharSequence contentTitle = "My Notification Title";
		//contentText shown when clicking on icon, along with contentTitle
		CharSequence contentText = tickerText;  
		Intent notificationIntent = new Intent();
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				notificationIntent, 0);
		notification.defaults = Notification.DEFAULT_SOUND;
		notification.setLatestEventInfo(context, contentTitle, contentText,
				contentIntent);
		nm.notify(NOTIFY_ID, notification);
	}

	/*
	 * inflates menu for help screen
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.notificationhelpmenu, menu);
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
		case R.id.notificationhelp:
			intent = new Intent("com.rcc.opensourcecodesamplesronc.NOTIFICATIONHELP");
			startActivity(intent);
			break;
		}
		return false;
	}
}
