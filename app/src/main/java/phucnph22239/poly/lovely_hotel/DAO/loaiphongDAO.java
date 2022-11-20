package phucnph22239.poly.lovely_hotel.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import phucnph22239.poly.lovely_hotel.DTO.Phong;
import phucnph22239.poly.lovely_hotel.DTO.loaiphong;
import phucnph22239.poly.lovely_hotel.Database.DbHelper;

public class loaiphongDAO {
    private DbHelper dbHelper;
    private SQLiteDatabase db;

    public loaiphongDAO(Context context){
        dbHelper=new DbHelper(context);
        db=dbHelper.getWritableDatabase();
    }
    public long insert(loaiphong loaiphong){
        ContentValues values=new ContentValues();
        values.put("name",loaiphong.getName());
        return db.insert("Room_Types",null,values);
    }
    public List<loaiphong> getDaTa(String sql, String...selectionArgs){
        List<loaiphong> list=new ArrayList<>();
        Cursor c=db.rawQuery(sql,selectionArgs);
        if (c.getCount() > 0) {
            c.moveToFirst();
            while (!c.isAfterLast()) {
                int a = c.getInt(0);
                String b = c.getString(1);
                list.add(new loaiphong(a,b));
                c.moveToNext();
            }
            c.close();
        }
        return list;
    }
    public List<loaiphong> getAll(){
        String sql="select * from Room_Types";
        return getDaTa(sql);
    }
}
