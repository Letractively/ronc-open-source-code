package com.rcc.opensourcecodesamplesronc.android.table;

import com.rcc.opensourcecodesamplesronc.android.constant.UserProviderMetaData;

import android.net.Uri;
import android.provider.BaseColumns;

/*
 * Meta data specific to the user table
 */
public class UserTableMetaData implements BaseColumns {

	public static final String TABLE_NAME = "user_table";

	// uri and MIME type defs
	public static final Uri CONTENT_URI = Uri.parse("content://"
			+ UserProviderMetaData.AUTHORITY_USER + "/user");
	public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.android.user";
	public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.android.user";

	public static final String DEFAULT_SORT_ORDER = "user ASC";

	// table cols
	// String type
	public static final String USER = "user";
	// String type
	public static final String FIRST_NAME = "first_name";
	// String type
	public static final String LAST_NAME = "last_name";

}
