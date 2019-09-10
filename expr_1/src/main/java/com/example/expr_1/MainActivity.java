package com.example.expr_1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


	private Button register,login;
	private EditText name,passwd;
	private RadioButton male_Button,female_Button;
	private RadioGroup Gender;
	private Button btn3;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.linearsample);

		Gender = (RadioGroup) findViewById(R.id.RadioGroup_Gender);
		male_Button = (RadioButton) findViewById(R.id.male_RB);
		female_Button = (RadioButton) findViewById(R.id.female_RB);
		btn3 = findViewById(R.id.btn3);

		register = findViewById(R.id.btn1);
		login = findViewById(R.id.btn2);
		name = findViewById(R.id.editText);
		passwd = findViewById(R.id.editText4);

		btn3.setOnClickListener(new View.OnClickListener() {
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
		});

		register.setOnClickListener(this);	//接口实现按钮事件监听

		login.setOnClickListener(new View.OnClickListener() {	//匿名内部类实现按钮事件监听
			@Override
			public void onClick(View view) {

				if(name.getText().toString().equals("admin") && passwd.getText().toString().equals("123456")){
					Toast.makeText(MainActivity.this,"correct usr name and passwd",Toast.LENGTH_LONG).show();
				} else {
					Toast.makeText(MainActivity.this, "Incorrect usr name or passwd", Toast.LENGTH_LONG).show();
				}

			}
		});
	}


	@Override
	public void onClick(View view) {	//重写事件监听接口中的onClick方法

		if(name.getText().toString().equals("admin") && passwd.getText().toString().equals("123456")){
			Toast.makeText(MainActivity.this,"correct usr name and passwd",Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(MainActivity.this, "Incorrect usr name or passwd", Toast.LENGTH_LONG).show();
		}
	}
}
