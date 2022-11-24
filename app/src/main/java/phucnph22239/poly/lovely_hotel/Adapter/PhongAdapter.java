package phucnph22239.poly.lovely_hotel.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import phucnph22239.poly.lovely_hotel.DAO.LoaiPhongDAO;
import phucnph22239.poly.lovely_hotel.DAO.PhongDao;
import phucnph22239.poly.lovely_hotel.DTO.LoaiPhong;
import phucnph22239.poly.lovely_hotel.DTO.Phong;
import phucnph22239.poly.lovely_hotel.R;


public class PhongAdapter extends RecyclerView.Adapter<PhongAdapter.phongViewHolder> {
    private Context context;
    private List<Phong> list;
    private PhongDao phongDAO;

    private LoaiPhong loaiphong;
    private LoaiPhongDAO loaiphongDAO;


    public PhongAdapter(Context context, List<Phong> list) {
        this.context = context;
        this.list = list;
        phongDAO=new PhongDao(context);
        loaiphongDAO=new LoaiPhongDAO(context);
    }
    @NonNull
    @Override
    public phongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_phong, parent, false);
        return new phongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull phongViewHolder holder, int position) {
        Phong phong=list.get(position);
        holder.item_phong_ten.setText("Phòng: "+phong.getName());

        loaiphongDAO =new LoaiPhongDAO(context);
        loaiphong = loaiphongDAO.getID(String.valueOf(phong.getRoom_type_id()));
        holder.item_phong_loaiphong.setText("Loại phòng: \n"+loaiphong.getName());

        holder.item_phong_giaphong.setText("Giá phòng: \n"+phong.getPrice()+" VNĐ");



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class phongViewHolder extends RecyclerView.ViewHolder{
        TextView item_phong_ten,item_phong_loaiphong,item_phong_giaphong,item_phong_trangthai;
        public phongViewHolder(@NonNull View itemView) {
            super(itemView);
            item_phong_ten=itemView.findViewById(R.id.item_phong_ten);
            item_phong_giaphong=itemView.findViewById(R.id.item_phong_giaphong);
            item_phong_loaiphong=itemView.findViewById(R.id.item_phong_loaiphong);
        }
    }
}

