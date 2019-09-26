package com.example.localization;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

	TextView Message;
	Button show_btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Message = findViewById(R.id.message);
		show_btn = findViewById(R.id.show_btn);
		Message.setText("");

		show_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Message.setText(R.string.message_info);
			}
		});

	}
}
