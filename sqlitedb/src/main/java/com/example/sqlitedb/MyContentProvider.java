package com.example.sqlitedb;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {
    private MyDBHelper dbHelper;
    private  static UriMatcher uriMatcher=new UriMatcher(-1);
    private static final int SUCCESS=1;
    static {
        uriMatcher.addURI("com.example.sqlitedb","info",SUCCESS);
    }

    @Override
    public boolean onCreate() {
        dbHelper=new MyDBHelper(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        if(uriMatcher.match(uri)==SUCCESS){
            SQLiteDatabase db=dbHelper.getReadableDatabase();
            Cursor cursor=db.query("info",null,null,null,null,null,null);
            return cursor;
        }else {
            // TODO: Implement this to handle query requests from clients.
            throw new IllegalArgumentException("路径不正确，无法查询数据！");
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if(uriMatcher.match(uri)==SUCCESS){
            SQLiteDatabase db=dbHelper.getReadableDatabase();
            long rowID=db.insert("info",null,values);
            if(rowID>0){
                Uri insertedUri= ContentUris.withAppendedId(uri,rowID);
                getContext().getContentResolver().notifyChange(insertedUri,null);
                return insertedUri;
            }
            db.close();
            return uri;
        }else {
            // TODO: Implement this to handle requests to insert a new row.
            throw new IllegalArgumentException("路径不正确，无法插入数据！ ");
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        if(uriMatcher.match(uri)==SUCCESS){
            SQLiteDatabase db=dbHelper.getWritableDatabase();
            int count=db.delete("info",selection,selectionArgs);
            if(count>0){
                getContext().getContentResolver().notifyChange(uri,null);
            }
            db.close();
            return count;
        }else {
            // Implement this to handle requests to delete one or more rows.
            throw new IllegalArgumentException("路径不正确，无法随便删除数据！");
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        if (uriMatcher.match(uri) == SUCCESS) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            int count = db.update("info",values, selection, selectionArgs);
            if (count > 0) {
                getContext().getContentResolver().notifyChange(uri, null);
            }
            db.close();
            return count;
        } else {
            // Implement this to handle requests to delete one or more rows.
            throw new IllegalArgumentException("路径不正确，无法随便删除数据！");
        }
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

}
