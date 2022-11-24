package phucnph22239.poly.lovely_hotel.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import phucnph22239.poly.lovely_hotel.DTO.HoaDon;
import phucnph22239.poly.lovely_hotel.DTO.HoaDonDichVu;
import phucnph22239.poly.lovely_hotel.Database.DbHelper;

public class HoaDonDichVuDAO {
    private SQLiteDatabase db;

    public HoaDonDichVuDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(HoaDonDichVu hoaDonDichVu){
        ContentValues values = new ContentValues();
        values.put("bill_id",hoaDonDichVu.getBill_id());
        values.put("service_id",hoaDonDichVu.getService_id());
        values.put("service_quantity",hoaDonDichVu.getService_quantity());
        values.put("service_date",String.valueOf(hoaDonDichVu.getService_date()));
        values.put("total",hoaDonDichVu.getTotal());
        return db.insert("Service_bills",null,values);
    }

    public long update(HoaDonDichVu hoaDonDichVu){
        ContentValues values = new ContentValues();
        values.put("bill_id",hoaDonDichVu.getBill_id());
        values.put("service_id",hoaDonDichVu.getService_id());
        values.put("service_quantity",hoaDonDichVu.getService_quantity());
        values.put("service_date",String.valueOf(hoaDonDichVu.getService_date()));
        values.put("total",hoaDonDichVu.getTotal());
        return db.update("Service_bills",values,"id=?",new String[]{String.valueOf(hoaDonDichVu.getId())});
    }

    public List<HoaDonDichVu> getAll(){
        String sql = "SELECT * FROM Service_bills";
        return getData(sql);
    }
    public List<HoaDonDichVu> getId(){
        String sql = "SELECT * FROM Service_bills WHERE id=?";
        return getData(sql);
    }

    @SuppressLint("Range")
    private List<HoaDonDichVu> getData(String sql, String...selectionArgs) {

        List<HoaDonDichVu> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            HoaDonDichVu hoaDonDichVu = new HoaDonDichVu();
            hoaDonDichVu.setId(Integer.parseInt(c.getString(c.getColumnIndex("id"))));
            hoaDonDichVu.setBill_id(Integer.parseInt(c.getString(c.getColumnIndex("bill_id"))));
            hoaDonDichVu.setService_id(Integer.parseInt(c.getString(c.getColumnIndex("service_id"))));
            hoaDonDichVu.setService_quantity(Integer.parseInt(c.getString(c.getColumnIndex("service_quantity"))));
            hoaDonDichVu.setService_date(c.getString(c.getColumnIndex("service_date")));
            hoaDonDichVu.setTotal(Integer.parseInt(c.getString(c.getColumnIndex("total"))));
            list.add(hoaDonDichVu);
        }
        return list;

    }

}
