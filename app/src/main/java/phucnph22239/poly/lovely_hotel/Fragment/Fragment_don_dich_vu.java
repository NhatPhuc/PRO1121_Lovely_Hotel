package phucnph22239.poly.lovely_hotel.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import phucnph22239.poly.lovely_hotel.R;


public class Fragment_don_dich_vu extends Fragment {
    private FloatingActionButton button;
    private RecyclerView recyclerView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_don_dich_vu, container, false);
        button = view.findViewById(R.id.btn_add_dich_vu);
        recyclerView = view.findViewById(R.id.rcv_dich_vu);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }
}