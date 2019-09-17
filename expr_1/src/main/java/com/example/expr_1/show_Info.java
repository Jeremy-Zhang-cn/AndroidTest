package com.example.expr_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class show_Info extends AppCompatActivity {

	TextView show_Name,show_Passwd,show_Email,show_Phone;
	Button info_Change;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show__info);

		show_Name = findViewById(R.id.show_Name);
		show_Passwd = findViewById(R.id.show_passwd);
		show_Phone = findViewById(R.id.show_Phone);
		show_Email = findViewById(R.id.show_Email);
		info_Change = findViewById(R.id.info_Change);
		final userInfo user1 = (userInfo)getIntent().getSerializableExtra("info");
		Intent intent = getIntent();

		setTitle("用户注册信息");
		show_Name.setText("用户名："+user1.getUserName());
		show_Passwd.setText("密码："+user1.getPasswd());
		show_Phone.setText("手机号:"+user1.getPhone());
		show_Email.setText("邮箱："+user1.getEmail());

		//修改信息的监听器
		info_Change.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				String userName = user1.getUserName();
				String userPasswd = user1.getPasswd();
				String userEmail = user1.getEmail();
				String userPhone = user1.getPhone();
				userInfo user = new userInfo(userName,userPasswd,userPhone,userEmail);
				Bundle bundle = new Bundle();
				bundle.putSerializable("info1",user);
				Intent intent = new Intent(show_Info.this,userInfo_Change.class);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});

	}
}
