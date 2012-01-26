package com.rcc.opensourcecodesamplesronc.android.activity;

import com.rcc.opensourcecodesamplesronc.android.bean.UserBean;
import com.rcc.opensourcecodesamplesronc.android.dao.UserDAO;
import com.rcc.opensourcecodesamplesronc.android.table.UserTableMetaData;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

/**
 * @author Ron Currier
 * This is a sample activity for creating content providers
 */
public class ContentProviderSamplesActivity extends ListActivity {

	private final static String TAG = ContentProviderSamplesActivity.class.getSimpleName();
	
	private ListView lv = null;
	private Cursor cursor = null;
	private final Uri uri = UserTableMetaData.CONTENT_URI;
	private int idCol = -1;
	private int userCol = -1;
	private int firstCol = -1;
	private int lastCol = -1;
	
	/*
	 * Show users, if any, on screen using data created in content provider
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contentprovidersamples);
		
		lv = getListView();
		
		cursor = managedQuery(uri, null, null, null, null);
		
		String[] cols = new String[]{UserTableMetaData.USER};
		idCol = cursor.getColumnIndex(UserTableMetaData._ID);
		userCol = cursor.getColumnIndex(UserTableMetaData.USER);
		firstCol = cursor.getColumnIndex(UserTableMetaData.FIRST_NAME);
		lastCol = cursor.getColumnIndex(UserTableMetaData.LAST_NAME);
		
		int[] views = new int[] {android.R.id.text1};
		
		SimpleCursorAdapter sca = new SimpleCursorAdapter(this, 
										android.R.layout.simple_list_item_checked, cursor, cols, views);
		
		this.setListAdapter(sca);
		
		lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		
        Log.d(TAG, "onCreate() leave");	
	}

	/*
	 * Called when button pressed to add user
	 * @param view
	 */
	public void doClickAddUserScreen(View view) {

		startActivity(new Intent("com.rcc.opensourcecodesamplesronc.CPUSERADD_SAMPLE"));
	}

	/*
	 * Called when button pressed to delete user.  Finds checked position and 
	 * calls userDAO.removeSingleUserWithId() to delete User and associated Emails
	 * @param view
	 */
	public void doClickDeleteUser(View view) {
		Log.d(TAG, "in doClickDeleteUser()");
		int positionChecked = lv.getCheckedItemPosition();
		
		if (positionChecked != -1){
			Log.d(TAG, "positionChecked = " + positionChecked);
			cursor.moveToPosition(positionChecked);
			UserBean ub = setChosenUserBeanInfo();
			
			UserDAO userDAO = new UserDAO();
			userDAO.removeSingleUserWithId(this, ub.get_id());	
		}else{
			Toast.makeText(this,
					"Please choose a User to delete",
					Toast.LENGTH_LONG).show();
		}
	}
	
	/*
	 * Obtains cursor information for chosen item on screen and creates a new
	 * UserBean based on that information
	 */
	public UserBean setChosenUserBeanInfo(){
		UserBean ub = new UserBean();
		
		long id = cursor.getLong(idCol);
		String user = cursor.getString(userCol);
		String first = cursor.getString(firstCol);
		String last = cursor.getString(lastCol);
		
		ub.set_id(id);
		ub.setUser(user);
		ub.setFirstName(first);
		ub.setLastName(last);
		Log.d(TAG, "id chosen = " + ub.get_id());
		Log.d(TAG, "user chosen = " + ub.getUser());
		Log.d(TAG, "first name chosen = " + ub.getFirstName());		
		Log.d(TAG, "last name chosen = " + ub.getLastName());
		
		return ub;
	}
	
	/*
	 * inflates menu for help screen
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.cpsampleshelpmenu, menu);
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
		case R.id.cpsampleshelp:
			intent = new Intent("com.rcc.opensourcecodesamplesronc.CPSAMPLESHELP");
			startActivity(intent);
			break;
		}
		return false;
	}
}
