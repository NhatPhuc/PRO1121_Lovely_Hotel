package phucnph22239.poly.lovely_hotel.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import phucnph22239.poly.lovely_hotel.Adapter.photoAdapter;
import phucnph22239.poly.lovely_hotel.DTO.photo;
import phucnph22239.poly.lovely_hotel.R;


public class Fragment1 extends Fragment {
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private phucnph22239.poly.lovely_hotel.Adapter.photoAdapter photoAdapter;
    public Fragment1() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_1, container, false);
        viewPager=view.findViewById(R.id.viewpager);
        circleIndicator=view.findViewById(R.id.circle_indicator);
        photoAdapter=new photoAdapter(this.getContext(),getlistPhoto()) ;
        viewPager.setAdapter(photoAdapter);
        circleIndicator.setViewPager(viewPager);
        photoAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
        return view;
    }
    private List<photo> getlistPhoto(){
        List<photo>list=new ArrayList<>();
        list.add(new photo(R.drawable.img_2));
        list.add(new photo(R.drawable.img_1));
        list.add(new photo(R.drawable.img));
        return list;
    }
}