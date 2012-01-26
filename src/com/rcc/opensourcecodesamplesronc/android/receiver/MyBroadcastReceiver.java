package com.rcc.opensourcecodesamplesronc.android.receiver;

import java.util.Calendar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.rcc.opensourcecodesamplesronc.android.service.MyIntentService;

/**
 * A Broadcast Receiver to react to an alarm
 * @author Ron Currier
 * 
 */
public class MyBroadcastReceiver extends BroadcastReceiver {

	private final String TAG = MyBroadcastReceiver.class.getSimpleName();
	private final String myServiceName = MyIntentService.class.getSimpleName();

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.content.BroadcastReceiver#onReceive(android.content.Context,
	 * android.content.Intent)
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		
		Log.d(TAG, "in receiver");
		
		String hrString;
		String minString;
		String secString;
		
		Calendar cal = Calendar.getInstance();
		int hr = cal.get(Calendar.HOUR_OF_DAY);
		int min = cal.get(Calendar.MINUTE);
		int sec = cal.get(Calendar.SECOND);
		
		hrString = formatTimeParameter(hr);
		minString = formatTimeParameter(min);
		secString = formatTimeParameter(sec);
		
		String timeString = hrString + ":" + minString + ":" + secString;

		Toast.makeText(context,
				intent.getExtras().getString(myServiceName) + " " + timeString,
				Toast.LENGTH_SHORT).show();
	}
	
	/**
	 * Utility function to help pad the portions of a time appropriately
	 * @param timeParam - int
	 * @return timeParamString - String
	 */
	private String formatTimeParameter(int timeParam){
		
		String timeParamString;
		
		timeParamString = timeParam + "";
		
		if (timeParam < 10){
			timeParamString = "0" + timeParamString;
		}
		
		return timeParamString;
	}

}
