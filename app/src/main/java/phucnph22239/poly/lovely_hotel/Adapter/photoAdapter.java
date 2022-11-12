package phucnph22239.poly.lovely_hotel.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;


import java.util.List;

import phucnph22239.poly.lovely_hotel.DTO.photo;
import phucnph22239.poly.lovely_hotel.R;

public class photoAdapter extends PagerAdapter {
    private Context mContext;
    private List<photo> mlistphoto;

    public photoAdapter(Context mContext, List<photo> mlistphoto) {
        this.mContext = mContext;
        this.mlistphoto = mlistphoto;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view= LayoutInflater.from(container.getContext()).inflate(R.layout.item_sideshow,container,false);
        ImageView imgphoto=view.findViewById(R.id.imgphoto);
        photo photo=mlistphoto.get(position);
        if (photo!=null){
            Glide.with(mContext).load(photo.getResourceID()).into(imgphoto);
        }
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        if (mlistphoto!= null){
            return mlistphoto.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((View)object);
    }
}
