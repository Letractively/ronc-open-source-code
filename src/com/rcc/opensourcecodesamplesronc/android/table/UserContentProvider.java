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

public class UserContentProvider extends DbBaseContentProvider {

	private static final String TAG = UserContentProvider.class.getSimpleName();

	/*
	 * ProjectionMap Setup... Projection maps are similar to "as" (column alias)
	 * construct in a sql statement where you can rename columns HashMap<String,
	 * String>
	 */
	private static HashMap<String, String> UserProjectionMap;
	static {
		UserProjectionMap = new HashMap<String, String>();

		UserProjectionMap.put(UserTableMetaData._ID, UserTableMetaData._ID);
		UserProjectionMap.put(UserTableMetaData.USER, UserTableMetaData.USER);
		UserProjectionMap.put(UserTableMetaData.FIRST_NAME,
				UserTableMetaData.FIRST_NAME);
		UserProjectionMap.put(UserTableMetaData.LAST_NAME,
				UserTableMetaData.LAST_NAME);
	}

	// uri setups
	// provide a mechanism to identify all the incoming url patterns
	private static final UriMatcher sUriMatcher;
	private static final int INCOMING_USER_COLLECTION_URI_INDICATOR = 1;
	private static final int INCOMING_SINGLE_USER_URI_INDICATOR = 2;
	static {
		sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		sUriMatcher.addURI(UserProviderMetaData.AUTHORITY_USER, "user",
				INCOMING_USER_COLLECTION_URI_INDICATOR);
		sUriMatcher.addURI(UserProviderMetaData.AUTHORITY_USER, "user/#",
				INCOMING_SINGLE_USER_URI_INDICATOR);
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
		case INCOMING_USER_COLLECTION_URI_INDICATOR:
			return UserTableMetaData.CONTENT_TYPE;
		case INCOMING_SINGLE_USER_URI_INDICATOR:
			return UserTableMetaData.CONTENT_ITEM_TYPE;
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
	}

	/*
	 * Queries user records from content provider
	 * (non-Javadoc)
	 * @see android.content.ContentProvider#query(android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String)
	 */
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {

		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

		switch (sUriMatcher.match(uri)) {
		case INCOMING_USER_COLLECTION_URI_INDICATOR:
			qb.setTables(UserTableMetaData.TABLE_NAME);
			qb.setProjectionMap(UserProjectionMap);
			break;
		case INCOMING_SINGLE_USER_URI_INDICATOR:
			qb.setTables(UserTableMetaData.TABLE_NAME);
			qb.setProjectionMap(UserProjectionMap);
			qb.appendWhere(UserTableMetaData._ID + "="
					+ uri.getPathSegments().get(1));
			break;
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}

		// if no sort order specified, use default
		String orderBy;
		if (TextUtils.isEmpty(sortOrder)) {
			orderBy = UserTableMetaData.DEFAULT_SORT_ORDER;
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
	 * Updates user records in content provider
	 * (non-Javadoc)
	 * @see android.content.ContentProvider#update(android.net.Uri, android.content.ContentValues, java.lang.String, java.lang.String[])
	 */
	@Override
	public int update(Uri uri, ContentValues values, String whereClause,
			String[] whereArgs) {
		SQLiteDatabase db = myDBHelper.getWritableDatabase();
		int count;
		switch (sUriMatcher.match(uri)) {
		case INCOMING_USER_COLLECTION_URI_INDICATOR:
			count = db.update(UserTableMetaData.TABLE_NAME, values,
					whereClause, whereArgs);
			break;
		case INCOMING_SINGLE_USER_URI_INDICATOR:
			String rowId = uri.getPathSegments().get(1);
			String where = UserTableMetaData._ID
					+ "="
					+ rowId
					+ (!TextUtils.isEmpty(whereClause) ? " AND (" + whereClause
							+ ')' : "");
			count = db.update(UserTableMetaData.TABLE_NAME, values, where,
					whereArgs);
			break;
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}

		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}

	/*
	 * Deletes user records from content provider
	 * (non-Javadoc)
	 * @see android.content.ContentProvider#delete(android.net.Uri, java.lang.String, java.lang.String[])
	 */
	@Override
	public int delete(Uri uri, String whereClause, String[] whereArgs) {
		SQLiteDatabase db = myDBHelper.getWritableDatabase();

		int count;
		switch (sUriMatcher.match(uri)) {
		case INCOMING_USER_COLLECTION_URI_INDICATOR:
			count = db.delete(UserTableMetaData.TABLE_NAME, whereClause,
					whereArgs);
			break;
		case INCOMING_SINGLE_USER_URI_INDICATOR:
			String rowId = uri.getPathSegments().get(1);
			String where = UserTableMetaData._ID
					+ "="
					+ rowId
					+ (!TextUtils.isEmpty(whereClause) ? " AND (" + whereClause
							+ ')' : "");
			count = db.delete(UserTableMetaData.TABLE_NAME, where, whereArgs);
			break;
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}

		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}

	/*
	 * Inserts user records into content provider
	 * (non-Javadoc)
	 * @see android.content.ContentProvider#insert(android.net.Uri, android.content.ContentValues)
	 */
	@Override
	public Uri insert(Uri uri, ContentValues initialValues) {

		Log.d(TAG, "entered insert");
		if (sUriMatcher.match(uri) != INCOMING_USER_COLLECTION_URI_INDICATOR) {
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
		Log.d(TAG, "uri matches");
		ContentValues values;
		if (initialValues != null) {
			values = new ContentValues(initialValues);
		} else {
			values = new ContentValues();
		}

		if ((values.containsKey(UserTableMetaData.USER) == false)
				|| (values.getAsString(UserTableMetaData.USER).equals(""))) {
			throw new SQLException(
					"Failed to insert row because User Id is required " + uri);
		} else {
			Log.d(TAG, values.getAsString(UserTableMetaData.USER));
		}

		if ((values.containsKey(UserTableMetaData.FIRST_NAME) == false)
				|| (values.getAsString(UserTableMetaData.FIRST_NAME).equals(""))) {
			throw new SQLException(
					"Failed to insert row because First Name is required "
							+ uri);
		} else {
			Log.d(TAG, values.getAsString(UserTableMetaData.FIRST_NAME));
		}

		if ((values.containsKey(UserTableMetaData.LAST_NAME) == false)
				|| (values.getAsString(UserTableMetaData.LAST_NAME).equals(""))) {
			throw new SQLException(
					"Failed to insert row because Last Name is required " + uri);
		} else {
			Log.d(TAG, values.getAsString(UserTableMetaData.LAST_NAME));
		}

		SQLiteDatabase db = myDBHelper.getWritableDatabase();
		long rowId = db.insert(UserTableMetaData.TABLE_NAME, null, values);
		if (rowId > 0) {
			Uri insertedUserUri = ContentUris.withAppendedId(
					UserTableMetaData.CONTENT_URI, rowId);

			getContext().getContentResolver().notifyChange(insertedUserUri,
					null);
			Log.d(TAG, "insertedUserUri = " + insertedUserUri);
			return insertedUserUri;
		}

		throw new SQLException("Failed to insert row into " + uri);
	}

}