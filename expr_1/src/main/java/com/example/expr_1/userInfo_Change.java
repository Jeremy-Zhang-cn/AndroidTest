package com.example.expr_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class userInfo_Change extends AppCompatActivity {

	EditText usrName,usrPassword,usrPhone,usrEmail;
	Button btn_submit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_info__change);

		usrName = findViewById(R.id.changed_Info_Name);
		usrPassword = findViewById(R.id.changed_Info_passwd);
		usrPhone = findViewById(R.id.changed_Info_Phone);
		usrEmail = findViewById(R.id.changed_Info_Email);
		btn_submit = findViewById(R.id.changed_Info_submit);

		final userInfo user = (userInfo)getIntent().getSerializableExtra("info1");
		Intent intent = getIntent();

		usrName.setText(user.getUserName());
		usrPassword.setText(user.getPasswd());
		usrPhone.setText(user.getPhone());
		usrEmail.setText(user.getEmail());

		//设置监听器
		btn_submit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				String userName = usrName.getText().toString();
				String userPasswd = usrPassword.getText().toString();
				String userEmail = usrEmail.getText().toString();
				String userPhone = usrPhone.getText().toString();
				userInfo user = new userInfo(userName,userPasswd,userPhone,userEmail);
				Bundle bundle = new Bundle();
				bundle.putSerializable("info",user);
				Intent intent = new Intent(userInfo_Change.this,show_Info.class);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});


	}
}
