package com.example.uacrowdsource;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 
public class MobileArrayAdapter extends ArrayAdapter<Event> {
	private final Context context;
	private final ArrayList<Event> values;
	
	public MobileArrayAdapter(Context context, ArrayList<Event> values) {
		super(context, R.layout.activity_event_list, values);
		this.context = context;
		this.values = values;
	}
 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View rowView = inflater.inflate(R.layout.activity_event_list, parent, false);
		TextView titleView = (TextView) rowView.findViewById(R.id.title);
		titleView.setTag(position);
		TextView dateView = (TextView) rowView.findViewById(R.id.date);
		TextView venueView = (TextView) rowView.findViewById(R.id.venue);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);
		imageView.setTag(position);
		titleView.setText(values.get(position).getTitle());
		dateView.setText("" + values.get(position).getDate() + 
						 ", " + values.get(position).getTime());
		venueView.setText(values.get(position).getSport() + " at " + values.get(position).getVenue());
		
		// Change icon based on name
		String s = values.get(position).getSport();
 
		if (s.toLowerCase().contains("basketball"))
		{
			imageView.setImageResource(R.drawable.basketball);
		}
		else if (s.toLowerCase().contains("football"))
		{
			imageView.setImageResource(R.drawable.football);
		}
		else if (s.toLowerCase().contains("foosball"))
		{
			imageView.setImageResource(R.drawable.foosball);
		}
		else
		{
			imageView.setImageResource(R.drawable.alabama);
		}
 
		return rowView;
	}
	
	public Event getEvent(int position)
	{
		return values.get(position);
	}
}