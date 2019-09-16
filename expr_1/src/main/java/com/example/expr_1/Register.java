package com.example.expr_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {

	private Button confirm;
	private EditText name,passwd,passwdConfirm,email,phone;
	private RadioButton male_Button,female_Button;
	private RadioGroup Gender;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		Gender = (RadioGroup) findViewById(R.id.register_Gender_RG);
		male_Button = (RadioButton) findViewById(R.id.register_male_RB);
		female_Button = (RadioButton) findViewById(R.id.register_female_RB);
		confirm = findViewById(R.id.register_confirm);
		name = findViewById(R.id.register_name);
		passwd = findViewById(R.id.register_pwd);
		passwdConfirm = findViewById(R.id.register_pwdConfirm);
		email = findViewById(R.id.register_Email);
		phone = findViewById(R.id.register_Phone);

		Gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup radioGroup, int i) {
				RadioButton rb = (RadioButton) findViewById(i);
				Toast.makeText(Register.this,rb.getText().toString(),Toast.LENGTH_LONG).show();
			}
		});

		//为确认按钮添加监听器，实现对文本框内容判断并进行页面跳转
		/*confirm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if ((name.getText().toString().equals("")) || (passwd.getText().toString().equals("")) || (passwdConfirm.getText().toString().equals(""))) {
					Toast.makeText(Register.this,"请完善用户信息",Toast.LENGTH_LONG).show();
				} else {
					Toast.makeText(Register.this,"注册成功",Toast.LENGTH_LONG).show();
					Intent intent = new Intent(Register.this,MainActivity.class );
					intent.putExtra("userName", name.getText().toString());
					startActivity(intent);
				}
			}
		});*/

		confirm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if ((name.getText().toString().equals("")) || (passwd.getText().toString().equals("")) || (passwdConfirm.getText().toString().equals(""))) {
					Toast.makeText(Register.this,"请完善用户信息",Toast.LENGTH_LONG).show();
				} else {
					Toast.makeText(Register.this,"注册成功",Toast.LENGTH_LONG).show();
					String userName = name.getText().toString();
					String userPasswd = passwd.getText().toString();
					String userPasswdConfirm = passwdConfirm.getText().toString();
					String userEmail = email.getText().toString();
					String userPhone = phone.getText().toString();
					userInfo user1 = new userInfo(userName,userPasswd,userPhone,userEmail);
					Bundle bundle = new Bundle();
					bundle.putSerializable("info",user1);
					Intent intent = new Intent(Register.this,show_Info.class);
					intent.putExtras(bundle);
					startActivity(intent);
				}
			}
		});

	}
}
