package phucnph22239.poly.lovely_hotel.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import phucnph22239.poly.lovely_hotel.DAO.HoaDonDAO;
import phucnph22239.poly.lovely_hotel.DAO.KhachHangDAO;
import phucnph22239.poly.lovely_hotel.DAO.PhongDao;
import phucnph22239.poly.lovely_hotel.DTO.HoaDon;
import phucnph22239.poly.lovely_hotel.DTO.KhachHang;
import phucnph22239.poly.lovely_hotel.DTO.Phong;
import phucnph22239.poly.lovely_hotel.Fragment.FragmentHoaDon;
import phucnph22239.poly.lovely_hotel.R;
import phucnph22239.poly.lovely_hotel.click_interface.HoaDonClick;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.HoaDonViewHolder>{
    private Context context;
    private ArrayList<HoaDon> arrayList;

    private HoaDonClick hoaDonClick;
    public void setHoaDonClick(HoaDonClick hoaDonClick) {
        this.hoaDonClick = hoaDonClick;
    }

    public HoaDonAdapter(Context context){
        this.context= context;
    }

    public void setData(ArrayList<HoaDon> arrayList){
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public HoaDonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_hoadon,parent,false);
        return new HoaDonViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull HoaDonViewHolder holder, int position) {
        HoaDon hoaDon = arrayList.get(position);

        KhachHangDAO khachHangDAO = new KhachHangDAO(context);
        KhachHang khachHang = khachHangDAO.getID(String.valueOf(hoaDon.getGuest_id()));
        holder.tv_tenKhach.setText("Tên khách hàng: "+khachHang.getName());

        PhongDao phongDao = new PhongDao(context);
        Phong phong = phongDao.getID(String.valueOf(hoaDon.getRoom_id()));
        holder.tv_tenPhong.setText("Phòng: "+phong.getName());

        holder.tv_tienPhong.setText("Tiền phòng: "+hoaDon.getRoom_total()+" VNĐ");
        holder.tv_ngayBD.setText("Từ: "+ hoaDon.getStart_date());
        holder.tv_ngayKT.setText("Đến: "+hoaDon.getEnd_date());
        holder.tv_ngayHD.setText("Ngày tạo hóa đơn: "+hoaDon.getBill_date());


        if (hoaDon.getStatus()==1){
            holder.tv_trangThai.setText("Đã trả phòng");
            holder.tv_trangThai.setTextColor(Color.GREEN);
        }else{
            holder.tv_trangThai.setText("Chưa Trả Phòng");
            holder.tv_trangThai.setTextColor(Color.RED);
        }

        holder.tv_tienMat.setText("Tiền đền bù: \n"+hoaDon.getLost_total()+" VNĐ");
        holder.tv_tienDV.setText("Tiền dịch vụ: \n"+hoaDon.getService_total()+" VNĐ");
        holder.tv_ghiChu.setText("Ghi chú: "+hoaDon.getNote());
        holder.tv_tongTien.setText("Tổng tiền hóa đơn: "+hoaDon.getBill_total()+" VNĐ");



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class HoaDonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tv_tenPhong,tv_tenKhach,tv_ngayBD,tv_ngayKT,tv_ngayHD,
                tv_trangThai,tv_tienMat,tv_tienDV,tv_tienPhong,tv_ghiChu,tv_tongTien;
        public HoaDonViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_tenPhong = itemView.findViewById(R.id.tv_bill_room);
            tv_tenKhach = itemView.findViewById(R.id.tv_bill_guest);
            tv_ngayBD = itemView.findViewById(R.id.tv_bill_start_date);
            tv_ngayKT = itemView.findViewById(R.id.tv_bill_end_date);
            tv_ngayHD = itemView.findViewById(R.id.tv_bill_date);
            tv_trangThai = itemView.findViewById(R.id.tv_bill_status);
            tv_tienPhong = itemView.findViewById(R.id.tv_room_total);
            tv_tienMat = itemView.findViewById(R.id.tv_bill_lost_total);
            tv_tienDV = itemView.findViewById(R.id.tv_bill_service_total);
            tv_ghiChu = itemView.findViewById(R.id.tv_bill_note);
            tv_tongTien = itemView.findViewById(R.id.tv_bill_total);
            itemView.setOnClickListener(this);
        }



        @Override
        public void onClick(View v) {
            try {
                hoaDonClick.onClick(v,getAdapterPosition());
            }catch (Exception e){
                Log.d("zzzzz", "click hoa don: "+ e);
            }
        }
    }
}
