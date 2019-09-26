package com.example.sqlitedb;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    MyDBHelper myDBHelper;
    private EditText name;
    private EditText phone;
    private Button add;
    private Button query;
    private Button delete;
    private TextView show;
    private Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        myDBHelper = new MyDBHelper(this);
    }

    private void initView() {
        name = (EditText) findViewById(R.id.name);
        phone = (EditText) findViewById(R.id.phone);
        add = (Button) findViewById(R.id.add);
        query = (Button) findViewById(R.id.query);
        delete = (Button) findViewById(R.id.delete);

        add.setOnClickListener(this);
        query.setOnClickListener(this);
        delete.setOnClickListener(this);
        show = (TextView) findViewById(R.id.show);
        show.setOnClickListener(this);
        update = (Button) findViewById(R.id.update);
        update.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String name1, phone1;
        ContentValues values;
        SQLiteDatabase db;
        switch (v.getId()) {
            case R.id.add:
                name1 = name.getText().toString();
                phone1 = phone.getText().toString();
                db = myDBHelper.getWritableDatabase();
                values = new ContentValues();
                values.put("name", name1);
                values.put("phone", phone1);
                db.insert("info", null, values);
                Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
                db.close();
                break;

            case R.id.query:
                db = myDBHelper.getReadableDatabase();
                Cursor cursor = db.query("info", null, null, null, null, null, null);
                if (cursor.getCount() == 0) {
                    Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
                } else {
                    cursor.moveToFirst();
                    show.setText("Name : " + cursor.getString(1) + " ; Phone : " + cursor.getString(2));
                }
                while (cursor.moveToNext()) {
                    show.append("\n" + "Name : " + cursor.getString(1) + " ; Phone : " + cursor.getString(2));
                }
                cursor.close();
                db.close();
                break;

            case R.id.update:
                db = myDBHelper.getWritableDatabase();
                values=new ContentValues();
                values.put("name",name1=name.getText().toString());
                values.put("phone",phone1=phone.getText().toString());
                db.update("info",values,"name=?",new String[]{name.getText().toString()});
                Toast.makeText(this,"信息修改成功",Toast.LENGTH_SHORT).show();
                db.close();
                break;

            case R.id.delete:
                db=myDBHelper.getWritableDatabase();
                db.delete("info",null,null);
                Toast.makeText(this,"信息删除成功",Toast.LENGTH_SHORT).show();
                show.setText("");
                db.close();
                break;

        }
    }

    private void submit() {
        // validate
        String nameString = name.getText().toString().trim();
        if (TextUtils.isEmpty(nameString)) {
            Toast.makeText(this, "Name", Toast.LENGTH_SHORT).show();
            return;
        }

        String passwordString = phone.getText().toString().trim();
        if (TextUtils.isEmpty(passwordString)) {
            Toast.makeText(this, "Password", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
