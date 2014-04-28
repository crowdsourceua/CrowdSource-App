package com.example.uacrowdsource;

import java.util.concurrent.ExecutionException;

import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity
{
	private EditText username=null;
	private EditText password=null;
	private Button login = null;
	private Button register = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		username = (EditText)findViewById(R.id.editText1);
		password = (EditText)findViewById(R.id.editText2);
		login = (Button)findViewById(R.id.button1);
		register = (Button)findViewById(R.id.button2);
		
		
	}

	public void login(View view) throws InterruptedException, ExecutionException, JSONException
	{
	   if(isValidUser(username.getText().toString(), password.getText().toString()))
	   {
		   Toast.makeText(getApplicationContext(), "Login Successful!", Toast.LENGTH_SHORT).show();
		   Intent intent = new Intent(this, EventListActivity.class);
		   startActivity(intent);
	   }	
       else
       {
    	   Toast.makeText(getApplicationContext(), "Email/Password Not Found...", Toast.LENGTH_SHORT).show();
    	   ((EditText) findViewById(R.id.editText2)).setText("");
    	   password.requestFocus();
       }
	}
	
	public boolean isValidUser(String username, String password) throws InterruptedException, ExecutionException, JSONException
	{
		String base = "http://crowdsourceua.herokuapp.com/verifyUser.php?";
		String email = "email=" + username;
		String pass = "&pass=" + password;
		String url = base + email + pass;
		String json = new JSONReader().execute(url).get();
		
		if (json.contains("true") || json.contains("1"))
		{
			return true;
		}
		
		return false;
	}
	
	public void register(View view)
	{
		Toast.makeText(getApplicationContext(), "Redirecting to registration!", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(this, RegisterActivity.class);
		startActivity(intent);
	}
	   
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
       // Inflate the menu; this adds items to the action bar if it is present.
       getMenuInflater().inflate(R.menu.main, menu);
       return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
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
    public static class PlaceholderFragment extends Fragment
    {
        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState)
        {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
}
