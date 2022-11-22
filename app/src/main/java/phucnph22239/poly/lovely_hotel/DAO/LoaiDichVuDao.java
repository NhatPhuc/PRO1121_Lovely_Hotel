package phucnph22239.poly.lovely_hotel.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import phucnph22239.poly.lovely_hotel.DTO.LoaiDichVu;
import phucnph22239.poly.lovely_hotel.Database.DbHelper;

public class LoaiDichVuDao {
    private DbHelper dbHelper;
    private SQLiteDatabase db;

    public LoaiDichVuDao(Context context){
        dbHelper=new DbHelper(context);
        db=dbHelper.getWritableDatabase();
    }
    public long insert(LoaiDichVu loaiDichVu){
        ContentValues values=new ContentValues();
        values.put("name",loaiDichVu.getName());
        values.put("price",loaiDichVu.getPrice());
        return db.insert("Services",null,values);
    }
    public List<LoaiDichVu> getDaTa(String sql, String...selectionArgs){
        List<LoaiDichVu> list=new ArrayList<>();
        Cursor c=db.rawQuery(sql,selectionArgs);
        if (c.getCount() > 0) {
            c.moveToFirst();
            while (!c.isAfterLast()) {
                int a = c.getInt(0);
                String b = c.getString(1);
                int price = c.getInt(2);
                list.add(new LoaiDichVu(a,b,price));
                c.moveToNext();
            }
            c.close();
        }
        return list;
    }
    public List<LoaiDichVu> getAll(){
        String sql="select * from Services";
        return getDaTa(sql);
    }

    //DÙng hàm để lấy cái tham chiếu ra các bố ,:)))
    public LoaiDichVu getID(String id){
        String sql="select * from Services where id=?";
        List<LoaiDichVu> list = getDaTa(sql,id);
        return list.get(0);
    }
}