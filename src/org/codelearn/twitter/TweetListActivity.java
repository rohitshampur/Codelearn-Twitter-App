package org.codelearn.twitter;

import java.util.ArrayList;
import java.util.List;

import org.codelearn.twitter.Model.Tweet;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class TweetListActivity extends ListActivity {
	
	
	private ArrayAdapter<?> tweetItemArrayAdapter;
	private List<Tweet> tweets = new ArrayList<Tweet>();
	private EditText tweetTitle;
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
		tweetTitle = (EditText) findViewById(R.id.tweetTitle);
		EditText tweetbody = (EditText) findViewById(R.id.tweetBody);
		tweetItemArrayAdapter = new TweetAdapter(this,tweets);
		setListAdapter(tweetItemArrayAdapter);
		
		
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = new Intent(this, TweetDetailActivity.class);
		startActivity(intent);
	}

	
}
