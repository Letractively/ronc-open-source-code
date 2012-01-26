package com.rcc.opensourcecodesamplesronc.android.table;

import com.rcc.opensourcecodesamplesronc.android.constant.SQLConstants;
import com.rcc.opensourcecodesamplesronc.android.constant.UserProviderMetaData;

import android.content.ContentProvider;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/*
 * Base content provider containing a helper class for creating and updating 
 * sqlite database.  The content providers extend this class
 */
public abstract class DbBaseContentProvider extends ContentProvider {

	static class DBHelper extends SQLiteOpenHelper {

		private static final String TAG = DBHelper.class.getSimpleName();

		DBHelper(Context context) {
			super(context, UserProviderMetaData.DB_NAME, null,
					UserProviderMetaData.DB_VERSION);
			Log.d(TAG, "in DBHelper(context) after super() called");
		}

		/*
		 * Create tables in sqlite database
		 * (non-Javadoc)
		 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
		 */
		@Override
		public void onCreate(SQLiteDatabase db) {
			Log.d(TAG, "in onCreate(SQLiteDatabase)");
			db.execSQL(SQLConstants.CREATE_USER_TABLE);
			Log.d(TAG, "Table Created: SQLConstants.CREATE_USER_TABLE");

			db.execSQL(SQLConstants.CREATE_EMAIL_TABLE);
			Log.d(TAG, "Table Created: SQLConstants.CREATE_EMAIL_TABLE");
		}

		/*
		 * Upgrades database based on the version
		 * (non-Javadoc)
		 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
		 */
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// This will wipe out all user data. Avoid doing this if at all
			// possible.
			// Note: Sqlite does not support adding foreign and primary keys. In
			// such a case, you might
			// want to rename the existing table, create a new table with the
			// original name
			// and copy the data from the renamed table to the new table

			// Avoid...
			// db.execSQL("drop table if exists " +
			// UserTableMetaData.TABLE_NAME);
			// db.execSQL("drop table if exists " +
			// EmailTableMetaData.TABLE_NAME);
			// Log.d(TAG, "in onUpgrade()...going back to onCreate()");
			// this.onCreate(db);

			// Instead use ALTER with the statement depending upon what you are
			// trying to do

			// Below is the basic concept of dropping and renaming the user_table to add a
			// foreign key in table creation:
			// db.execSQL("alter table user_table rename to user_table_copy");
			// after copying the SQLConstants.CREATE_USER_TABLE as
			// SQLConstants.CREATE_USER_TABLE1 to include a foreign key,
			// db.execSQL(SQLConstants.CREATE_USER_TABLE1);
			// db.execSQL(select user_id, user_name into user_table from user_table_copy);
			// db.execSQL("drop table if exists " + user);
			
			//Be careful not to over-write original sql statement as there will potentially 
			//be different existing versions
		}
	}

}
