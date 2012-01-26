package com.rcc.opensourcecodesamplesronc.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/*
 * Shows a sample of a 2 Spinners populated by an Array of colors in strings.xml
 */
public class SpinnerSampleActivity extends Activity{
	
	private final String TAG = SpinnerSampleActivity.class.getSimpleName();
	
	LinearLayout llsp1;
	TextView txt_sptxt;
	Spinner sp1, sp2;

	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "entered onCreate()");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.spinner);
		
		llsp1 = (LinearLayout) findViewById(R.id.ll_spinner1);
		txt_sptxt = (TextView) findViewById(R.id.txt_sptxt);
		sp1 = (Spinner) findViewById(R.id.sp1);
		sp2 = (Spinner) findViewById(R.id.sp2);
		
		ArrayAdapter<CharSequence> spAdapter = ArrayAdapter.createFromResource(this, R.array.strarray_colors, android.R.layout.simple_spinner_item);	
		spAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		sp1.setAdapter(spAdapter);
		sp1.setPrompt(getResources().getText(R.string.setbackground));
		
		sp2.setAdapter(spAdapter);
		sp2.setPrompt(getResources().getText(R.string.settext));
		
		sp1.setOnItemSelectedListener(new OnItemSelectedListener() {

			/*
			 * When an item is selected from the Background Color Spinner, set background
			 * using colors from mycolors.xml 
			 * (non-Javadoc)
			 * @see android.widget.AdapterView.OnItemSelectedListener#onItemSelected(android.widget.AdapterView, android.view.View, int, long)
			 */
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				
				int selection = sp1.getSelectedItemPosition();
				
				switch (selection) {
				case 0:
					break;
				case 1:
					llsp1.setBackgroundColor(getResources().getColor(R.color.red));
					break;
				case 2:
					llsp1.setBackgroundColor(getResources().getColor(R.color.green));
					break;
				case 3:
					llsp1.setBackgroundColor(getResources().getColor(R.color.blue));
					break;
				case 4:
					llsp1.setBackgroundColor(getResources().getColor(R.color.aqua));
					break;
				case 5:
					llsp1.setBackgroundColor(getResources().getColor(R.color.translucent_red));
					break;
				case 6:
					llsp1.setBackgroundColor(getResources().getColor(R.color.translucent_aqua));
					break;
				case 7:
					llsp1.setBackgroundColor(getResources().getColor(R.color.yellow));
					break;
				case 8:
					llsp1.setBackgroundColor(getResources().getColor(R.color.orangish));
					break;

				default:
					break;
				}
				
			}

			/*
			 * (non-Javadoc)
			 * @see android.widget.AdapterView.OnItemSelectedListener#onNothingSelected(android.widget.AdapterView)
			 */
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// 
				Toast.makeText(getBaseContext(), (getResources().getString(R.string.selectItem)).toString(), Toast.LENGTH_LONG).show();
			}
			
		});
		
		sp2.setOnItemSelectedListener(new OnItemSelectedListener() {

			/*
			 * When an item is selected from the Text Color Spinner, set text color
			 * using colors from mycolors.xml
			 * (non-Javadoc)
			 * @see android.widget.AdapterView.OnItemSelectedListener#onItemSelected(android.widget.AdapterView, android.view.View, int, long)
			 */
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				
				int selection = sp2.getSelectedItemPosition();
				
				switch (selection) {
				case 0:
					break;
				case 1:
					txt_sptxt.setTextColor(getResources().getColor(R.color.red));
					break;
				case 2:
					txt_sptxt.setTextColor(getResources().getColor(R.color.green));
					break;
				case 3:
					txt_sptxt.setTextColor(getResources().getColor(R.color.blue));
					break;
				case 4:
					txt_sptxt.setTextColor(getResources().getColor(R.color.aqua));
					break;
				case 5:
					txt_sptxt.setTextColor(getResources().getColor(R.color.translucent_red));
					break;
				case 6:
					txt_sptxt.setTextColor(getResources().getColor(R.color.translucent_aqua));
					break;
				case 7:
					txt_sptxt.setTextColor(getResources().getColor(R.color.yellow));
					break;
				case 8:
					txt_sptxt.setTextColor(getResources().getColor(R.color.orangish));
					break;

				default:
					break;
				}
				
			}

			/*
			 * (non-Javadoc)
			 * @see android.widget.AdapterView.OnItemSelectedListener#onNothingSelected(android.widget.AdapterView)
			 */
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// 
				Toast.makeText(getBaseContext(), (getResources().getString(R.string.selectItem)).toString(), Toast.LENGTH_LONG).show();
			}
			
		});
	}
	
	/*
	 * inflates menu for help screen
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.spinnerhelpmenu, menu);
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
		case R.id.spinnerhelp:
			intent = new Intent("com.rcc.opensourcecodesamplesronc.SPINNERHELP");
			startActivity(intent);
			break;
		}
		return false;
	}

}
