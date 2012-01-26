package com.rcc.opensourcecodesamplesronc.android.dao;

import com.rcc.opensourcecodesamplesronc.android.bean.EmailBean;
import com.rcc.opensourcecodesamplesronc.android.table.EmailTableMetaData;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

/*
 * Data access object for email data
 */
public class EmailDAO {
	private final static String TAG = EmailDAO.class.getSimpleName();

	/*
	 * Allows insertion of email data into a content provider
	 * @param Context
	 * @param EmailBean
	 * @return long - rowId if successful insert or -1 if unsuccessful
	 */
	@SuppressWarnings("finally")
	public long addEmail(Context context, EmailBean eb) {

		ContentValues cv = new ContentValues();
		cv.put(EmailTableMetaData.USER_ID, eb.getUser_id());
		cv.put(EmailTableMetaData.EMAIL_ADDRESS, eb.getEmailAddress());

		ContentResolver cr = context.getContentResolver();
		Uri uri = EmailTableMetaData.CONTENT_URI;

		long rowId = -1;

		try {
			Log.d(TAG, "Email insert uri:" + uri);
			Uri insertedUri = cr.insert(uri, cv);
			Log.d(TAG, "Inserted uri:" + insertedUri);
			String strRowId = insertedUri.getLastPathSegment();
			Log.d(TAG, "Inserted Email row id: " + strRowId);
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
}
