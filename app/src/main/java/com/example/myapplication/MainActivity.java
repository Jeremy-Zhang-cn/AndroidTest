package com.example.myapplication;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

	private EditText et_account;
	private EditText et_password;
	private Button btn_login;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		et_account = findViewById(R.id.userName);
		et_password = findViewById(R.id.userPasswd);
		btn_login = findViewById(R.id.login_btn);

		Map<String,String> userInfo = fileSaveQQ.getUserInfo(this);
		if(userInfo != null) {
			et_account.setText(userInfo.get("account"));
			et_password.setText(userInfo.get("password"));
		}

		btn_login.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				String account = et_account.getText().toString().trim();
				String password = et_password.getText().toString();

				if(TextUtils.isEmpty(account)) {
					Toast.makeText(MainActivity.this,"请输入账号",Toast.LENGTH_SHORT).show();
					return;
				}
				if(TextUtils.isEmpty(password)) {
					Toast.makeText(MainActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
					return;
				}
				Toast.makeText(MainActivity.this,"登陆成功",Toast.LENGTH_SHORT).show();

				boolean isSaveSuccess = false;
				try {
					isSaveSuccess = fileSaveQQ.saveUserInfo(MainActivity.this,account,password);
				} catch (IOException e) {
					e.printStackTrace();
				}

				if(isSaveSuccess) {
					Toast.makeText(MainActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(MainActivity.this,"保存失败",Toast.LENGTH_SHORT).show();
				}
			}
		});
	}



}
