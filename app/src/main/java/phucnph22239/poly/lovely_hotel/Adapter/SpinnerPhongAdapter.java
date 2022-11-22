package phucnph22239.poly.lovely_hotel.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import phucnph22239.poly.lovely_hotel.DTO.KhachHang;
import phucnph22239.poly.lovely_hotel.DTO.Phong;
import phucnph22239.poly.lovely_hotel.R;

public class SpinnerPhongAdapter extends ArrayAdapter<Phong> {
    private Context context;
    private ArrayList<Phong> objects;
    TextView tvspnphong;

    public SpinnerPhongAdapter(Context context, ArrayList<Phong> objects) {
        super(context, 0, objects);
        this.context = context;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View holder = convertView;
        if (holder==null){
            LayoutInflater inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            holder = inflater.inflate(R.layout.item_phong_spinner,null);

        }
        final Phong obj = objects.get(position);
        if (obj != null){

            tvspnphong = holder.findViewById(R.id.item_spn_phong);
            tvspnphong.setText(obj.getName());
        }
        return holder;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View holder = convertView;
        if (holder==null){
            LayoutInflater inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            holder = inflater.inflate(R.layout.item_phong_spinner,null);

        }
        final Phong obj = objects.get(position);
        if (obj != null){

            tvspnphong = holder.findViewById(R.id.item_spn_phong);
            tvspnphong.setText(obj.getName());
        }
        return holder;
    }
}