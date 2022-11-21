package phucnph22239.poly.lovely_hotel.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import phucnph22239.poly.lovely_hotel.Adapter.PhongAdapter;
import phucnph22239.poly.lovely_hotel.DAO.PhongDao;
import phucnph22239.poly.lovely_hotel.DAO.loaiphongDAO;
import phucnph22239.poly.lovely_hotel.DTO.Phong;
import phucnph22239.poly.lovely_hotel.R;


public class FragmentPhong extends Fragment {
    private RecyclerView recyclerView;
    private PhongDao phongDAO;
    private loaiphongDAO loaiphongDAO;
    private List<Phong> list=new ArrayList<Phong>();
    private PhongAdapter adapter;
    private FloatingActionButton button;
    private TextInputEditText dialog_ed_tenphong,dialog_ed_giaphong;
    private Spinner dialog_spn_loaiphong;
    private CheckBox ckb_phong;
    Button dialog_btn_themphong,dialog_btn_huythemphong;
    PhongDao phong;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_phong, container, false);
        phongDAO=new PhongDao(getContext());
        recyclerView=view.findViewById(R.id.rcv_phong);
        list=phongDAO.getAll();
        adapter=new PhongAdapter(getContext(),list);
        button=view.findViewById(R.id.fab_add_phong);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialog();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        return view;
    }
    private void opendialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_them_phong, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        dialog_ed_tenphong=view.findViewById(R.id.dialog_ed_tenphong);
        dialog_ed_giaphong=view.findViewById(R.id.dialog_ed_giaphong);
        dialog_spn_loaiphong=view.findViewById(R.id.dialog_spn_loaiphong);
        dialog_btn_themphong=view.findViewById(R.id.dialog_btn_themphong);
        dialog_btn_huythemphong=view.findViewById(R.id.dialog_btn_huythemphong);
        ckb_phong=view.findViewById(R.id.ckb_phong);
        dialog_btn_themphong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        dialog_btn_huythemphong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

}
