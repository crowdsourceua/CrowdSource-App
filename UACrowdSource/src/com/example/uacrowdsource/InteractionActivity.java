package com.example.uacrowdsource;

import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class InteractionActivity extends ActionBarActivity {
	
	Question q;
	Event event;
	ArrayList<String> qIds;
	
	int triviaRight;
	int triviaTotal;
	int polls;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_interaction);

		event = (Event) getIntent().getSerializableExtra("eventObj");
		q = (Question) getIntent().getSerializableExtra("question");
		qIds = (ArrayList<String>) getIntent().getSerializableExtra("qIds");
		
		triviaRight = getIntent().getIntExtra("triviaright", 0);
		triviaTotal = getIntent().getIntExtra("triviatotal", 0);
		polls = getIntent().getIntExtra("polls", 0);
		
		TextView qView = (TextView) findViewById(R.id.question);
		TextView aView = (TextView) findViewById(R.id.choiceA);
		TextView bView = (TextView) findViewById(R.id.choiceB);
		TextView cView = (TextView) findViewById(R.id.choiceC);
		TextView dView = (TextView) findViewById(R.id.choiceD);
		
		String type = "";
		if (q.getType().equals("1")) { type = "Poll: "; }
		else { type = "Trivia: "; }
		
		qView.setText(type + q.getQuestion());
		aView.setText(q.getChoiceA());
		bView.setText(q.getChoiceB());
		cView.setText(q.getChoiceC());
		dView.setText(q.getChoiceD());
	}
	
	public void selectChoiceA(View view) throws Exception
	{
		Button choiceA = (Button) findViewById(R.id.choiceA);
		choiceA.setBackgroundColor(Color.WHITE);
		choiceA.setTextColor(Color.BLACK);
		
		gradeAnswer(0, choiceA);
	}
	
	public void selectChoiceB(View view) throws Exception
	{
		Button choiceB = (Button) findViewById(R.id.choiceB);
		choiceB.setBackgroundColor(Color.WHITE);
		choiceB.setTextColor(Color.BLACK);
		
		gradeAnswer(1, choiceB);
	}
	
	public void selectChoiceC(View view) throws Exception
	{
		Button choiceC = (Button) findViewById(R.id.choiceC);
		choiceC.setBackgroundColor(Color.WHITE);
		choiceC.setTextColor(Color.BLACK);
		
		gradeAnswer(2, choiceC);
	}
	
	public void selectChoiceD(View view) throws Exception
	{
		Button choiceD = (Button) findViewById(R.id.choiceD);
		choiceD.setBackgroundColor(Color.WHITE);
		choiceD.setTextColor(Color.BLACK);
		
		gradeAnswer(3, choiceD);
	}
	
	public void gradeAnswer(int choice, Button btn) throws Exception
	{
		qIds.add(q.getQId());
		if (q.getType().equals("1"))	// poll
		{
			polls++;
			
			Thread.sleep(500);
			Toast.makeText(getApplicationContext(), "Thanks for Your Vote!", Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(this, EventActivity.class);
			intent.putExtra("qIds", qIds);
			intent.putExtra("eventObj", event);
			intent.putExtra("triviaright", triviaRight);
    		intent.putExtra("triviatotal", triviaTotal);
    		intent.putExtra("polls", polls);
			startActivity(intent);
			finish();
		}
		else	// trivia
		{
			if (q.getCorrectAnswer().equals(null) || q.getCorrectAnswer().equals(""))	// accident poll
			{
				polls++;
				
				Thread.sleep(500);
				Toast.makeText(getApplicationContext(), "Thanks for Your Vote!", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(this, EventActivity.class);
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
				int answer = Integer.parseInt(q.getCorrectAnswer());
				triviaTotal++;
				
				if (choice == answer)
				{
					triviaRight++;
					
					btn.setBackgroundColor(Color.rgb(115, 255, 115));
					Thread.sleep(500);
					
					Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_LONG).show();
					Intent intent = new Intent(this, EventActivity.class);
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
					btn.setBackgroundColor(Color.rgb(247, 169, 169));
					Thread.sleep(500);
					
					Toast.makeText(getApplicationContext(), "Sorry! You missed that one.", Toast.LENGTH_LONG).show();
					Intent intent = new Intent(this, EventActivity.class);
					intent.putExtra("qIds", qIds);
					intent.putExtra("eventObj", event);
					intent.putExtra("triviaright", triviaRight);
		    		intent.putExtra("triviatotal", triviaTotal);
		    		intent.putExtra("polls", polls);
					startActivity(intent);
					finish();
				}
			}
		}
	}

	@Override
	public void onBackPressed() {
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.interaction, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_interaction,
					container, false);
			return rootView;
		}
	}

}
