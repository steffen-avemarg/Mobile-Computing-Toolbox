package de.emgress.android.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLHelper extends SQLiteOpenHelper 
{
	private static final String DATABASE_NAME = "myDatabase.db";
	private static final int DATABASE_VERSION = 1;
	
	public MySQLHelper(Context context) 
	{
		super( context, DATABASE_NAME, null, DATABASE_VERSION );
	}

	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		EntriesDao.onCreate( db );
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{
		EntriesDao.onUpdate( db, oldVersion, newVersion );
	}

}
