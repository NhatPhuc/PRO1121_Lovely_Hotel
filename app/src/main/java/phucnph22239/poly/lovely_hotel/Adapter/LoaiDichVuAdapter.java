package phucnph22239.poly.lovely_hotel.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import phucnph22239.poly.lovely_hotel.DTO.LoaiDichVu;
import phucnph22239.poly.lovely_hotel.R;

public class LoaiDichVuAdapter extends RecyclerView.Adapter<LoaiDichVuAdapter.LoaiDichVuHoler> {
    private Context context;
    private List<LoaiDichVu> list = new ArrayList<>();

    public LoaiDichVuAdapter(Context context) {
        this.context = context;
        notifyDataSetChanged();
    }

    public void setList(List<LoaiDichVu> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public LoaiDichVuHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_loai_dich_vu,parent,false);

        return new LoaiDichVuHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiDichVuHoler holder, int position) {
        LoaiDichVu loaiDichVu = list.get(position);
        if(loaiDichVu == null){
            return;
        }
        holder.e1.setText("Tên dịch vụ : " + loaiDichVu.getName());
        holder.e2.setText("Giá : " + loaiDichVu.getPrice());
    }

    @Override
    public int getItemCount() {
        if(list != null){
            return list.size();
        }
        return 0;
    }

    public class LoaiDichVuHoler extends RecyclerView.ViewHolder{
        TextView e1,e2;
        public LoaiDichVuHoler(@NonNull View itemView) {
            super(itemView);
            e1 = itemView.findViewById(R.id.tv_namedv);
            e2 = itemView.findViewById(R.id.tv_pricedv);
        }
    }
}
