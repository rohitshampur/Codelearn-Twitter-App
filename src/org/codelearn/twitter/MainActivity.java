package org.codelearn.twitter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.codelearn.twitter.Model.Tweet;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	
	private List<Tweet> tweets = new ArrayList<Tweet>();
	public static final String TWEETS_CACHE = "tweets_cache.ser";
	private ArrayAdapter<?> tweetItemArrayAdapter;
	private List<Tweet> tweetsRead = new ArrayList<Tweet>();
	
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
		/*String params = "";
		new AsyncFetchTweets(this).execute(params);*/
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
		String params = "";
		TweetListActivity twl = new TweetListActivity();
		Activity act = MainActivity.this ;
		new AsyncFetchTweets(act,twl).execute(params);
				if(saveduname!=null & savedpaswrd!=null){
			Intent intent = new Intent(getApplicationContext(), TweetListActivity.class);
			startActivity(intent);
			finish();
		}
	}

	class AsyncFetchTweets extends AsyncTask<String, String, List<Tweet>>{
		Activity activity ;
		TweetListActivity twl ;
		List<Tweet> tweets = new ArrayList<Tweet>();
		public AsyncFetchTweets(Activity activity, TweetListActivity twl) {
			this.activity = activity;
			this.twl = twl;
		}
		@Override
		protected List<Tweet> doInBackground(String... params) {
			try {
				System.out.println("Before thread Sleep");
				publishProgress("Thread Sleeping");
				Thread.sleep(5000);
				System.out.println("Inside doInBackground()");
				for (int i=0 ; i <= 20; i++) {
					Tweet tweet = new Tweet();
					tweet.setId(i+"");
					tweet.setTitle("@stormtheh4ck3r Tweet # "+i);
					tweet.setbody("helllo Tweet # "+i);
					tweets.add(tweet);
					
				}
				System.out.println(tweets);
				
					
				try {
					FileOutputStream fos = openFileOutput(TWEETS_CACHE, MODE_PRIVATE);
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					Thread.sleep(5000);
					oos.writeObject(tweets);
					Log.d("codelearn", "Successfully wrote tweets to the file.");
					oos.close();
					fos.close();
					return tweets;
				} catch (Exception e) {
					Log.e("codelearn", "Error writing to file");
					e.printStackTrace();
				}
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}@Override
		protected void onProgressUpdate(String... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
		}
		/*@Override
		protected void onPostExecute(List<Tweet> tweet) {
			Log.d("codelearn", "inside renderTweet");
			FileInputStream fis = null;
			ObjectInputStream ois = null;
			try{
				fis = openFileInput(TWEETS_CACHE);
				ois = new ObjectInputStream(fis);
				tweetsRead = (List<Tweet>)ois.readObject();
				Log.d("codelearn", "Successfully read tweets from file.");
			}catch(Exception e){
				Log.e("codelearn", "Error reading to file");
			}finally{
				try{
				ois.close();
				fis.close();
				}catch(Exception e){
					
				}
			}
			
			twl.renderTweet(tweet);
			
		}*/
		
	}
		
}
