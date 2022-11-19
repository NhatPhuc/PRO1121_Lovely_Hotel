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

import phucnph22239.poly.lovely_hotel.DTO.KhachHang;
import phucnph22239.poly.lovely_hotel.R;

public class KhachHangAdapter extends RecyclerView.Adapter<KhachHangAdapter.KhachHangViewHolder> {
    private Context context;
    private ArrayList<KhachHang> list;

    public KhachHangAdapter(Context context,ArrayList<KhachHang> list){
        this.context= context;
        this.list = list;
    }

    @NonNull
    @Override
    public KhachHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_khach_hang,parent,false);
        return new KhachHangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KhachHangViewHolder holder, int position) {
        holder.txtName.setText("Khách hàng : "+list.get(position).getName());
        holder.txtPhone.setText("SĐT : "+list.get(position).getPhone());
        holder.txtBirtday.setText("Ngày sinh : "+list.get(position).getBirthday());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class KhachHangViewHolder extends RecyclerView.ViewHolder{
        TextView txtName , txtPhone , txtBirtday ;
        public KhachHangViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtPhone = itemView.findViewById(R.id.txtPhone);
            txtBirtday = itemView.findViewById(R.id.txtBirthday);
        }
    }
}
