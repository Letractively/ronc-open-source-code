package com.rcc.opensourcecodesamplesronc.android.activity.help;

import com.rcc.opensourcecodesamplesronc.android.activity.R;

import android.app.Activity;
import android.os.Bundle;

/*
 * Help associated with the AsyncSampleActivity
 */
public class NotificationHelpActivity extends Activity{

	/*
	 * Help for AsyncSampleActivity
	 * Layout asynchelp.xml
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notificationhelp);
	}

}
