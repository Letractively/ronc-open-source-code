package com.rcc.opensourcecodesamplesronc.android.activity;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

/*
 * Allows the user to choose 2 dates and times from
 * DatePicker and from TimePicker to be shown formatted on the screen
 */
public class DateTimePickerActivity extends Activity {
	private static final String TAG = DateTimePickerActivity.class
			.getSimpleName();

	private static final int ARRIVAL_DATE_DIALOG = 1;
	private static final int DEPARTURE_DATE_DIALOG = 2;
	private static final int ARRIVAL_TIME_DIALOG = 3;
	private static final int DEPARTURE_TIME_DIALOG = 4;

	Button b_arriveDate, b_departDate;
	Button b_arriveTime, b_departTime;
	TextView tv_arriveDate, tv_departDate;
	TextView tv_arriveTime, tv_departTime;

	private int ayr, amo, aday;
	private int dyr, dmo, dday;
	private int ahr, amin;
	private int dhr, dmin;

	/*
	 * Called when the activity is first created. (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.datetime);

		b_arriveDate = (Button) findViewById(R.id.b_arriveDate);
		b_departDate = (Button) findViewById(R.id.b_departDate);
		b_arriveTime = (Button) findViewById(R.id.b_arriveTime);
		b_departTime = (Button) findViewById(R.id.b_departTime);

		tv_arriveDate = (TextView) findViewById(R.id.tv_arriveDate);
		tv_departDate = (TextView) findViewById(R.id.tv_departDate);
		tv_arriveTime = (TextView) findViewById(R.id.tv_arriveTime);
		tv_departTime = (TextView) findViewById(R.id.tv_departTime);

		b_arriveDate.setOnClickListener(new OnClickListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see android.view.View.OnClickListener#onClick(android.view.View)
			 */
			@Override
			public void onClick(View v) {
				showDialog(ARRIVAL_DATE_DIALOG);

			}
		});

		b_departDate.setOnClickListener(new OnClickListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see android.view.View.OnClickListener#onClick(android.view.View)
			 */
			@Override
			public void onClick(View v) {
				showDialog(DEPARTURE_DATE_DIALOG);

			}
		});

		b_arriveTime.setOnClickListener(new OnClickListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see android.view.View.OnClickListener#onClick(android.view.View)
			 */
			@Override
			public void onClick(View v) {
				showDialog(ARRIVAL_TIME_DIALOG);

			}
		});

		b_departTime.setOnClickListener(new OnClickListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see android.view.View.OnClickListener#onClick(android.view.View)
			 */
			@Override
			public void onClick(View v) {
				showDialog(DEPARTURE_TIME_DIALOG);

			}
		});

		// Get current date/time
		Calendar cal = Calendar.getInstance();
		aday = cal.get(Calendar.DAY_OF_MONTH);
		amo = cal.get(Calendar.MONTH);
		ayr = cal.get(Calendar.YEAR);
		ahr = cal.get(Calendar.HOUR);
		amin = cal.get(Calendar.MINUTE);
		dday = cal.get(Calendar.DAY_OF_MONTH);
		dmo = cal.get(Calendar.MONTH);
		dyr = cal.get(Calendar.YEAR);
		dhr = cal.get(Calendar.HOUR);
		dmin = cal.get(Calendar.MINUTE);

	}

	/*
	 * Create appropriate picker dialogs with dates/times defaulted or
	 * previously chosen (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreateDialog(int)
	 */
	@Override
	protected Dialog onCreateDialog(int id) {
		if (id == ARRIVAL_DATE_DIALOG) {
			// Create a DatePickerDialog with a DatePicker calendar view if the
			// user's sdk version is 11 or greater
			// setCalendarViewShown() was new in version 11, Honeycomb
			DatePickerDialog dpd = new DatePickerDialog(this, aDateSetListener,
					ayr, amo, aday);
			if (android.os.Build.VERSION.SDK_INT >= 11) {
				Log.d(TAG, "Arrival... current version >= 11");
				DatePicker dp = dpd.getDatePicker();
				dp.setCalendarViewShown(true);
			}
			return dpd;
		} else if (id == DEPARTURE_DATE_DIALOG) {
			// Create a DatePickerDialog with a DatePicker calendar view if the
			// user's sdk version is 11 or greater
			// setCalendarViewShown() was new in version 11, Honeycomb
			DatePickerDialog dpd = new DatePickerDialog(this, dDateSetListener,
					dyr, dmo, dday);
			if (android.os.Build.VERSION.SDK_INT >= 11) {
				Log.d(TAG, "Departure... current version >= 11");
				DatePicker dp = dpd.getDatePicker();
				dp.setCalendarViewShown(true);
			}
			return dpd;
		} else if (id == ARRIVAL_TIME_DIALOG) {
			return new TimePickerDialog(this, aTimeSetListener, ahr, amin, true);
		} else if (id == DEPARTURE_TIME_DIALOG) {
			return new TimePickerDialog(this, dTimeSetListener, dhr, dmin, true);
		}
		return null;

	}

	DatePicker.OnDateChangedListener aDPListener = new DatePicker.OnDateChangedListener() {

		@Override
		public void onDateChanged(DatePicker paramDatePicker, int paramInt1,
				int paramInt2, int paramInt3) {
			ayr = paramInt1;
			amo = paramInt2;
			aday = paramInt3;
			Log.d(TAG, "year = " + paramInt1 + ", mo = " + paramInt2
					+ ", day = " + paramInt3);
		}
	};

	DatePickerDialog.OnDateSetListener aDateSetListener = new DatePickerDialog.OnDateSetListener() {
		/*
		 * When date picked, save dates in class vars and display on screen
		 * (non-Javadoc)
		 * 
		 * @see
		 * android.app.DatePickerDialog.OnDateSetListener#onDateSet(android.
		 * widget.DatePicker, int, int, int)
		 */
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {

			ayr = year;
			amo = monthOfYear;
			aday = dayOfMonth;
			Log.d(TAG, "year Dialog = " + year + ", mo = " + monthOfYear
					+ ", day = " + dayOfMonth);
			tv_arriveDate.setText(buildDate(aday, amo, ayr));
		}

	};

	DatePickerDialog.OnDateSetListener dDateSetListener = new DatePickerDialog.OnDateSetListener() {
		/*
		 * When date picked, save dates in class vars and display on screen
		 * (non-Javadoc)
		 * 
		 * @see
		 * android.app.DatePickerDialog.OnDateSetListener#onDateSet(android.
		 * widget.DatePicker, int, int, int)
		 */
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			dyr = year;
			dmo = monthOfYear;
			dday = dayOfMonth;
			tv_departDate.setText(buildDate(dday, dmo, dyr));
		}
	};

	TimePickerDialog.OnTimeSetListener aTimeSetListener = new TimePickerDialog.OnTimeSetListener() {

		/*
		 * When time picked, save times in class vars and display on screen
		 * (non-Javadoc)
		 * 
		 * @see
		 * android.app.TimePickerDialog.OnTimeSetListener#onTimeSet(android.
		 * widget.TimePicker, int, int)
		 */
		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			ahr = hourOfDay;
			amin = minute;
			tv_arriveTime.setText(buildTime(ahr, amin));

		}
	};

	TimePickerDialog.OnTimeSetListener dTimeSetListener = new TimePickerDialog.OnTimeSetListener() {

		/*
		 * When time picked, save times in class vars and display on screen
		 * (non-Javadoc)
		 * 
		 * @see
		 * android.app.TimePickerDialog.OnTimeSetListener#onTimeSet(android.
		 * widget.TimePicker, int, int)
		 */
		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			dhr = hourOfDay;
			dmin = minute;
			tv_departTime.setText(buildTime(dhr, dmin));

		}
	};

	/*
	 * Utility method to format date for screen
	 * 
	 * @param day
	 * 
	 * @param mo
	 * 
	 * @param year
	 */
	private StringBuilder buildDate(int day, int mo, int year) {
		String strDay;
		String strMo;
		
		if (day < 10){
			strDay = "0" + day;			
		}else{
			strDay = day + "";
		}
		
		mo++; // Month is 0 based so add 1
		if (mo < 10){
			strMo = "0" + mo;
		}else{
			strMo = mo + "";
		}
		
		return new StringBuilder()
				.append(strMo).append("-").append(strDay).append("-")
				.append(year).append(" ");
	}

	/*
	 * Utility method to format time for screen
	 * 
	 * @param hour
	 * 
	 * @param min
	 */
	private StringBuilder buildTime(int hour, int min) {
		String strHour;
		String strMin;
		
		if (hour < 10){
			strHour = "0" + hour;			
		}else{
			strHour = hour + "";
		}
		
		if (min < 10){
			strMin = "0" + min;
		}else{
			strMin = min + "";
		}
		
		return new StringBuilder().append(strHour).append(":").append(strMin);
	}

	/*
	 * Restore instance data to screen output (non-Javadoc)
	 * 
	 * @see android.app.Activity#onRestoreInstanceState(android.os.Bundle)
	 */
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		tv_arriveDate.setText(savedInstanceState
				.getCharSequence("arrivalDateCharSeq"));
		tv_departDate.setText(savedInstanceState
				.getCharSequence("departureDateCharSeq"));
		tv_arriveTime.setText(savedInstanceState
				.getCharSequence("arrivalTimeCharSeq"));
		tv_departTime.setText(savedInstanceState
				.getCharSequence("departureTimeCharSeq"));
	}

	/*
	 * Save instance data for screen output (non-Javadoc)
	 * 
	 * @see android.app.Activity#onSaveInstanceState(android.os.Bundle)
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putCharSequence("arrivalDateCharSeq", tv_arriveDate.getText());
		outState.putCharSequence("departureDateCharSeq",
				tv_departDate.getText());
		outState.putCharSequence("arrivalTimeCharSeq", tv_arriveTime.getText());
		outState.putCharSequence("departureTimeCharSeq",
				tv_departTime.getText());
	}

	/*
	 * inflates menu for help screen (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.dtphelpmenu, menu);
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
		case R.id.dtphelp:
			intent = new Intent("com.rcc.opensourcecodesamplesronc.DTPHELP");
			startActivity(intent);
			break;
		}
		return false;
	}

}