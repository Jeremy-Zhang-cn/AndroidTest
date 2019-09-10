package com.example.uidemo;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

	TextView display;
	Button left,right,confirm;
	Switch aSwitch;
	ProgressBar progressBar;
	EditText editText;
	RadioGroup radioGroup;
	ImageView imageView;
	SeekBar seekBar;
	CheckBox chinese,math,english;
	RatingBar ratingBar;
	String chinese_value="";
	String math_value="";
	String english_value="";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//实例化对象
		display = findViewById(R.id.textView1);
		left = findViewById(R.id.left_btn);
		right = findViewById(R.id.right_btn);
		confirm = findViewById(R.id.process_btn);
		aSwitch = findViewById(R.id.switch1);
		progressBar = findViewById(R.id.progressBar4);
		editText = findViewById(R.id.editText);
		radioGroup = findViewById(R.id.RadioGroup1);
		imageView = findViewById(R.id.android_icon);
		seekBar = findViewById(R.id.seekBar);
		chinese = findViewById(R.id.checkBox);
		math = findViewById(R.id.checkBox3);
		english = findViewById(R.id.checkBox2);
		ratingBar = findViewById(R.id.ratingBar);

		//设置监听器
		left.setOnClickListener(new View.OnClickListener() {	//为左按键设置监听器，点击时display显示对应文字
			@Override
			public void onClick(View view) {
				display.setText(R.string.left_btn);
			}
		});

		right.setOnClickListener(new View.OnClickListener() {	//为右按键设置监听器
			@Override
			public void onClick(View view) {
				display.setText(R.string.right_btn);
			}
		});

		aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {	//为开关设置监听器,点击时display现实对应文字
			@Override
			public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
				if(b) {
					display.setText(R.string.swt_on);
				}else{
					display.setText(R.string.swt_off);
				}
			}
		});

		confirm.setOnClickListener(new View.OnClickListener() {		//为确认按钮设置监听器，点击时获取文本框中数值
			@Override
			public void onClick(View view) {
				String editContext;
				editContext = editText.getText().toString();
				if(TextUtils.isEmpty(editContext)) {	//判断文本框中内容是否为空
					editContext = "0";
				}
				progressBar.setProgress(Integer.valueOf(editContext));	//在进度条上显示所输入数值
				display.setText(editContext);		//在display中显示所输入数值
			}
		});

		radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {	//为单选框组设置监听器，改变为对应选项图片
			@Override
			public void onCheckedChanged(RadioGroup radioGroup, int i) {
				if(i == R.id.Android_icon_RB) {
					imageView.setImageResource(R.drawable.android);
				}else {
					imageView.setImageResource(R.drawable.ios);
				}
			}
		});

		seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {	//为SeekBar设置监听器，改变display为对应值
			@Override
			public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
				display.setText(String.valueOf(i));
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {

			}
		});


		//为3个复选框设置监听器，设置display为对应值
		chinese.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
				if(b) {
					chinese_value = "语文";
				} else {
					chinese_value = "";
				}
				display.setText(chinese_value+math_value+english_value);
			}
		});

		math.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
				if(b) {
					math_value = "数学";
				} else {
					math_value = "";
				}
				display.setText(chinese_value+math_value+english_value);
			}
		});

		english.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
				if (b) {
					english_value = "英语";
				} else {
					english_value = "";
				}
				display.setText(chinese_value+math_value+english_value);
			}
		});

		//为RatingBar设置监听器，在屏幕底端显示相应等级分数
		ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
			@Override
			public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
				Toast.makeText(getApplicationContext(),String.valueOf(v)+"星评价!",Toast.LENGTH_SHORT).show();
			}
		});

	}
}
