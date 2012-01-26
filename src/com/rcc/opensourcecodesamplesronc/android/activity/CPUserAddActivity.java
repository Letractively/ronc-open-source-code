package com.rcc.opensourcecodesamplesronc.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.rcc.opensourcecodesamplesronc.android.bean.UserBean;
import com.rcc.opensourcecodesamplesronc.android.dao.UserDAO;

/*
 * Allows a user to be added to the user content provider
 */
public class CPUserAddActivity extends Activity {

	UserBean ub;
	TextView txt_userName, txt_firstName, txt_lastName;

	/*
	 * Allows user to be added in user content provider
	 * Layout useradd.xml
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.useradd);

		txt_userName = (TextView) findViewById(R.id.txt_username);
		txt_firstName = (TextView) findViewById(R.id.txt_firstname);
		txt_lastName = (TextView) findViewById(R.id.txt_lastname);

	}

	/*
	 * Called when user presses button to add a user
	 * Method starts activity to allow email addresses to be added passing
	 * a UserBean created from user information
	 */
	public void doClickAddUser(View view) {
		ub = new UserBean();
		ub.setUser(txt_userName.getText().toString());
		ub.setFirstName(txt_firstName.getText().toString());
		ub.setLastName(txt_lastName.getText().toString());

		UserDAO uDAO = new UserDAO();

		long id_value = uDAO.addUser(this, ub);

		if (id_value != -1) {
			ub.set_id(id_value);
			Intent intent = new Intent("com.rcc.opensourcecodesamplesronc.CPEMAILADD_SAMPLE");
			intent.putExtra("userBean", ub);
			startActivity(intent);
		} else {
			Toast.makeText(
					this,
					"Problem adding user.  User, First and Last names are required.  Please try again.",
					Toast.LENGTH_LONG).show();
		}

	}
}
