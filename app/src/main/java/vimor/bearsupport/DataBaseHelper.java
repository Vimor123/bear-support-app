package vimor.bearsupport;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String ENTRY_TABLE = "ENTRY_TABLE";
    public static final String ENTRY_COLUMN_ID = "ID";
    public static final String ENTRY_COLUMN_TITLE = "TITLE";
    public static final String ENTRY_COLUMN_DATE = "DATE";
    public static final String ENTRY_COLUMN_ENTRY_TEXT = "ENTRY_TEXT";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "bear_support.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + ENTRY_TABLE + " (" + ENTRY_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ENTRY_COLUMN_TITLE + " TEXT, " +
                ENTRY_COLUMN_DATE + " TEXT, " +
                ENTRY_COLUMN_ENTRY_TEXT + " TEXT)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addEntry(EntryModel entryModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(ENTRY_COLUMN_TITLE, entryModel.getTitle());
        cv.put(ENTRY_COLUMN_DATE, entryModel.getDate().toString());
        cv.put(ENTRY_COLUMN_ENTRY_TEXT, entryModel.getEntry());

        long insert = db.insert(ENTRY_TABLE, null, cv);

        if (insert == -1) {
            return false;
        }
        return true;
    }

    public List<EntryModel> getAllEntries() {
        List<EntryModel> entries = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String queryString = "SELECT * FROM " + ENTRY_TABLE + " ORDER BY " + ENTRY_COLUMN_DATE + " DESC";

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                int entryID = cursor.getInt(0);
                String title = cursor.getString(1);
                String dateString = cursor.getString(2);
                String entryText = cursor.getString(3);

                String[] segments = dateString.split("-");
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    LocalDate date = LocalDate.of(Integer.parseInt(segments[0]), Integer.parseInt(segments[1]), Integer.parseInt(segments[2]));
                    EntryModel entryModel = new EntryModel(entryID, title, date, entryText);
                    entries.add(entryModel);
                } else {
                    System.out.println("SDK problem");
                }
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return entries;
    }

    public boolean editEntry(EntryModel entryModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(ENTRY_COLUMN_TITLE, entryModel.getTitle());
        cv.put(ENTRY_COLUMN_DATE, entryModel.getDate().toString());
        cv.put(ENTRY_COLUMN_ENTRY_TEXT, entryModel.getEntry());

        long update = db.update(ENTRY_TABLE, cv, ENTRY_COLUMN_ID + " = " + entryModel.getId(), null);

        return true;
    }
}
