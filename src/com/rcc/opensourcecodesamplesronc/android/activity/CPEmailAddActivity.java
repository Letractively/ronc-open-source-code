package com.rcc.opensourcecodesamplesronc.android.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rcc.opensourcecodesamplesronc.android.bean.EmailBean;
import com.rcc.opensourcecodesamplesronc.android.bean.UserBean;
import com.rcc.opensourcecodesamplesronc.android.dao.EmailDAO;

/*
 * Creates email addresses in the email content provider for a particular user
 */
public class CPEmailAddActivity extends Activity {

	private final String TAG = CPEmailAddActivity.class.getSimpleName();

	UserBean ub;
	EditText et_emailAdd;
	Button b_saveEmail;

	/*
	 * Allows email addresses to be saved for a particular user
	 * Called with a UserBean as an extra for Intent
	 * Layout emailadd.xml
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "in onCreate()");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.emailadd);

		Bundle extraBundle = getIntent().getExtras();

		if (extraBundle != null) {
			ub = (UserBean) extraBundle.getSerializable("userBean");
		}

		b_saveEmail = (Button) findViewById(R.id.b_emailadd);
		et_emailAdd = (EditText) findViewById(R.id.et_emailadd);

	}

	/*
	 * Called from Button for saving emails
	 * @param view
	 */
	public void doClickSaveEmails(View view) {

		String emailAddress = et_emailAdd.getText().toString();
		if ((emailAddress == null) || (emailAddress.trim().length() == 0)) {
			Toast.makeText(this, "Email Address is Required", Toast.LENGTH_LONG).show();
		} else {
			EmailDAO eDAO = new EmailDAO();
			EmailBean eb = new EmailBean();
			eb.setUser_id(ub.get_id());
			Log.d(TAG, "User id = " + eb.getUser_id());

			eb.setEmailAddress(et_emailAdd.getText().toString());
			
			long id_value = eDAO.addEmail(this, eb);

			if (id_value != -1) {
				Toast.makeText(this, "Email address successfully added", Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(this, "Problem adding email address....", Toast.LENGTH_LONG).show();
			}
		}
	}

}
