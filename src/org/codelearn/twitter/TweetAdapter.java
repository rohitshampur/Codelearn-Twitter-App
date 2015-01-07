package org.codelearn.twitter;

import java.util.List;

import org.codelearn.twitter.Model.Tweet;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

@SuppressWarnings("rawtypes")
public class TweetAdapter extends ArrayAdapter{
	
	private Activity activity; 
	private List<Tweet> tweet;
	@SuppressWarnings("unchecked")
	public TweetAdapter(Activity activity , List<Tweet> tweets) {
		super(activity,R.layout.row_tweet,tweets);
		this.activity = activity;
		this.tweet = tweets;
		
	}
	@SuppressLint("ViewHolder")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView =convertView;
		TweetView title = null;
		if(rowView==null){
			// Get a new instance of the row layout view
			LayoutInflater inflator = activity.getLayoutInflater();
			rowView =inflator.inflate(R.layout.row_tweet, null);
			// Hold the view objects in an object,
            // so they don't need to be re-fetched
			title = new TweetView(); 
			title.title = (TextView) rowView.findViewById(R.id.tweetTitle);
			title.body = (TextView) rowView.findViewById(R.id.tweetBody);
			// Cache the view objects in the tag,
            // so they can be re-accessed later
			rowView.setTag(title);
			}else{
				title = (TweetView) rowView.getTag();
			}
		
		// Transfer the stock data from the data object
        // to the view objects
		Tweet currTweet = tweet.get(position);
		title.title.setText(currTweet.getTitle().toString());
		title.body.setText(currTweet.getBody().toString());
		return rowView;
		
	}	
	protected static class TweetView {

        protected TextView title;
        protected TextView body;
    }

	
	

}
