package com.example.netdemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

	private EditText et_address;
	private Button btn_check,btn_clear;
	private ImageView myImage;
	private Bitmap bitmap;


	private Handler handler = new Handler() {
		@Override
		public void handleMessage(@NonNull Message msg) {
			switch (msg.what) {
				case 0x001:
					myImage.setImageBitmap(bitmap);
					Toast.makeText(MainActivity.this,"图片加载完毕",
							Toast.LENGTH_SHORT).show();
					break;

				default:
					break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		et_address = findViewById(R.id.et_address);
		btn_check = findViewById(R.id.btn_check);
		btn_clear = findViewById(R.id.btn_clear);
		myImage = findViewById(R.id.mypicture);

		requestPermission();


		btn_clear.setOnClickListener(new View.OnClickListener() {	//清空编辑框
			@Override
			public void onClick(View view) {
				et_address.setText("");
			}
		});

		btn_check.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if ((et_address.getText().toString()).equals("")) {
					Toast.makeText(MainActivity.this,"链接为空",Toast.LENGTH_SHORT).show();
				} else {
					new Thread() {
						@Override
						public void run() {
							try {
								String path = et_address.getText().toString();
								byte[] data = GetData.getImage(path);
								bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
							} catch (Exception e) {
								e.printStackTrace();
							}
							handler.sendEmptyMessage(0x001);
						}
					}.start();
				}
			}
		});
	}

	public void requestPermission() {

		if (Build.VERSION.SDK_INT >= 23) {

			int REQUEST_CODE_CONTACT = 101;
			String[] PERMISSION_INTERNET = {
					Manifest.permission.INTERNET,
					Manifest.permission.ACCESS_NETWORK_STATE
			};

			for (String str : PERMISSION_INTERNET) {

				if (this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {

					this.requestPermissions(PERMISSION_INTERNET,REQUEST_CODE_CONTACT);


				}
			}
		}
	}
}
