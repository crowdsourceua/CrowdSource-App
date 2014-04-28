package com.example.uacrowdsource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
 
public class EventListActivity extends ListActivity
{
	ArrayList<Event> eventList = null;
	ArrayList<String> qIds = new ArrayList<String>();
	
	double latitude;
	double longitude;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		GpsLocationTracker mGpsLocationTracker = new GpsLocationTracker(EventListActivity.this);

        if (mGpsLocationTracker.canGetLocation()) 
        {
            latitude = mGpsLocationTracker.getLatitude();
            longitude = mGpsLocationTracker.getLongitude();
        }
		
		try {
			eventList = getEventList(latitude, longitude);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setListAdapter(new MobileArrayAdapter(this, eventList));
	}
 
	public void listItemClick(View view) {
 
		//get selected items
		Event event = eventList.get(Integer.parseInt(view.getTag().toString()));
		
		Toast.makeText(this, "Checking into: " + event.getTitle(), Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(this, EventActivity.class);
		intent.putExtra("eventObj", event);
		intent.putExtra("qIds", qIds);
		intent.putExtra("triviaright", 0);
		intent.putExtra("triviatotal", 0);
		intent.putExtra("polls", 0);
		startActivity(intent);
	}
	
	public static ArrayList<Event> getEventList(double lat, double lon) throws JSONException, IOException, InterruptedException, ExecutionException
	{	
		boolean haveLoc = true;
		
		if (lat == 0.0 || lon == 0.0)
		{
			haveLoc = false;
		}
		
		String url = "http://crowdsourceua.herokuapp.com/index.php";
		String json = new JSONReader().execute(url).get();
		JSONArray jsonarr = new JSONArray(json);
		
		// eventually from web service
		ArrayList<Event> eventList = new ArrayList<Event>();
		
		for (int i = 0; i < jsonarr.length(); i++)
		{
			JSONObject jsonobj = jsonarr.getJSONObject(i);
			
			String id = jsonobj.getString("eventId");
			String title = jsonobj.getString("eventName");
			String date = jsonobj.getString("date");
			String time = jsonobj.getString("eventTime");
			String sport = jsonobj.getString("eventType");
			String opponent = jsonobj.getString("opponent");
			String venue = jsonobj.getString("eventVenue");
			String location = jsonobj.getString("location");
			
			Calendar eventDate = new GregorianCalendar();
			
			int month = Integer.parseInt(date.split("/")[0]);
			int day = Integer.parseInt(date.split("/")[1]);
			int year = Integer.parseInt(date.split("/")[2]);
			
			eventDate.set(Calendar.YEAR, year);
			eventDate.set(Calendar.MONTH, month-1);
			eventDate.set(Calendar.DAY_OF_MONTH, day);
			
			Calendar today = new GregorianCalendar();
			
			if (eventDate.before(today))
			{
				continue; // event happened already
			}
			
			if (haveLoc)
			{
				double eLat = Double.parseDouble(location.split(", ")[0]);
				double eLon = Double.parseDouble(location.split(", ")[1]);
				
				if (Math.abs(lat-eLat) > 0.5 || Math.abs(lon-eLon) > 0.5)	// out of range
				{
					continue;
				}
			}
			
			eventList.add(new Event(id, title, date, time, sport, opponent, venue, location));
		}
		
		return eventList;
	}
}