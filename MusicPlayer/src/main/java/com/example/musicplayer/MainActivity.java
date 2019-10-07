package com.example.musicplayer;

import android.animation.ObjectAnimator;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	private static SeekBar sb;
	private static TextView tv_progress,tv_total;
	private static Button btn_start,btn_pause,btn_continue,btn_exit;
	private ObjectAnimator animator;
	private MusicService.MusicControl musicControl;
	MyServiceConn conn;
	Intent intent;
	private boolean isUnbind = false;	//记录服务是否被解绑


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tv_progress = findViewById(R.id.time_stamp);
		tv_total = findViewById(R.id.total_time);
		sb = findViewById(R.id.seekBar);
		btn_start = findViewById(R.id.btn_start);
		btn_pause = findViewById(R.id.btn_pause);
		btn_continue = findViewById(R.id.btn_continue);
		btn_exit = findViewById(R.id.btn_exit);

		btn_start.setOnClickListener(this);
		btn_pause.setOnClickListener(this);
		btn_continue.setOnClickListener(this);
		btn_exit.setOnClickListener(this);

		intent = new Intent(MainActivity.this, MusicService.class);	//创建意图对象
		conn = new MyServiceConn();	//创建服务连接对象
		bindService(intent, conn, BIND_AUTO_CREATE);	//绑定服务
		//为滑动条添加事件监听
		sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@RequiresApi(api = Build.VERSION_CODES.KITKAT)
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {	//滑动条改变时调用该方法
				if(progress == seekBar.getMax()){	//当滑动条滑到末端时，结束动画
					animator.pause();	//停止播放动画
				}
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {	//滑动条开始滑动时调用

			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {	//滑动条停止滑动时调用
				//根据拖动的进度改变音乐播放进度
				int progress = seekBar.getProgress();  //获取seekBar的进度
				musicControl.seekTo(progress); 	//改变播放进度
			}
		});

		ImageView iv_music = findViewById(R.id.music_Icon);
		animator = ObjectAnimator.ofFloat(iv_music,"rotation",0f,360.0f);
		animator.setDuration(10000);	//动画旋转一周的时间为10秒
		animator.setInterpolator(new LinearInterpolator());
		animator.setRepeatCount(-1);	//-1表示动画无限循环
	}


	public static Handler handler = new Handler() {	//创建消息处理器对象
		//在主线程中处理从子线程发送过来的消息


		@Override
		public void handleMessage(@NonNull Message msg) {

			Bundle bundle = msg.getData();	//获取从子线程发送过来的音乐播放进度
			int duration = bundle.getInt("duration");	//歌曲的总时长
			//歌曲当前进度
			int currentPosition = bundle.getInt("currentPosition");
			sb.setMax(duration);	//设置SeekBar的最大值为歌曲总时长
			sb.setProgress(currentPosition);	//设置SeekBar当前的进度位置
			//歌曲的总时长
			int minute = duration / 1000 / 60;
			int second = duration / 1000 % 60;
			String strMinute = null;
			String strSecond = null;
			if(minute < 10) {	//如果歌曲时间分钟小于10，在分钟前加个0
				strMinute = "0" + minute;
			} else {
				strMinute = minute + "";
			}
			if(second < 10) {
				strSecond = "0" + second;	//如果歌曲时间的秒小于10，在秒前加个0
			} else {
				strSecond = second + "";
			}
			tv_total.setText(strMinute +":"+strSecond);

			//歌曲当前播放时长
			minute = currentPosition / 1000 / 60;
			second = currentPosition / 1000 % 60;
			if(minute < 10) {	//如果歌曲时间分钟小于10，在分钟前加个0
				strMinute = "0" + minute;
			} else {
				strMinute = minute + "";
			}
			if(second < 10) {
				strSecond = "0" + second;	//如果歌曲时间的秒小于10，在秒前加个0
			} else {
				strSecond = second + "";
			}
			tv_progress.setText(strMinute +":"+strSecond);
			super.handleMessage(msg);
		}
	};

	class MyServiceConn implements ServiceConnection {

		@Override
		public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
			musicControl = (MusicService.MusicControl) iBinder;
		}

		@Override
		public void onServiceDisconnected(ComponentName componentName) {

		}
	}

	private void unbind(boolean isUnbind) {
		if(!isUnbind) {	//判断服务是否被解绑
			musicControl.pausePlay();	//暂停播放音乐
			unbindService(conn);	//解绑服务
			stopService(intent);	//停止服务
		}
	}



	@RequiresApi(api = Build.VERSION_CODES.KITKAT)
	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.btn_start:	//播放按钮点击事件
				musicControl.play();	//播放音乐
				animator.start();	//播放动画
				break;

			case R.id.btn_pause:
				musicControl.pausePlay();	//暂停播放音乐
				animator.pause();	//暂停播放动画
				break;

			case R.id.btn_continue:
				musicControl.continuePlay();	//继续播放音乐
				animator.start();	//播放动画
				break;

			case R.id.btn_exit:
				unbind(isUnbind);	//解绑服务绑定
				isUnbind = true;	//完成解绑服务
				finish();	//关闭音乐播放界面
				break;
		}
	}


	@Override
	protected void onDestroy() {
		super.onDestroy();
		unbind(isUnbind);	//解绑服务
	}
}
