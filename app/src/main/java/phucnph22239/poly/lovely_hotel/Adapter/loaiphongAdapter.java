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
import java.util.List;

import phucnph22239.poly.lovely_hotel.DAO.loaiphongDAO;
import phucnph22239.poly.lovely_hotel.DTO.loaiphong;
import phucnph22239.poly.lovely_hotel.R;

public class loaiphongAdapter extends RecyclerView.Adapter<loaiphongAdapter.viewholder> {
    private Context context;
    private List<loaiphong> list;
    private loaiphongDAO loaiphongDAO;

    public loaiphongAdapter(Context context, List<loaiphong> list) {
        this.context = context;
        this.list = list;
        loaiphongDAO=new loaiphongDAO(context);
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_loai_phong,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        loaiphong loaiphong=list.get(position);
        holder.tv_namelp.setText(""+loaiphong.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder  extends RecyclerView.ViewHolder{
        TextView tv_namelp;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            tv_namelp=itemView.findViewById(R.id.tv_namelp);
        }
    }
}
