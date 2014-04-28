package com.example.uacrowdsource;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends ActionBarActivity {

	private EditText username=null;
	private EditText password=null;
	private EditText password2=null;
	private Button register;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		username = (EditText)findViewById(R.id.editText1);
		password = (EditText)findViewById(R.id.editText2);
		password2 = (EditText)findViewById(R.id.editText3);
		register = (Button)findViewById(R.id.button1);
	}

	public void register(View view) throws Exception
	{
	   if(!password.getText().toString().equals(password2.getText().toString()))
	   {
		   Toast.makeText(getApplicationContext(), "Passwords Don't Match...", Toast.LENGTH_SHORT).show();
		   ((EditText) findViewById(R.id.editText2)).setText("");
    	   ((EditText) findViewById(R.id.editText3)).setText("");
    	   password.requestFocus();
	   }
	   else if(!emailIsFree(username.getText().toString()))
	   {
		   Toast.makeText(getApplicationContext(), "Email Address Is Taken!", Toast.LENGTH_SHORT).show();
		   ((EditText) findViewById(R.id.editText1)).setText("");
		   ((EditText) findViewById(R.id.editText2)).setText("");
    	   ((EditText) findViewById(R.id.editText3)).setText("");
    	   username.requestFocus();
	   }
       else
       {
    	   boolean success = addUser(username.getText().toString(), password.getText().toString());
    	   
    	   if (!success)
    	   {
    		   Toast.makeText(getApplicationContext(), "Email Address Is Taken!", Toast.LENGTH_SHORT).show();
    		   ((EditText) findViewById(R.id.editText1)).setText("");
    		   ((EditText) findViewById(R.id.editText2)).setText("");
        	   ((EditText) findViewById(R.id.editText3)).setText("");
        	   username.requestFocus();
    	   }
    	   else
    	   {
	    	   Intent intent = new Intent(this, EventListActivity.class);
	    	   intent.putExtra("username", username.getText().toString());
			   startActivity(intent);
    	   }
       }
	}
	
	public boolean addUser(String username, String password) throws Exception
	{
		String base = "http://crowdsourceua.herokuapp.com/addUser.php?";
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
	
	public boolean emailIsFree(String username) throws Exception
	{
		String base = "http://crowdsourceua.herokuapp.com/emailIsFree.php?";
		String email = "email=" + username;
		String url = base + email;
		
		String json = new JSONReader().execute(url).get();
		
		if (json.contains("true") || json.contains("1"))
		{
			return true;
		}
		
		return false;
	}
	   
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
       // Inflate the menu; this adds items to the action bar if it is present.
       getMenuInflater().inflate(R.menu.register, menu);
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
            View rootView = inflater.inflate(R.layout.fragment_register, container, false);
            return rootView;
        }
    }

}
