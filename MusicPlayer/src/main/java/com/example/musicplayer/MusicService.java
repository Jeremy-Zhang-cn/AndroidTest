package com.example.musicplayer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;

import java.util.Timer;
import java.util.TimerTask;

public class MusicService extends Service {

	private MediaPlayer player;
	private Timer timer;

	public MusicService() { }


	@Override
	public IBinder onBind(Intent intent) {
		return new MusicControl();
	}


	@Override
	public void onCreate() {
		super.onCreate();
		player = new MediaPlayer();	//创建音乐播放器对象
	}

	public void addTimer() {	//添加计时器用于设置音乐播放器中的播放进度条
		if(timer == null) {
			timer = new Timer();
			TimerTask task = new TimerTask() {
				@Override
				public void run() {
					if(player == null) return;
					int duration = player.getDuration();	//获取歌曲总时长
					//获取播放速度
					int currentPosition = player.getCurrentPosition();
					//创建消息对象
					Message msg = MainActivity.handler.obtainMessage();
					//将音乐的总时长和播放速度封装执消息对象中
					Bundle bundle = new Bundle();
					bundle.putInt("duration", duration);
					bundle.putInt("currentPosition",currentPosition);
					msg.setData(bundle);

					MainActivity.handler.sendMessage(msg);

				}
			};
			//开始计时任务后的5毫秒，第一次执行task任务，以后每500毫秒执行一次
				timer.schedule(task,5,500);
				}
			}
			class MusicControl extends Binder {
				public void play() {
					try {
						player.reset();	//重置音乐播放器
						//加载多媒体文件
						player = MediaPlayer.create(getApplicationContext(), R.raw.music);
						player.start();	//播放音乐
						addTimer();	//添加计时器
					} catch (Exception e) {
						e.printStackTrace();
					}
		}

		public void pausePlay() {
					player.pause();		//暂停播放
		}

		public void continuePlay() {
					player.start();		//继续播放
		}

		public void seekTo(int progress) {
					player.seekTo(progress);	//设置音乐的播放位置
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if(player == null) return;
		if(player.isPlaying()) player.stop();	//停止播放音乐
		player.release();		//释放占用的资源
		player = null;		//将player置为空
	}
}
