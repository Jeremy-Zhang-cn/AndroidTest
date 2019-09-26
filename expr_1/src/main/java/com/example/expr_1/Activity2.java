package com.example.expr_1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Activity2 extends AppCompatActivity {

	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_2);
		setTitle("系统界面");
		textView = findViewById(R.id.textView3);

		//显示用户名
		Intent intent = getIntent();	//接收登录信息
		String s = intent.getStringExtra("usrName");
		textView.setText("你好，"+s);
	}
}
