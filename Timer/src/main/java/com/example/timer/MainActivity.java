package com.example.timer;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

	private EditText et_input;
	private TextView tv_show;
	private Button btn_start;
	private int time;
	private Handler hd;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		et_input = findViewById(R.id.et_input);
		tv_show = findViewById(R.id.tv_show);
		btn_start = findViewById(R.id.btn_start);

		hd = new Handler() {
			@Override
			public void handleMessage(@NonNull Message msg) {
				switch (msg.what) {
					case 1:
						if(msg.arg1 > 0) {
						tv_show.setText(msg.arg1+"");
						} else {
							tv_show.setText("计时结束");
						}
						break;

					default:
						break;
				}
			}
		};

		btn_start.setOnClickListener(this);


	}

	@Override
	public void onClick(View view) {

		switch (view.getId()) {
			case R.id.btn_start:
				time = Integer.parseInt((et_input.getText().toString()));
				new Thread() {
					@Override
					public void run() {
						while (time >= 0) {
							try {
								Thread.sleep(1000);
								Message msg = new Message();
								msg.what = 1;
								msg.arg1 = time;
								hd.sendMessage(msg);
								time--;
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}.start();
				break;


			default:

				break;
		}
	}
}
