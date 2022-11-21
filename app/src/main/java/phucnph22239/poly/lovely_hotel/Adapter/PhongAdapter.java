package phucnph22239.poly.lovely_hotel.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import phucnph22239.poly.lovely_hotel.DAO.PhongDao;
import phucnph22239.poly.lovely_hotel.DTO.LoaiPhong;
import phucnph22239.poly.lovely_hotel.DTO.Phong;
import phucnph22239.poly.lovely_hotel.R;


public class PhongAdapter extends RecyclerView.Adapter<PhongAdapter.phongViewHolder> {
    private Context context;
    private List<Phong> list;
    private PhongDao phongDAO;
    private LoaiPhong lp;
    private loaiphongDAO loaiphongDAO;

    public PhongAdapter(Context context, List<Phong> list) {
        this.context = context;
        this.list = list;
        phongDAO=new PhongDao(context);
        loaiphongDAO=new loaiphongDAO(context);
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
        loaiphongDAO loaiphongDAO = new loaiphongDAO(context);//đặt tên biến ngu v < Duyên said :))
        lp = loaiphongDAO.getID(String.valueOf(phong.getId()));
        holder.item_phong_ten.setText(""+phong.getName());
        holder.item_phong_loaiphong.setText(""+lp.getName());
        holder.item_phong_giaphong.setText(""+phong.getPrice());
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
            item_phong_trangthai=itemView.findViewById(R.id.item_phong_trangthai);
        }
    }
}

