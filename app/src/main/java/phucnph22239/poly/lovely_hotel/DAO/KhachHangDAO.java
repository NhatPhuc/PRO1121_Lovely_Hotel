package phucnph22239.poly.lovely_hotel.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import phucnph22239.poly.lovely_hotel.DTO.KhachHang;
import phucnph22239.poly.lovely_hotel.Database.DbHelper;

public class KhachHangDAO {
    DbHelper dbHelper ;
    SQLiteDatabase db ;
    public KhachHangDAO(Context context){
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public long insert(KhachHang khachHang){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",khachHang.getName());
        contentValues.put("phone_number",khachHang.getPhone());
        contentValues.put("birthday",khachHang.getBirthday());
        long res = db.insert("Guests",null,contentValues);
        return res ;
    }
    public ArrayList<KhachHang> getAll(){
        ArrayList<KhachHang> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM Guests",null);
        if(cursor.getCount()!=0){
            cursor.moveToFirst();
            do {
                list.add(new KhachHang(cursor.getInt(0),cursor.getString(1),
                        cursor.getInt(2),cursor.getString(3)));
            }while (cursor.moveToNext());
        }
        return list;
    }
}
