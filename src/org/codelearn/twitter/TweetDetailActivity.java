package org.codelearn.twitter;

import org.codelearn.twitter.Model.Tweet;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class TweetDetailActivity extends Activity {
	
	private TextView titleView ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tweet_detail);
		Tweet tw = (Tweet) getIntent().getSerializableExtra("currentTweet");
		Log.d("codelearn", "inside tweetdetailactivity class"+tw);
		titleView = (TextView) findViewById(R.id.tweetDetailTitle);
		titleView.setText(tw.getTitle().toString());
		
		
	}

	
}
