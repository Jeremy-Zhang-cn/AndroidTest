package com.example.myservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity implements View.OnClickListener{

	private Button btn_start,btn_stop,btn_bind,btn_unbind,btn_call;
	private TextView tv_time;
	private MyService.MyBinder myBinder;
	private MyConn myConn;



	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btn_start = findViewById(R.id.btn_startService);
		btn_stop = findViewById(R.id.btn_stopService);
		btn_bind = findViewById(R.id.btn_bind);
		btn_unbind = findViewById(R.id.btn_unbind);
		btn_call = findViewById(R.id.btn_call);
		tv_time = findViewById(R.id.tv_timeStamp);

		btn_bind.setOnClickListener(this);
		btn_call.setOnClickListener(this);
		btn_unbind.setOnClickListener(this);

		btn_start.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				Intent intent = new Intent(MainActivity.this, MyService.class);
				startService(intent);
			}
		});


		btn_stop.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				Intent intent = new Intent(MainActivity.this, MyService.class);
				stopService(intent);
			}
		});

	}


	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.btn_bind:
				if(myConn == null) {
					myConn = new MyConn();
				}
				Intent intent = new Intent(MainActivity.this, MyService.class);
				bindService(intent, myConn, BIND_AUTO_CREATE);
				break;

			case R.id.btn_call:
				tv_time.setText(myBinder.callMethodInService());
				break;

			case R.id.btn_unbind:
				if(myConn != null) {
					unbindService(myConn);
					myConn = null;
				}
				break;
		}
	}


	private class MyConn implements ServiceConnection {

		@Override
		public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
			myBinder = (MyService.MyBinder) iBinder;
			Log.i("msg", "服务成功绑定，内存地址为：" + myBinder.toString());
		}

		@Override
		public void onServiceDisconnected(ComponentName componentName) {

		}
	}


}
