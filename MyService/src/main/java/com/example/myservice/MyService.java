package com.example.myservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyService extends Service {

	public MyService() {

	}

	class MyBinder extends Binder {

		public String callMethodInService() {
			return methodInService();	//调用服务中自定义的方法
		}
	}

	public String methodInService () {

		Log.i("msg", "执行服务中的methodInservice()方法");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat
				("YYYY--MM--dd HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());	//获取当前时间
		return simpleDateFormat.format(date);

	}


	@Override
	public void onCreate() {
		Log.i("msg", "执行onCreate()");
		super.onCreate();
	}


	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i("msg","执行onCommand()"+startId);
		return super.onStartCommand(intent,flags,startId);
	}


	@Override
	public IBinder onBind(Intent intent) {
		// TODO: Return the communication channel to the service.
		Log.i("msg","绑定服务，执行onBind()方法");
		return new MyBinder();
	}


	@Override
	public boolean onUnbind(Intent intent) {
		Log.i("msg", "解绑服务，执行onUnbind()方法");
		return super.onUnbind(intent);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i("msg", "执行onDestroy()");
	}



}
