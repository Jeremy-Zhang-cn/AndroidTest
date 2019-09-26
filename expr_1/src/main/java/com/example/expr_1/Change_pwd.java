package com.example.expr_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Change_pwd extends AppCompatActivity {

	private EditText Name;
	private Button Confirm;
	private EditText pwdNew;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_pwd);

		Name = findViewById(R.id.pwd_ChangeName);
		Confirm = findViewById(R.id.confirm);
		pwdNew = findViewById(R.id.pwd_new);

		setTitle("修改密码");
		final Intent intent = getIntent();
		Name.setText(intent.getStringExtra("userName"));

		Confirm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				intent.putExtra("newPwd",pwdNew.getText().toString());
				setResult(2,intent);
				finish();
			}
		});

	}
}
