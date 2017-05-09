package liwei.com.searchtest.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by wu  suo  wei on 2017/5/7.
 * sqliteOpenHelper数据库
 */

public class MainSqlite extends SQLiteOpenHelper {
    public MainSqlite(Context context) {
        super(context, "search.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table search(_id integer primary key autoincrement,name varchar(20),Id integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
