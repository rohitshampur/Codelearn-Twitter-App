package org.codelearn.twitter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.codelearn.twitter.Model.Tweet;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TweetListActivity extends ListActivity {
	
	
	private ArrayAdapter<?> tweetItemArrayAdapter;
	private List<Tweet> tweets = new ArrayList<Tweet>();
	private List<Tweet> tweetsRead = new ArrayList<Tweet>(); 
	public static final String TWEETS_CACHE = "tweets_cache.ser";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tweet_list);
		for (int i=0 ; i <= 20; i++) {
			Tweet tweet = new Tweet();
			tweet.setId(i+"");
			tweet.setTitle("@stormtheh4ck3r Tweet # "+i);
			tweet.setbody("helllo Tweet # "+i);
			tweets.add(tweet);
			
		}
		System.out.println(tweets);
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try{
			fis = openFileInput(TWEETS_CACHE);
			ois = new ObjectInputStream(fis);
			tweetsRead = (List<Tweet>)ois.readObject();
			Log.d("codelearn", "Successfully read tweets to the file.");
		}catch(Exception e){
			Log.e("codelearn", "Error reading to file");
		}finally{
			try{
			ois.close();
			fis.close();
			}catch(Exception e){
				
			}
		}
		
		try {
			FileOutputStream fos = openFileOutput(TWEETS_CACHE, MODE_PRIVATE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(tweets);
			Log.d("codelearn", "Successfully wrote tweets to the file.");
			oos.close();
			fos.close();
		} catch (Exception e) {
			Log.e("codelearn", "Error writing to file");
			e.printStackTrace();
		} 
		tweetItemArrayAdapter = new TweetAdapter(this,tweetsRead);
		setListAdapter(tweetItemArrayAdapter);
		
		
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = new Intent(this, TweetDetailActivity.class);
		startActivity(intent);
	}

	
}
