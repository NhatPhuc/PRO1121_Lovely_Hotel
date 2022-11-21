package phucnph22239.poly.lovely_hotel.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import phucnph22239.poly.lovely_hotel.DTO.KhachHang;
import phucnph22239.poly.lovely_hotel.DTO.LoaiPhong;
import phucnph22239.poly.lovely_hotel.Database.DbHelper;

public class LoaiPhongDAO {
    private DbHelper dbHelper;
    private SQLiteDatabase db;

    public LoaiPhongDAO(Context context){
        dbHelper=new DbHelper(context);
        db=dbHelper.getWritableDatabase();
    }
    public long insert(LoaiPhong loaiphong){
        ContentValues values=new ContentValues();
        values.put("name",loaiphong.getName());
        return db.insert("Room_Types",null,values);
    }
    public List<LoaiPhong> getDaTa(String sql, String...selectionArgs){
        List<LoaiPhong> list=new ArrayList<>();
        Cursor c=db.rawQuery(sql,selectionArgs);
        if (c.getCount() > 0) {
            c.moveToFirst();
            while (!c.isAfterLast()) {
                int a = c.getInt(0);
                String b = c.getString(1);
                list.add(new LoaiPhong(a,b));
                c.moveToNext();
            }
            c.close();
        }
        return list;
    }
    public List<LoaiPhong> getAll(){
        String sql="select * from Room_Types";
        return getDaTa(sql);
    }

<<<<<<< HEAD:app/src/main/java/phucnph22239/poly/lovely_hotel/DAO/loaiphongDAO.java
<<<<<<< HEAD:app/src/main/java/phucnph22239/poly/lovely_hotel/DAO/loaiphongDAO.java
    //DÙng hàm để lấy cái tham chiếu ra các bố ,:)))
    public loaiphong getID(String id){
        String sql="select * from Room_Types where id=?";
        List<loaiphong> list = getDaTa(sql,id);
        return list.get(0);
    }
=======
=======
>>>>>>> f93e727fd84c26c3b1208091d9024c8a718a2136:app/src/main/java/phucnph22239/poly/lovely_hotel/DAO/LoaiPhongDAO.java
    public LoaiPhong getID(String id){
        String sql = "SELECT * FROM Room_Types WHERE id=?";
        List<LoaiPhong> list = getDaTa(sql,id);
        return list.get(0);
    }

<<<<<<< HEAD:app/src/main/java/phucnph22239/poly/lovely_hotel/DAO/loaiphongDAO.java
>>>>>>> f93e727fd84c26c3b1208091d9024c8a718a2136:app/src/main/java/phucnph22239/poly/lovely_hotel/DAO/LoaiPhongDAO.java
=======
>>>>>>> f93e727fd84c26c3b1208091d9024c8a718a2136:app/src/main/java/phucnph22239/poly/lovely_hotel/DAO/LoaiPhongDAO.java
}
