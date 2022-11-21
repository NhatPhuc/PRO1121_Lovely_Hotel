package phucnph22239.poly.lovely_hotel.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import phucnph22239.poly.lovely_hotel.DAO.loaiphongDAO;
import phucnph22239.poly.lovely_hotel.DTO.loaiphong;
import phucnph22239.poly.lovely_hotel.R;
import phucnph22239.poly.lovely_hotel.click_interface.LoaiphongClick;

public class loaiphongAdapter extends RecyclerView.Adapter<loaiphongAdapter.viewholder> {
    private Context context;
    private List<loaiphong> list;
    private loaiphongDAO loaiphongDAO;
    private LoaiphongClick loaiphongClick;

    public loaiphongAdapter(Context context, List<loaiphong> list) {
        this.context = context;
        this.list = list;
        loaiphongDAO=new loaiphongDAO(context);
    }

    public void setLoaiphongClick(LoaiphongClick loaiphongClick) {
        this.loaiphongClick = loaiphongClick;
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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loaiphongClick.onClick(list.get(position));
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete")
                        .setTitle("Bạn có muốn xóa không")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (loaiphongDAO.delete(loaiphong.getId()) > 0) {
                                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                    list.clear();
                                    list.addAll(loaiphongDAO.getAll());
                                    notifyDataSetChanged();
                                } else {
                                    Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("CANNEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                Dialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder  extends RecyclerView.ViewHolder{
        TextView tv_namelp;
        ImageView delete;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            tv_namelp=itemView.findViewById(R.id.tv_namelp);
            delete=itemView.findViewById(R.id.btn_xoa);
        }
    }
}
