package com.example.broadcastsenddemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

	private Button btn_send,btn_randomCast;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btn_send = findViewById(R.id.btn_sendBroad);
		btn_randomCast = findViewById(R.id.btn_randomCast);

		btn_send.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent();
				intent.putExtra("info","This is a disordered message");
				intent.setAction("message");
				sendBroadcast(intent);
			}
		});

		btn_randomCast.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent();
				intent.putExtra("info","This is an ordered message");
				intent.setAction("message");
				sendOrderedBroadcast(intent,null);
			}
		});
	}
}
