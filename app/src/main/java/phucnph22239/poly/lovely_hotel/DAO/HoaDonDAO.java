package phucnph22239.poly.lovely_hotel.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import phucnph22239.poly.lovely_hotel.DTO.HoaDon;
import phucnph22239.poly.lovely_hotel.DTO.Phong;
import phucnph22239.poly.lovely_hotel.Database.DbHelper;

public class HoaDonDAO {
    private SQLiteDatabase db;

    public HoaDonDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(HoaDon hoaDon){
        ContentValues values = new ContentValues();
        values.put("manager_id",hoaDon.getManager_id());
        values.put("guest_id",hoaDon.getGuest_id());
        values.put("room_id",hoaDon.getRoom_id());
        values.put("start_date",String.valueOf(hoaDon.getStart_date()));
        values.put("end_date",String.valueOf(hoaDon.getEnd_date()));
        values.put("status",hoaDon.getStatus());
        values.put("note",hoaDon.getNote());
        values.put("room_total",hoaDon.getRoom_total());
        values.put("bill_date",String.valueOf(hoaDon.getBill_date()));
        values.put("lost_total",hoaDon.getLost_total());
        values.put("service_total",hoaDon.getService_total());
        values.put("bill_total",hoaDon.getBill_total());
        return db.insert("Bills",null,values);
    }

    public long update(HoaDon hoaDon){
        ContentValues values = new ContentValues();
        values.put("manager_id",hoaDon.getManager_id());
        values.put("guest_id",hoaDon.getGuest_id());
        values.put("room_id",hoaDon.getRoom_id());
        values.put("start_date",String.valueOf(hoaDon.getStart_date()));
        values.put("end_date",String.valueOf(hoaDon.getEnd_date()));
        values.put("status",hoaDon.getStatus());
        values.put("note",hoaDon.getNote());
        values.put("bill_date",String.valueOf(hoaDon.getBill_date()));
        values.put("room_total",hoaDon.getRoom_total());
        values.put("lost_total",hoaDon.getLost_total());
        values.put("service_total",hoaDon.getService_total());
        values.put("bill_total",hoaDon.getBill_total());
        return db.update("Bills",values,"id=?",new String[]{String.valueOf(hoaDon.getId())});
    }

    public List<HoaDon> getAll(){
        String sql = "SELECT * FROM Bills ORDER BY bill_date DESC";
        return getData(sql);
    }
    public List<HoaDon> getAllstatus0(){
        String sql = "SELECT * FROM Bills WHERE status = 0 ORDER BY bill_date DESC";
        return getData(sql);
    }
    public List<HoaDon> getAllstatus1(){
        String sql = "SELECT * FROM Bills WHERE status = 1 ORDER BY bill_date DESC";
        return getData(sql);
    }


    public HoaDon getId(String id){
        String sql = "SELECT * FROM Bills WHERE id=?";
        List<HoaDon> list=getData(sql,id);
        return list.get(0);
    }

    public List<HoaDon> getIdspn(){
        String sql = "SELECT * FROM Bills WHERE status=0";
//        List<HoaDon> list=
        return getData(sql);
    }



    @SuppressLint("Range")
    private List<HoaDon> getData(String sql, String...selectionArgs) {

        List<HoaDon> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            HoaDon hoaDon = new HoaDon();
            hoaDon.setId(Integer.parseInt(c.getString(c.getColumnIndex("id"))));
            hoaDon.setManager_id(c.getString(c.getColumnIndex("manager_id")));
            hoaDon.setGuest_id(Integer.parseInt(c.getString(c.getColumnIndex("guest_id"))));
            hoaDon.setRoom_id(Integer.parseInt(c.getString(c.getColumnIndex("room_id"))));
            hoaDon.setStart_date(c.getString(c.getColumnIndex("start_date")));
            hoaDon.setEnd_date(c.getString(c.getColumnIndex("end_date")));
            hoaDon.setStatus(Integer.parseInt(c.getString(c.getColumnIndex("status"))));
            hoaDon.setNote(c.getString(c.getColumnIndex("note")));
            hoaDon.setBill_date(c.getString(c.getColumnIndex("bill_date")));
            hoaDon.setRoom_total(Integer.parseInt(c.getString(c.getColumnIndex("room_total"))));
            hoaDon.setLost_total(Integer.parseInt(c.getString(c.getColumnIndex("lost_total"))));
            hoaDon.setService_total(Integer.parseInt(c.getString(c.getColumnIndex("service_total"))));
            hoaDon.setBill_total(Integer.parseInt(c.getString(c.getColumnIndex("bill_total"))));
            list.add(hoaDon);
        }
        return list;
    }
}
