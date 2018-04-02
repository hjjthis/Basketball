package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/12/21.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final String CREATE_PLAYER = "create table Player ("
            + "id integer primary key autoincrement, "
            + "num integer, "
            + "name text, "
            + "score integer,"
            + "zhugong integer, "
            + "lanban integer, "
            + "qiangduan integer, "
            + "gaimao integer, "
            + "fangui integer, "
            + "isTeamA integer)";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PLAYER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {



    }
}
