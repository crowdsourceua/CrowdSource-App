package com.example.uacrowdsource;

import android.location.Location;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Event implements Serializable
{
	private String id;
	private String title;
	private String date;
	private String time;
	private String sport;
	private String opponent;
	private String venue;
	private String location;
	
	public Event(String i, String t, String d, String ti, String s, String o, String v, String l)
	{
		setId(i);
		setTitle(t);
		setDate(d);
		setTime(ti);
		setSport(s);
		setOpponent(o);
		setVenue(v);
		setLocation(l);
	}
	public String getId()
	{
		return id;
	}
	private void setId(String id)
	{
		this.id = id;
	}
	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getDate()
	{
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime()
	{
		return time;
	}

	public void setTime(String time)
	{
		this.time = time;
	}

	public String getSport()
	{
		return sport;
	}

	public void setSport(String sport)
	{
		this.sport = sport;
	}

	public String getOpponent()
	{
		return opponent;
	}

	public void setOpponent(String opponent)
	{
		this.opponent = opponent;
	}

	public String getLocation()
	{
		return location;
	}

	public void setLocation(String location)
	{
		this.location = location;
	}
	
	public String toString()
	{
		return title + " you called toString";
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}
}
