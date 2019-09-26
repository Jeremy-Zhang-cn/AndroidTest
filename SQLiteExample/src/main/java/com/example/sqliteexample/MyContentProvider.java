package com.example.sqliteexample;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyContentProvider extends ContentProvider {

	private MyDBHelper myDBHelper;
	private static UriMatcher uriMatcher = new UriMatcher(-1);
	private static final int SUCCESS = 1;

	static {
		uriMatcher.addURI("com.example.sqliteexample/userInfo", "info", SUCCESS);
	}

	@Override
	public boolean onCreate() {

		myDBHelper = new MyDBHelper(getContext());
		return true;

	}

	@Nullable
	@Override
	public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
						@Nullable String[] selectionArgs, @Nullable String sortOrder) {

		if(uriMatcher.match(uri) == SUCCESS) {

			SQLiteDatabase db = myDBHelper.getReadableDatabase();
			Cursor cursor = db.query("info", null, null, null,
					null, null, null);
		} else {
			throw new IllegalArgumentException("无法查询到数据");
		}


		return null;
	}

	@Nullable
	@Override
	public String getType(@NonNull Uri uri) {
		return null;
	}

	@Nullable
	@Override
	public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

		if(uriMatcher.match(uri) == SUCCESS) {
			SQLiteDatabase db = myDBHelper.getWritableDatabase();
			long rowID = db.insert("info", null, values);
			if(rowID > 0) {
				Uri insertedUri = ContentUris.withAppendedId(uri, rowID);
				getContext().getContentResolver().notifyChange(insertedUri, null);
				//return insertedUri;
			}
			db.close();
			return uri;
		} else {
			throw new IllegalArgumentException("无法插入");
		}


	}

	@Override
	public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {

		if (uriMatcher.match(uri) == SUCCESS) {
			SQLiteDatabase db = myDBHelper.getWritableDatabase();
			int count = db.delete("info", selection, selectionArgs);
			if (count > 0) {
				getContext().getContentResolver().notifyChange(uri,null);
			}
			db.close();
			return count;
		} else {
			throw new IllegalArgumentException("删除失败");
		}
	}

	@Override
	public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {

		if(uriMatcher.match(uri) == SUCCESS) {
			SQLiteDatabase db = myDBHelper.getWritableDatabase();
			int count = db.update("info", values, selection, selectionArgs);

			if (count > 0) {
				getContext().getContentResolver().notifyChange(uri,null);
			}
			db.close();
			return count;
		} else {
			throw new IllegalArgumentException("更改失败");
		}

	}
}
