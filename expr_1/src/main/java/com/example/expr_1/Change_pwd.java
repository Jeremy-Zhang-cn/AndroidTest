package com.example.expr_1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Change_pwd extends AppCompatActivity {

	private EditText Name;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_pwd);

		Name = findViewById(R.id.pwd_ChangeName);

		setTitle("修改密码");
		Intent intent = getIntent();
		Name.setText(intent.getStringExtra("userName"));


	}
}
