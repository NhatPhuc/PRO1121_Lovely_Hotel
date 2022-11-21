package phucnph22239.poly.lovely_hotel.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import phucnph22239.poly.lovely_hotel.DTO.Phong;
import phucnph22239.poly.lovely_hotel.Database.DbHelper;

public class PhongDao {
    private DbHelper dbHelper;
    private SQLiteDatabase db;
    public PhongDao(Context context) {
        dbHelper=new DbHelper(context);
        db=dbHelper.getWritableDatabase();
    }
    public long insert(Phong phong){
        ContentValues values=new ContentValues();
        values.put("name",phong.getName());
        values.put("room_type_id",phong.getRoom_type_id());
        values.put("price",phong.getPrice());
        values.put("status",phong.getStatus());
        return db.insert("Rooms",null,values);
    }





    public List<Phong> getDaTa(String sql, String...selectionArgs){
        List<Phong> list=new ArrayList<>();
        Cursor c=db.rawQuery(sql,selectionArgs);
        if (c.getCount() > 0) {
            c.moveToFirst();
            while (!c.isAfterLast()) {
                int a = c.getInt(0);
                int d= c.getInt(1);
                String b = c.getString(2);
                int g=c.getInt(3);
                int p = c.getInt(4);
                list.add(new Phong(a,b,d,g,p));
                c.moveToNext();
            }
            c.close();
        }
        return list;
    }
    public List<Phong> getAll(){
        String sql="select * from Rooms";
        return getDaTa(sql);
    }
    public Phong getID(String id){
        String sql="select * from Rooms where id=?";
        List<Phong> list=getDaTa(sql,id);
        return list.get(0);
    }






}