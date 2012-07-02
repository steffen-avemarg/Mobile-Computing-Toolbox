package de.emgress.android.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class EntriesDao 
{
	public static final String TABLE_NAME = "entries";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_VALUE = "value";
	
	public static final String CREATE_TABLE = 
			"CREATE TABLE " + TABLE_NAME + "( " + 
			COLUMN_ID + " integer primary key autoincrement, " +
			COLUMN_VALUE + " text not null );";
	
	private SQLiteDatabase database;
	private MySQLHelper sqlHelper;
	private String[] allColumns = { COLUMN_ID, COLUMN_VALUE };
	
	public EntriesDao( Context cxt )
	{
		this.sqlHelper = new MySQLHelper( cxt );
	}
	
	public static void onCreate( SQLiteDatabase db )
	{
		db.execSQL( CREATE_TABLE );
	}
	
	public static void onUpdate( SQLiteDatabase db, int oldVersion, int newVersion )
	{
		db.execSQL( "DROP TABLE IF EXISTS " + TABLE_NAME );
		onCreate( db );
	}
	
	public void open() throws SQLException
	{
		this.database = this.sqlHelper.getWritableDatabase();
	}
	
	public void close()
	{
		this.sqlHelper.close();
	}

	public long addEntry( String value )
	{
		ContentValues values = new ContentValues();
		values.put( COLUMN_VALUE, value );
		long entryId = this.database.insert( TABLE_NAME, null, values );
		
		return entryId;
	}
	
	public void removeEntry( long entryId )
	{
		database.delete( TABLE_NAME, COLUMN_ID + " = " + entryId, null);
	}
	
	public Cursor getAllEntries() 
	{
		Cursor result = database.query( TABLE_NAME, this.allColumns, 
				null, null, null, null, null);
		
		return result;
	}
}
