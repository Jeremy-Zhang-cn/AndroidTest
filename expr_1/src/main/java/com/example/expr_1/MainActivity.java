package com.example.expr_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


	private Button register,login;
	private EditText name,passwd;
	private Button pwd_Change;

//	private RadioButton male_Button,female_Button;
//	private RadioGroup Gender;
//	private Button btn3;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.linearsample);

		/*Gender = (RadioGroup) findViewById(R.id.RadioGroup_Gender);
		male_Button = (RadioButton) findViewById(R.id.male_RB);
		female_Button = (RadioButton) findViewById(R.id.female_RB);
		btn3 = findViewById(R.id.btn3);*/

		register = findViewById(R.id.btn1);
		login = findViewById(R.id.btn2);
		name = findViewById(R.id.editText);
		passwd = findViewById(R.id.editText4);

		pwd_Change = findViewById(R.id.pwd_Change);

		Intent intent = getIntent();
		name.setText(intent.getStringExtra("userName"));	//获取其他页面传输的用户名信息


		//为修改密码按钮创建事件监听器
		pwd_Change.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(MainActivity.this, Change_pwd.class);
				if(name.getText().toString().equals("admin") && passwd.getText().toString().equals("123456")) {
					Intent intent1 = new Intent(MainActivity.this,Change_pwd.class);
					startActivityForResult(intent,1);	//数据回传请求
				} else {
					Toast.makeText(MainActivity.this,"请输入正确的用户名及密码",Toast.LENGTH_LONG).show();
				}
			}
		});


		/*btn3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				int counting = Gender.getChildCount();
				for(int a=0;a<counting;a++) {  //使用额外按钮监听获取RadioButton内容
					RadioButton rb = (RadioButton)Gender.getChildAt(a);
					if(rb.isChecked()) {
						Toast.makeText(MainActivity.this,rb.getText().toString(),Toast.LENGTH_LONG).show();
					}
				}
			}
		});

		Gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { //无额外按钮监听获取RadioButton内容
			@Override
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				RadioButton rb = (RadioButton) findViewById(checkedId);
				Toast.makeText(MainActivity.this,rb.getText().toString(),Toast.LENGTH_LONG).show();
			}
		});*/

		register.setOnClickListener(this);	//接口实现按钮事件监听

		login.setOnClickListener(new View.OnClickListener() {	//匿名内部类实现按钮事件监听
			@Override
			public void onClick(View view) {

				if(name.getText().toString().equals("admin") && passwd.getText().toString().equals("123456")){
					Toast.makeText(MainActivity.this,"correct usr name and passwd",Toast.LENGTH_LONG).show();  //在当前窗口提示登录信息

					Intent intent = new Intent(MainActivity.this,Activity2.class);
					intent.putExtra("usrName",name.getText().toString());  //传递登录信息
					startActivity(intent);
				} else {
					Toast.makeText(MainActivity.this, "Incorrect usr name or passwd", Toast.LENGTH_LONG).show();
				}

			}
		});


	}


	@Override
	public void onClick(View view) {	//重写事件监听接口中的onClick方法

		/*if(name.getText().toString().equals("admin") && passwd.getText().toString().equals("123456")){
			Toast.makeText(MainActivity.this,"correct usr name and passwd",Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(MainActivity.this, "Incorrect usr name or passwd", Toast.LENGTH_LONG).show();
		}*/

		//点击注册时，实现页面跳转
		Intent intent_register = new Intent(MainActivity.this,Register.class);
		startActivity(intent_register);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == 1 && resultCode == 2) {
			passwd.setText(data.getStringExtra("newPwd"));	//接收回传数据
		}
	}
}
