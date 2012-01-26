package com.rcc.opensourcecodesamplesronc.android.table;

import java.util.HashMap;

import com.rcc.opensourcecodesamplesronc.android.constant.UserProviderMetaData;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

/*
 * Content provider for email data
 */
public class EmailContentProvider extends DbBaseContentProvider {

	private static final String TAG = EmailContentProvider.class
			.getSimpleName();

	/*
	 * ProjectionMap Setup...
	 * Projection maps are similar to "as" (column alias) construct in a sql
	 * statement where you can rename columns
	 * HashMap<String, String>
	 */
	private static HashMap<String, String> EmailProjectionMap;
	static {
		EmailProjectionMap = new HashMap<String, String>();

		EmailProjectionMap.put(EmailTableMetaData._ID, EmailTableMetaData._ID);
		EmailProjectionMap.put(EmailTableMetaData.USER_ID,
				EmailTableMetaData.USER_ID);
		EmailProjectionMap.put(EmailTableMetaData.EMAIL_ADDRESS,
				EmailTableMetaData.EMAIL_ADDRESS);
	}

	// uri setups
	// provide a mechanism to identify all the incoming url patterns
	private static final UriMatcher sUriMatcher;
	private static final int INCOMING_EMAIL_COLLECTION_URI_INDICATOR = 1;
	private static final int INCOMING_SINGLE_EMAIL_URI_INDICATOR = 2;
	static {
		sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		sUriMatcher.addURI(UserProviderMetaData.AUTHORITY_EMAIL, "email",
				INCOMING_EMAIL_COLLECTION_URI_INDICATOR);
		sUriMatcher.addURI(UserProviderMetaData.AUTHORITY_EMAIL, "email/#",
				INCOMING_SINGLE_EMAIL_URI_INDICATOR);
	}

	private DBHelper myDBHelper;

	/*
	 * Called when content provider first created
	 * (non-Javadoc)
	 * @see android.content.ContentProvider#onCreate()
	 */
	@Override
	public boolean onCreate() {
		Log.d(TAG, "main onCreate() called");
		myDBHelper = new DBHelper(this.getContext());
		Log.d(TAG, "main onCreate() called... after myDBHelper");
		return true;
	}

	/*
	 * Maps from the uri pattern
	 * (non-Javadoc)
	 * @see android.content.ContentProvider#getType(android.net.Uri)
	 */
	@Override
	public String getType(Uri uri) {

		switch (sUriMatcher.match(uri)) {
		case INCOMING_EMAIL_COLLECTION_URI_INDICATOR:
			return EmailTableMetaData.CONTENT_TYPE;
		case INCOMING_SINGLE_EMAIL_URI_INDICATOR:
			return EmailTableMetaData.CONTENT_ITEM_TYPE;
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
	}

	/*
	 * Queries email records from the content provider
	 * (non-Javadoc)
	 * @see android.content.ContentProvider#query(android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String)
	 */
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {

		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

		switch (sUriMatcher.match(uri)) {
		case INCOMING_EMAIL_COLLECTION_URI_INDICATOR:
			qb.setTables(EmailTableMetaData.TABLE_NAME);
			qb.setProjectionMap(EmailProjectionMap);
			break;
		case INCOMING_SINGLE_EMAIL_URI_INDICATOR:
			qb.setTables(EmailTableMetaData.TABLE_NAME);
			qb.setProjectionMap(EmailProjectionMap);
			qb.appendWhere(EmailTableMetaData._ID + "="
					+ uri.getPathSegments().get(1));
			break;
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}

		// if no sort order specified, use default
		String orderBy;
		if (TextUtils.isEmpty(sortOrder)) {
			orderBy = EmailTableMetaData.DEFAULT_SORT_ORDER;
		} else {
			orderBy = sortOrder;
		}

		// get db and run query
		SQLiteDatabase db = myDBHelper.getReadableDatabase();
		Cursor c = qb.query(db, projection, selection, selectionArgs, null,
				null, orderBy);

		// example of getting a count
		// int i = c.getCount();

		// Tell the cursor what uri to watch so it knows when its source data
		// changes
		c.setNotificationUri(getContext().getContentResolver(), uri);
		return c;
	}

	/*
	 * Updates email records in the content provider
	 * (non-Javadoc)
	 * @see android.content.ContentProvider#update(android.net.Uri, android.content.ContentValues, java.lang.String, java.lang.String[])
	 */
	@Override
	public int update(Uri uri, ContentValues values, String whereClause,
			String[] whereArgs) {
		SQLiteDatabase db = myDBHelper.getWritableDatabase();
		int count;
		switch (sUriMatcher.match(uri)) {
		case INCOMING_EMAIL_COLLECTION_URI_INDICATOR:
			count = db.update(EmailTableMetaData.TABLE_NAME, values,
					whereClause, whereArgs);
			break;
		case INCOMING_SINGLE_EMAIL_URI_INDICATOR:
			String rowId = uri.getPathSegments().get(1);
			String where = EmailTableMetaData._ID
					+ "="
					+ rowId
					+ (!TextUtils.isEmpty(whereClause) ? " AND (" + whereClause
							+ ')' : "");
			count = db.update(EmailTableMetaData.TABLE_NAME, values, where,
					whereArgs);
			break;
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}

		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}

	/*
	 * Deletes email records from content provider
	 * (non-Javadoc)
	 * @see android.content.ContentProvider#delete(android.net.Uri, java.lang.String, java.lang.String[])
	 */
	@Override
	public int delete(Uri uri, String whereClause, String[] whereArgs) {
		SQLiteDatabase db = myDBHelper.getWritableDatabase();

		int count;
		switch (sUriMatcher.match(uri)) {
		case INCOMING_EMAIL_COLLECTION_URI_INDICATOR:
			count = db.delete(EmailTableMetaData.TABLE_NAME, whereClause,
					whereArgs);
			break;
		case INCOMING_SINGLE_EMAIL_URI_INDICATOR:
			String rowId = uri.getPathSegments().get(1);
			String where = EmailTableMetaData._ID
					+ "="
					+ rowId
					+ (!TextUtils.isEmpty(whereClause) ? " AND (" + whereClause
							+ ')' : "");
			count = db.delete(EmailTableMetaData.TABLE_NAME, where, whereArgs);
			break;
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}

		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}

	/*
	 * Inserts email records into the content provider
	 * (non-Javadoc)
	 * @see android.content.ContentProvider#insert(android.net.Uri, android.content.ContentValues)
	 */
	@Override
	public Uri insert(Uri uri, ContentValues initialValues) {

		Log.d(TAG, "entered insert");
		if (sUriMatcher.match(uri) != INCOMING_EMAIL_COLLECTION_URI_INDICATOR) {
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
		Log.d(TAG, "uri matches");
		ContentValues values;
		if (initialValues != null) {
			values = new ContentValues(initialValues);
		} else {
			values = new ContentValues();
		}

		if ((values.containsKey(EmailTableMetaData.USER_ID) == false)
				|| (values.getAsLong(EmailTableMetaData.USER_ID).equals(0L))) {
			throw new SQLException(
					"Failed to insert row because User Id is required " + uri);
		} else {
			Log.d(TAG, values.getAsString(EmailTableMetaData.USER_ID));
		}

		if ((values.containsKey(EmailTableMetaData.EMAIL_ADDRESS) == false)
				|| (values.getAsString(EmailTableMetaData.EMAIL_ADDRESS)
						.equals(""))) {
			throw new SQLException(
					"Failed to insert row because Email Address is required "
							+ uri);
		} else {
			Log.d(TAG, values.getAsString(EmailTableMetaData.EMAIL_ADDRESS));
		}

		SQLiteDatabase db = myDBHelper.getWritableDatabase();
		long rowId = db.insert(EmailTableMetaData.TABLE_NAME, null, values);
		if (rowId > 0) {
			Uri insertedEmailUri = ContentUris.withAppendedId(
					EmailTableMetaData.CONTENT_URI, rowId);

			getContext().getContentResolver().notifyChange(insertedEmailUri,
					null);
			Log.d(TAG, "insertedEmailUri = " + insertedEmailUri);
			return insertedEmailUri;
		}

		throw new SQLException("Failed to insert row into " + uri);
	}

}
