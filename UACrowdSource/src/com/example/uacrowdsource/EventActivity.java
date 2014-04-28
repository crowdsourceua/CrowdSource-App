package com.example.uacrowdsource;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EventActivity extends ActionBarActivity {

	Event event;
	ArrayList<String> qIds;
	
	int triviaRight;
	int triviaTotal;
	int polls;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event);
		
		Intent i = getIntent();
		event = (Event)i.getSerializableExtra("eventObj");
		
		triviaRight = i.getIntExtra("triviaright", 0);
		triviaTotal = i.getIntExtra("triviatotal", 0);
		polls = i.getIntExtra("polls", 0);
		
		setTitle(event.getTitle());
		
		TextView stats = (TextView) findViewById(R.id.textView3);
		stats.setText(stats.getText() + " " + event.getTitle());
		
		TextView triviatext = (TextView) findViewById(R.id.triviatext);
		triviatext.setText(triviatext.getText() + " " + String.valueOf(triviaRight) + "/" + String.valueOf(triviaTotal) + " correct");
		
		TextView polltext = (TextView) findViewById(R.id.polltext);
		polltext.setText(polltext.getText() + " " + String.valueOf(polls) + " answered");
		
		ArrayList<String> serializableExtra = (ArrayList<String>)i.getSerializableExtra("qIds");
		qIds = serializableExtra;
	}
	
	public void getContent(View view) throws Exception
	{
		lookForQuestion(event);
	}

	public void lookForQuestion(Event event) throws Exception
	{
    	Question q = parseJSONForQuestion();
    	
    	if (q != null)
    	{
        	Intent intent = new Intent(this, InteractionActivity.class);
    		intent.putExtra("question", q);
    		intent.putExtra("qIds", qIds);
    		intent.putExtra("eventObj", event);
    		intent.putExtra("triviaright", triviaRight);
    		intent.putExtra("triviatotal", triviaTotal);
    		intent.putExtra("polls", polls);
    		startActivity(intent);
    		finish();
    	}
    	else
    	{
        	Toast.makeText(this, "No new event content yet...:(", Toast.LENGTH_SHORT).show();
    	}
	}
	
	public Question parseJSONForQuestion() throws Exception
	{
		String base = "http://crowdsourceua.herokuapp.com/getQuestion.php?";
    	String e = "eventID=" + event.getId();
    	String url = base + e;
		String json = new JSONReader().execute(url).get();
		JSONArray jsonarr = new JSONArray(json);
		
		for (int i = 0; i < jsonarr.length(); i++)
		{
			JSONObject jsonobj = jsonarr.getJSONObject(i);
			String qId = jsonobj.getString("questionID");
			
			if (qIds.contains(qId))
			{
				continue;
			}
			
			String eId = jsonobj.getString("eventID");
			String question = jsonobj.getString("question");
			String type = jsonobj.getString("questionType");
			String a = jsonobj.getString("choiceA");
			String b = jsonobj.getString("choiceB");
			String c = jsonobj.getString("choiceC");
			String d = jsonobj.getString("choiceD");
			String answer = jsonobj.getString("correctAnswer");
			
			Question q = new Question(qId, eId, type, question, a, b, c, d, answer);
			
			return q;
		}
		
		return null;	
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.event, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_event,
					container, false);
			return rootView;
		}
	}
}
