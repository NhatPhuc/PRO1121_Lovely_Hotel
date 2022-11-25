package phucnph22239.poly.lovely_hotel.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import phucnph22239.poly.lovely_hotel.DAO.HoaDonDAO;
import phucnph22239.poly.lovely_hotel.DAO.KhachHangDAO;
import phucnph22239.poly.lovely_hotel.DAO.LoaiDichVuDao;
import phucnph22239.poly.lovely_hotel.DAO.PhongDao;
import phucnph22239.poly.lovely_hotel.DTO.HoaDon;
import phucnph22239.poly.lovely_hotel.DTO.HoaDonDichVu;
import phucnph22239.poly.lovely_hotel.DTO.KhachHang;
import phucnph22239.poly.lovely_hotel.DTO.LoaiDichVu;
import phucnph22239.poly.lovely_hotel.DTO.Phong;
import phucnph22239.poly.lovely_hotel.R;

public class DonDichVuAdapter extends RecyclerView.Adapter<DonDichVuAdapter.HoaDonViewHolder>{
    private Context context;
    private ArrayList<HoaDonDichVu> list;

    public DonDichVuAdapter(Context context, ArrayList<HoaDonDichVu> list){
        this.context= context;
        this.list = list;
    }

    @NonNull
    @Override
    public HoaDonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_don_dich_vu,parent,false);
        return new HoaDonViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull HoaDonViewHolder holder, int position) {
        HoaDonDichVu hoaDonDichVu = list.get(position);
        holder.tv_ngayDatDV.setText("Ngày tạo hóa đơn: "+hoaDonDichVu.getService_date());

        HoaDonDAO hoaDonDAO = new HoaDonDAO(context);
        HoaDon hoaDon = hoaDonDAO.getId(String.valueOf(hoaDonDichVu.getBill_id()));
        PhongDao phongDao = new PhongDao(context);
        Phong phong = phongDao.getID(String.valueOf(hoaDon.getRoom_id()));
        holder.tv_tenPhong.setText("Phòng: "+phong.getName());

        LoaiDichVuDao loaiDichVuDao = new LoaiDichVuDao(context);
        LoaiDichVu loaiDichVu = loaiDichVuDao.getID(String.valueOf(hoaDonDichVu.getService_id()));
        holder.tv_loaiDV.setText("Loại dịch vụ: "+loaiDichVu.getName());

        holder.tv_soLuong.setText("Số lượng dịch vụ: "+hoaDonDichVu.getService_quantity());
        holder.tv_tongTienDV.setText("Tổng tiền: "+hoaDonDichVu.getTotal()+" VNĐ");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HoaDonViewHolder extends RecyclerView.ViewHolder{
        TextView tv_ngayDatDV,tv_tenPhong,tv_loaiDV,tv_soLuong,tv_tongTienDV;
        public HoaDonViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_ngayDatDV = itemView.findViewById(R.id.hddv_tv_ngay);
            tv_tenPhong = itemView.findViewById(R.id.hddv_tv_phong);
            tv_loaiDV = itemView.findViewById(R.id.hddv_tv_loai_dv);
            tv_soLuong = itemView.findViewById(R.id.hddv_tv_so_luong_dv);
            tv_tongTienDV = itemView.findViewById(R.id.hddv_tv_tong_tien);

        }
    }
}
