package com.example.storesample;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	private EditText et_input;
	private TextView et_show;
	private Button btn_save, btn_read;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		et_input = findViewById(R.id.et_input);
		et_show = findViewById(R.id.et_show);
		btn_save = findViewById(R.id.btn_save);
		btn_read = findViewById(R.id.btn_read);


		btn_save.setOnClickListener(this);
		btn_read.setOnClickListener(this);

		requestPermission();

	}

	@Override
	public void onClick(View view) {

		switch (view.getId()) {

			case R.id.btn_save:

				String state = Environment.getExternalStorageState();

				if (state.equals(Environment.MEDIA_MOUNTED)) {
					File sdPath = Environment.getExternalStorageDirectory();
					File sdFile = new File(sdPath, "data.txt");
					String content = et_input.getText().toString();
					FileOutputStream fos = null;

					try {

						fos = new FileOutputStream(sdFile,true);
						fos.write(content.getBytes());
						fos.flush();
						Toast.makeText(MainActivity.this, "存储成功", Toast.LENGTH_SHORT).show();
					} catch (IOException e) {
						e.printStackTrace();
						Toast.makeText(MainActivity.this, "存储失败", Toast.LENGTH_SHORT).show();
					} finally {
						if (fos != null) {
							try {
								fos.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}

				} else {
					Toast.makeText(MainActivity.this, "cannot read SD card", Toast.LENGTH_SHORT).show();
				}
				break;

			case R.id.btn_read:

				String state1 = Environment.getExternalStorageState();
				if (state1.equals(Environment.MEDIA_MOUNTED)) {
					File SDPath = Environment.getExternalStorageDirectory();
					File file = new File(SDPath, "data.txt");
					FileInputStream fis = null;
					BufferedReader br = null;

					try {

						fis = new FileInputStream(file);
						br = new BufferedReader(new InputStreamReader(fis));
						String data = br.readLine();
						et_show.setText(data);

					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						if (br != null) {
							try {
								br.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						if (fis != null) {
							try {
								fis.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}

				}

				break;
		}
	}

	public void requestPermission() {

		if (Build.VERSION.SDK_INT >= 23) {

			int REQUEST_CODE_CONTACT = 101;
			String[] PERMISSION_STORAGE = {
					Manifest.permission.WRITE_EXTERNAL_STORAGE,
					Manifest.permission.READ_EXTERNAL_STORAGE
			};

		/*	if (this.checkSelfPermission(PERMISSION_STORAGE[0]) != PackageManager.PERMISSION_GRANTED) {
				this.requestPermissions(PERMISSION_STORAGE, 101);
			}*/

			for (String str : PERMISSION_STORAGE) {

				if (this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {

				this.requestPermissions(PERMISSION_STORAGE,REQUEST_CODE_CONTACT);
				return;

				}
			}
		}
	}
}