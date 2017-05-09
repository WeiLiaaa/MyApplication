package liwei.com.searchtest.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by wu  suo  wei on 2017/5/7.
 */

public class ControllerDao {
    private Context context;


    public ControllerDao(Context context) {
        // TODO Auto-generated constructor stub
        this.context=context;
    }

    /**
     * 添加
     */
    public void insert(String name) {
        //实例化数据库sqliteopenhelper
        MainSqlite sqlite = new MainSqlite(context);
        //创建可读可写的数据库
        SQLiteDatabase db = sqlite.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name", name);
        db.insert("search", null, values);
        db.close();
    }

    /**
     * 修改
     */
    public void update(String name,int Id) {
        MainSqlite sqlite = new MainSqlite(context);
        //创建可读可写的数据库
        SQLiteDatabase db = sqlite.getWritableDatabase();

        ContentValues values=new ContentValues();
        //values是要修改的内容
        //第二个参数是修改的条件
        //第三个参数是对应的传入参数
        /*values.put("name", name);
        db.close();*/
    }

    /**
     * 删除
     */
    public void delete(String name) {
        MainSqlite sqlite = new MainSqlite(context);
        //创建可读可写的数据库
        SQLiteDatabase db = sqlite.getWritableDatabase();
        //按条件删除
        db.delete("search", "name=?", new String [] {name});
        db.close();

    }

    /**
     * 查询
     */
    public ArrayList<Bean> query() {
        MainSqlite sqlite = new MainSqlite(context);
        ArrayList<Bean> list=new ArrayList<>();

        //注意查询中不能关闭数据库否则报空指针异常

        //创建可读可写的数据库
        SQLiteDatabase db = sqlite.getWritableDatabase();

        Cursor cursor = db.query("search", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Bean bean=new Bean();
            String name = cursor.getString(cursor.getColumnIndex("name"));
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            bean.setName(name);
            bean.setId(id);
            list.add(bean);
        }
        return list;
    }
}
