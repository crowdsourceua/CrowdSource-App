package com.example.uacrowdsource;

import java.util.TimerTask;

import android.app.Activity;

public class MyTimerTask extends TimerTask {
	
	Activity act = null;
	
	public MyTimerTask(Activity a)
	{
		act = a;
	}
	
    @Override
    public void run() {
        act.runOnUiThread(new Runnable() {
            @Override
            public void run() {
               //code to get and send location information

            }
        });
    }
}
