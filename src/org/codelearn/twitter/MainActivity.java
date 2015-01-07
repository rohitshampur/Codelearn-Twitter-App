package org.codelearn.twitter;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	private Button mLoginbtn ;
	
	public void Login(View v){
		EditText username = (EditText) findViewById(R.id.fld_username);
		String usernameValue = username.getText().toString();
		System.out.println("user "+usernameValue);
		Log.d("codelearn", "Username caught   "+usernameValue);
		EditText password = (EditText) findViewById(R.id.fld_paswrd);
		String passwordValue = password.getText().toString();
		System.out.println("pass "+passwordValue);
		Log.d("codelearn", "Username caught   "+passwordValue);
		SharedPreferences prefs = getSharedPreferences("twitter", MODE_PRIVATE);
		Editor editor = prefs.edit();
		editor.putString("user", usernameValue);
		editor.putString("pass", passwordValue);
		editor.commit();
		Intent intent = new Intent(getApplicationContext(), TweetListActivity.class);
		startActivity(intent);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		SharedPreferences pref = getSharedPreferences("twitter", MODE_PRIVATE);
		String saveduname = pref.getString("user",null);
		String savedpaswrd = pref.getString("pass", null);
		if(saveduname!=null & savedpaswrd!=null){
			Intent intent = new Intent(getApplicationContext(), TweetListActivity.class);
			startActivity(intent);
			finish();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
