package com.example.webview;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

	private WebView myWeb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		myWeb = findViewById(R.id.myWeb);

		requestPermission();

		myWeb.loadUrl("https://cn.bing.com/");
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
