package com.example.sqlitetest;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText name;
    private EditText phone;
    private Button add;
    private Button query;
    private Button delete;
    private TextView show;
    private Button update;
    private ContentResolver resolver;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        resolver=getContentResolver();
        uri = Uri.parse("content://com.example.sqlitedb/info");
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
        ContentValues values;
        switch (v.getId()) {
            case R.id.add:
                String name1=name.getText().toString();
                String phone1=phone.getText().toString();
                values=new ContentValues();
                values.put("name",name1);
                values.put("phone",phone1);
                Uri insertUri=resolver.insert(uri,values);
                if(insertUri.equals(null)){
                    show.setText("添加失败");
                }else{
                    Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.query:
                Cursor cursor=resolver.query(uri,null,null,null,null);
                if(cursor.getCount()==0) {
                    show.setText("查询失败");
                }else {
                    show.setText("查询结果：" + "\n");
                    while (cursor.moveToNext()) {
                        show.append("\n" + "Name : " + cursor.getString(1) + " ; Phone : " + cursor.getString(2));
                    }
                }
                    break;

                    case R.id.update:
                        values=new ContentValues();
                        values.put("name",name1=name.getText().toString());
                        values.put("phone",phone1=phone.getText().toString());
                        int j = resolver.update(uri,values,"name=?",new String[]{name.getText().toString()});
                        if(j>0) {
                            Toast.makeText(this, "信息已修改", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(this, "修改失败", Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case R.id.delete:
                        int i = resolver.delete(uri, null, null);
                        if(i>0) {
                            Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(this, "删除失败", Toast.LENGTH_SHORT).show();
                        }
                        show.setText("删除" + String.valueOf(i) + "条");
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
            Toast.makeText(this, "Phone", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
