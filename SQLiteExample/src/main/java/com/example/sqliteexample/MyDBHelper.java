package com.example.sqliteexample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDBHelper extends SQLiteOpenHelper {


	public static final String DB_NAME = "user.db";
	//public static final String TABLE_NAME = "userInfo";

	public MyDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	public MyDBHelper(Context context) {
		super(context,DB_NAME,null,1);
	}

	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase) {
		//String sql = "create table "+TABLE_NAME+"(name varchar(20),phone varchar(20))";
		sqLiteDatabase.execSQL("CREATE TABLE userInfo(_id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR(20),phone VARCHAR(20))");

	}

	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

	}
}
