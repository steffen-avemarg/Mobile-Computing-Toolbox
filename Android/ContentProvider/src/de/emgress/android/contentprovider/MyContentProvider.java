package de.emgress.android.contentprovider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {

	private MySQLHelper database;
	
	private static final int ENTRIES = 10;
	private static final int ENTRY_ID = 20;
	
	private static final String AUTHORITY = "de.fhe.mycontentprovider";
	private static final String BASE_PATH = "entries";
	
	public static final Uri CONTENT_URI = Uri.parse( "content://" +
			AUTHORITY + "/" + BASE_PATH );
	
	public static final String CONTENT_TYPE = 
			ContentResolver.CURSOR_DIR_BASE_TYPE + "entries";
	public static final String CONTENT_ITEM_TYPE = 
			ContentResolver.CURSOR_ITEM_BASE_TYPE + "entry";
	
	private static final UriMatcher matcher = new UriMatcher( UriMatcher.NO_MATCH );
	static {
		matcher.addURI( AUTHORITY, BASE_PATH, ENTRIES );
		matcher.addURI( AUTHORITY, BASE_PATH + "/#", ENTRY_ID );
	}
	
	@Override
	public boolean onCreate() {
		this.database = new MySQLHelper( getContext() );
		return false;
	}
	
	@Override
	public String getType(Uri uri) {	
		return null; // Data MIME-Type
	}
	
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
		
		// Pr�fen, ob die Projektion sinnvoll ist!
		
		queryBuilder.setTables( EntriesTable.TABLE_NAME );
		int uriType = matcher.match( uri );
		
		switch( uriType )
		{
		case ENTRIES:
			break;
		case ENTRY_ID:
			queryBuilder.appendWhere( EntriesTable.COLUMN_ID + "=" + uri.getLastPathSegment() );
			break;
		default:
			throw new IllegalArgumentException( "Unknown URI: " + uri );
		}
		
		SQLiteDatabase db = database.getWritableDatabase();
		
		Cursor cursor = queryBuilder.query(db, projection, selection, 
				selectionArgs, null, null, sortOrder);
		cursor.setNotificationUri( getContext().getContentResolver(), uri );
		
		return cursor;
	}


	
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		int uriType = matcher.match( uri );
		SQLiteDatabase db = database.getWritableDatabase();
		
		long id = 0;
		
		switch ( uriType )
		{
		case ENTRIES:
			id = db.insert( EntriesTable.TABLE_NAME, null, values );
			break;
		default:
			throw new IllegalArgumentException( "Unknown URI: " + uri );
		}
		
		getContext().getContentResolver().notifyChange( uri, null );
		
		return Uri.parse( BASE_PATH + "/" + id );
	}
	
	
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		int uriType = matcher.match( uri );
		SQLiteDatabase db = database.getWritableDatabase();
		
		int rowsDeleted = 0;
		
		switch ( uriType )
		{
		case ENTRIES:
			rowsDeleted = db.delete( EntriesTable.TABLE_NAME, selection, selectionArgs );
			break;
		case ENTRY_ID:
			String id = uri.getLastPathSegment();
			rowsDeleted = db.delete( EntriesTable.TABLE_NAME, 
					EntriesTable.COLUMN_ID + "=" + id, null );
			break;
		default:
			throw new IllegalArgumentException( "Unknown URI: " + uri );
		}
		
		getContext().getContentResolver().notifyChange( uri, null );
		
		return rowsDeleted;
	}
	
	
	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		int uriType = matcher.match( uri );
		SQLiteDatabase db = database.getWritableDatabase();
		
		int rowsUpdated = 0;
		
		switch ( uriType )
		{
		case ENTRIES:
			rowsUpdated = db.update( EntriesTable.TABLE_NAME, values, selection, selectionArgs );
			break;
		case ENTRY_ID:
			String id = uri.getLastPathSegment();
			rowsUpdated = db.update( EntriesTable.TABLE_NAME, values, 
					EntriesTable.COLUMN_ID + "=" + id, null );
		default:
			throw new IllegalArgumentException( "Unknown URI: " + uri );
		}
		
		getContext().getContentResolver().notifyChange( uri, null );
		
		return rowsUpdated;
	}
}
