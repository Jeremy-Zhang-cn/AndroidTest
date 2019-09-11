package com.example.expr_1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class register_success extends AppCompatActivity {

	TextView welcome;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register_success);
		setTitle("注册成功");
		welcome = findViewById(R.id.register_sucessTag);

		Intent intent = getIntent();
		String info = intent.getStringExtra("userName");
		welcome.setText("注册成功，"+info+"您好");
	}
}
