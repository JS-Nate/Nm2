package com.example.noteme2;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
public class NoteDatabase extends SQLiteOpenHelper {
    public static final int DB_VERSION = 2;
    // Database columns
    public static String DB_NAME = "NotesDB.db";
    public static String DB_TABLE = "NotesTable";
    public static String COLUMN_ID = "NotesId";
    public static String COLUMN_TITLE = "NotesTitle";
    public static String COLUMN_DETAILS = "NotesDetails";
    public static String COLUMN_COLOR = "NotesColor";
    public NoteDatabase(@Nullable Context context) {super(context, DB_NAME, null, DB_VERSION); }

    // Define database table
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + DB_TABLE +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT," +
                COLUMN_DETAILS + " TEXT," +
                COLUMN_COLOR + " TEXT" + ")";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if(oldVersion >= newVersion)
            return;
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_NAME);
        onCreate(sqLiteDatabase);
    }

    // Adds entries in the table
    public long AddNote(ModelNote noteModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TITLE, noteModel.getNoteTitle());
        contentValues.put(COLUMN_DETAILS, noteModel.getNoteDescription());
        contentValues.put(COLUMN_COLOR, noteModel.getNoteColor());
        long ID = db.insert(DB_TABLE, null, contentValues);
        Log.d("Inserted", "id ->" + ID);
        return ID;
    }

    // Returns a list of entries in the database to display on the main page
    public List<ModelNote> getNote(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<ModelNote> allNote = new ArrayList<>();
        String queryStatement = "SELECT * FROM " + DB_TABLE;
        Cursor cursor = db.rawQuery(queryStatement, null);
        if(cursor.moveToFirst()){
            do{
                ModelNote noteModel = new ModelNote();
                noteModel.setId(cursor.getInt(0));
                noteModel.setNoteTitle(cursor.getString(1));
                noteModel.setNoteDescription(cursor.getString(2));
                noteModel.setNoteColor(cursor.getString(3));
                allNote.add(noteModel);
            }while(cursor.moveToNext());
        }
        return allNote;
    }

    // to get info of a specific note based on its ID
    public ModelNote getNote(int ID){
        SQLiteDatabase db = this.getReadableDatabase();
        String [] query = new String[] {COLUMN_ID, COLUMN_TITLE, COLUMN_DETAILS, COLUMN_COLOR, COLUMN_TITLE};
        Cursor cursor = db.query(DB_TABLE, query, COLUMN_ID+"=?", new String[]{String.valueOf(ID)}, null, null, null, null);
        if(cursor != null)
            cursor.moveToFirst();
        return new ModelNote(
                Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3));
    }

    // To delete a note from the database and main page
    void deleteNote(int ID){
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(DB_TABLE, COLUMN_ID+"=?", new String[]{String.valueOf(ID)});
        db.close();
    }
}
