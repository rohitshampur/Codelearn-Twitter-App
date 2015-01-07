package org.codelearn.twitter;

import java.util.List;

import org.codelearn.twitter.R.id;
import org.codelearn.twitter.Model.Tweet;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;

@SuppressWarnings("rawtypes")
public class TweetAdapter extends ArrayAdapter{
	
	private LayoutInflater inflater;
	private EditText title;
	private List<Tweet> tweets;
	@SuppressWarnings("unchecked")
	public TweetAdapter(Activity activity , List<Tweet> tweets) {
		super(activity,R.layout.row_tweet,tweets);
		inflater = activity.getWindow().getLayoutInflater();
	}
	@SuppressLint("ViewHolder")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		int n = position;
		title = (EditText) convertView.findViewById(id.tweetTitle);
		Log.d("title", "title of tweet"+ title);
		title.setText((CharSequence) tweets.get(n));
		return inflater.inflate(R.layout.row_tweet, parent, false);
	}	
	
	

}
