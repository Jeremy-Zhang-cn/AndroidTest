package com.example.sqliteexample;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


	public static final String DB_NAME = "user.db";
	public static final String TABLE_NAME = "userInfo";
	MyDBHelper myDBHelper;

	private TextView mTvShow;
	private EditText et_name,et_phone;
	private Button add,query,modify,delete;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		


		myDBHelper = new MyDBHelper(this);
		mTvShow = findViewById(R.id.tv_Show);
		et_name = findViewById(R.id.name);
		et_phone = findViewById(R.id.phone);
		add = findViewById(R.id.btn_add);
		query = findViewById(R.id.btn_query);
		modify = findViewById(R.id.btn_modify);
		delete = findViewById(R.id.btn_delete);

		add.setOnClickListener(this);
		query.setOnClickListener(this);
		modify.setOnClickListener(this);
		delete.setOnClickListener(this);


	}

	@Override
	public void onClick(View view) {
		String name,phone;
		SQLiteDatabase db;
		ContentValues values;

		switch (view.getId()) {

			case R.id.btn_add:	//添加
				name = et_name.getText().toString();
				phone = et_phone.getText().toString();
				db = myDBHelper.getWritableDatabase();	//获取可读写的SQLiteDatabase对象
				values = new ContentValues();	//创建ContentValues对象
				values.put("name",name);
				values.put("phone",phone);	//将数据添加至ContentValues对象
				db.insert("userInfo", null, values);
				Toast.makeText(MainActivity.this,"信息已添加",Toast.LENGTH_SHORT).show();
				db.close();
				break;

			case R.id.btn_query:	//查询
				db = myDBHelper.getReadableDatabase();
				Cursor cursor = db.query("userInfo", null, null, null, null,
						null, null);
				if(cursor.getCount() == 0) {
					mTvShow.setText("");
					Toast.makeText(MainActivity.this,"没有数据",Toast.LENGTH_SHORT).show();
				} else {
					cursor.moveToFirst();
					mTvShow.setText("Name: "+cursor.getString(1)+" ; " +
							"Tel: "+cursor.getString(2));
				}
				while (cursor.moveToNext()) {
					mTvShow.append("\n"+"name:"+cursor.getString(1)+"; Tel: " +cursor.getString(2));
				}
				cursor.close();
				db.close();
				break;

			case R.id.btn_modify:	//修改
				db = myDBHelper.getWritableDatabase();
				values = new ContentValues();
				values.put("phone", phone = et_phone.getText().toString());
				db.update("userInfo",values,"name=?",new String[] {et_name.getText().toString()});
				Toast.makeText(MainActivity.this,"信息已修改",Toast.LENGTH_SHORT).show();
				db.close();
				break;

			case  R.id.btn_delete:	//删除
				db = myDBHelper.getWritableDatabase();
				try {
					db.delete("userInfo",null,null);
					Toast.makeText(MainActivity.this,"信息已删除",Toast.LENGTH_SHORT).show();
				} catch (Exception e) {
					e.printStackTrace();
					Toast.makeText(MainActivity.this,"无数据",Toast.LENGTH_SHORT).show();
				}

				mTvShow.setText("");
				db.close();
				break;

		}
	}

}
