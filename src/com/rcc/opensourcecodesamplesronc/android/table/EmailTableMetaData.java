package com.rcc.opensourcecodesamplesronc.android.table;

import com.rcc.opensourcecodesamplesronc.android.constant.UserProviderMetaData;

import android.net.Uri;
import android.provider.BaseColumns;

/*
 * Meta data specific to the email table
 */
public class EmailTableMetaData implements BaseColumns {

	public static final String TABLE_NAME = "email_table";

	// uri and MIME type defs
	public static final Uri CONTENT_URI = Uri.parse("content://"
			+ UserProviderMetaData.AUTHORITY_EMAIL + "/email");
	public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.android.email";
	public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.android.email";

	public static final String DEFAULT_SORT_ORDER = "user_id ASC";

	// table cols
	// String type
	public static final String USER_ID = "user_id";
	// String type
	public static final String EMAIL_ADDRESS = "email_address";

}
