package phucnph22239.poly.lovely_hotel.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import phucnph22239.poly.lovely_hotel.R;


public class FragmentLoaiPhong extends Fragment {


    public FragmentLoaiPhong() {
        // Required empty public constructor
    }


    public static FragmentLoaiPhong newInstance(String param1, String param2) {
        FragmentLoaiPhong fragment = new FragmentLoaiPhong();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_loai_phong, container, false);
    }
}