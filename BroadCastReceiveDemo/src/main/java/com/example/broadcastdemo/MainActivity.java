package com.example.broadcastdemo;

import android.content.IntentFilter;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

	private MyReceiver myReceiver1;
	private MyReceiver2 myReceiver2;
	private MyReceiver3 myReceiver3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		myReceiver1 = new MyReceiver();
		IntentFilter intentFilter1 = new IntentFilter();
		intentFilter1.addAction("message");
		intentFilter1.setPriority(100);

		myReceiver2 = new MyReceiver2();
		IntentFilter intentFilter2 = new IntentFilter();
		intentFilter2.addAction("message");
		intentFilter2.setPriority(200);

		myReceiver3 = new MyReceiver3();
		IntentFilter intentFilter3 = new IntentFilter();
		intentFilter3.addAction("message");
		intentFilter3.setPriority(300);

		registerReceiver(myReceiver1,intentFilter1);
		registerReceiver(myReceiver2,intentFilter2);
		registerReceiver(myReceiver3,intentFilter3);

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(myReceiver1);
		unregisterReceiver(myReceiver2);
		unregisterReceiver(myReceiver3);
	}
}
