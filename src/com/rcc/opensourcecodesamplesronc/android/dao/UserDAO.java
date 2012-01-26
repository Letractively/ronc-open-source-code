package com.rcc.opensourcecodesamplesronc.android.dao;

import com.rcc.opensourcecodesamplesronc.android.activity.R;
import com.rcc.opensourcecodesamplesronc.android.bean.UserBean;
import com.rcc.opensourcecodesamplesronc.android.constant.SQLConstants;
import com.rcc.opensourcecodesamplesronc.android.table.EmailTableMetaData;
import com.rcc.opensourcecodesamplesronc.android.table.UserTableMetaData;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

/*
 * Data access object for user data
 */
public class UserDAO {

	private static final String TAG = UserDAO.class.getSimpleName();

	/*
	 * Allows insertion of a user into a content provider
	 * @param Context
	 * @param UserBean
	 * @return long - rowId if successful insert or -1 if unsuccessful
	 */
	@SuppressWarnings("finally")
	public long addUser(Context context, UserBean ub) {

		ContentValues cv = new ContentValues();
		cv.put(UserTableMetaData.USER, ub.getUser());
		cv.put(UserTableMetaData.FIRST_NAME, ub.getFirstName());
		cv.put(UserTableMetaData.LAST_NAME, ub.getLastName());

		ContentResolver cr = context.getContentResolver();
		Uri uri = UserTableMetaData.CONTENT_URI;

		long rowId = -1;

		try {
			Log.d(TAG, "User insert uri:" + uri);
			Uri insertedUri = cr.insert(uri, cv);
			Log.d(TAG, "Inserted uri:" + insertedUri);
			String strRowId = insertedUri.getLastPathSegment();
			Log.d(TAG, "Inserted User row id: " + strRowId);
			rowId = new Long(strRowId);
		} catch (Exception e) {
			// Writing exception to log and handling as a Toast to the user in
			// calling method
			e.printStackTrace();
			Log.d(TAG, e.getMessage());
		} finally {
			return rowId;
		}

	}

	/*
	 * Removes a User with a given id by first deleting all Emails associated
	 * with the User, then deleting the User
	 * @param Context
	 * @param long - user id
	 * @return int - number of rows removed
	 */
	public int removeSingleUserWithId(Context context, long id) {
		ContentResolver cr = context.getContentResolver();
		Uri uri = EmailTableMetaData.CONTENT_URI;
		try {
			Log.d(TAG, "uri = " + uri);
			int emailsDelete = cr.delete(uri,
					SQLConstants.WHERE_ALL_EMAILS_FOR_USER, new String[] { id
							+ "" });
			Log.d(TAG, "where = " + SQLConstants.WHERE_ALL_EMAILS_FOR_USER);
			if (emailsDelete != 0) {
				Log.d(TAG, "All emails deleted for user id=" + id);
			} else {
				Log.d(TAG, "There were no emails to delete for user id=" + id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.d(TAG, "There was a problem deleting the emails for user id="
					+ id);
		}

		// delete User now
		uri = UserTableMetaData.CONTENT_URI;
		Log.d(TAG, "uri base = " + uri.toString());
		uri = Uri.parse(uri.toString() + "/" + id);

		int removedRows = 0;
		try {
			removedRows = cr.delete(uri, null, null);

			if (removedRows != 0) {
				Toast.makeText(context, context.getResources().getText(R.string.userdeletesuccessful),
						Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(context, context.getResources().getText(R.string.userdeletenotsuccessful),
						Toast.LENGTH_LONG).show();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Toast.makeText(context, context.getResources().getText(R.string.userdeletenotsuccessful),
					Toast.LENGTH_LONG).show();
		}

		return removedRows;
	}

}
