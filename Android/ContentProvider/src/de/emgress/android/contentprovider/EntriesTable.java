package de.emgress.android.contentprovider;

import android.database.sqlite.SQLiteDatabase;

public class EntriesTable 
{
	public static final String TABLE_NAME = "entries";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_VALUE = "value";
	
	public static final String CREATE_TABLE = 
			"CREATE TABLE " + TABLE_NAME + "( " + 
			COLUMN_ID + " integer primary key autoincrement, " +
			COLUMN_VALUE + " text not null );";
		
	public static void onCreate( SQLiteDatabase db )
	{
		db.execSQL( CREATE_TABLE );
	}
	
	public static void onUpdate( SQLiteDatabase db, int oldVersion, int newVersion )
	{
		db.execSQL( "DROP TABLE IF EXISTS " + TABLE_NAME );
		onCreate( db );
	}
	
}
