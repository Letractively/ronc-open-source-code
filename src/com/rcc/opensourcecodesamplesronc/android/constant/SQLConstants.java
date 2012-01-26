package com.rcc.opensourcecodesamplesronc.android.constant;

import com.rcc.opensourcecodesamplesronc.android.table.EmailTableMetaData;
import com.rcc.opensourcecodesamplesronc.android.table.UserTableMetaData;

/*
 * Constants used specifically for sql statements
 */
public class SQLConstants {

	public static final String CREATE_USER_TABLE = String
			.format("CREATE TABLE %s (%s integer primary key autoincrement, %s text not null, %s text not null, %s text not null);",
					UserTableMetaData.TABLE_NAME, 
					UserTableMetaData._ID,
					UserTableMetaData.USER, 
					UserTableMetaData.FIRST_NAME,
					UserTableMetaData.LAST_NAME);

	public static final String CREATE_EMAIL_TABLE = String
			.format("CREATE TABLE %s (%s integer primary key autoincrement, %s integer not null, %s text not null,"
					+ " FOREIGN KEY (%s) REFERENCES %s (%s));",
					EmailTableMetaData.TABLE_NAME, 
					EmailTableMetaData._ID,
					EmailTableMetaData.USER_ID,  //id of the row in User table
					EmailTableMetaData.EMAIL_ADDRESS,
					EmailTableMetaData.USER_ID, 
					UserTableMetaData.TABLE_NAME,
					UserTableMetaData._ID);
	
	public static final String WHERE_ALL_EMAILS_FOR_USER = String.format(
			"%s = ? ", 
			EmailTableMetaData.USER_ID);
}
