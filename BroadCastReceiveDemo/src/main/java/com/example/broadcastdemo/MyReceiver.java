package com.example.broadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {



	@Override
	public void onReceive(Context context, Intent intent) {

		Log.i("msg", "This is a message from the 1st receiver" + "," + intent.getStringExtra("info"));

	}

}
