package phucnph22239.poly.lovely_hotel.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import phucnph22239.poly.lovely_hotel.Adapter.SpinnerDichVuAdapter;
import phucnph22239.poly.lovely_hotel.Adapter.SpinnerHoaDonAdapter;
import phucnph22239.poly.lovely_hotel.DAO.HoaDonDAO;
import phucnph22239.poly.lovely_hotel.DAO.LoaiDichVuDao;
import phucnph22239.poly.lovely_hotel.DAO.PhongDao;
import phucnph22239.poly.lovely_hotel.DTO.HoaDon;
import phucnph22239.poly.lovely_hotel.DTO.LoaiDichVu;
import phucnph22239.poly.lovely_hotel.DTO.LoaiPhong;
import phucnph22239.poly.lovely_hotel.R;


public class Fragment_don_dich_vu extends Fragment {
    private FloatingActionButton fab_them_don_dv;
    private RecyclerView recyclerView;
    private Spinner dialog_spn_hd_phong,dialog_spn_loai_dv;

    private HoaDonDAO hoaDonDAO;
    private List<HoaDon> listHoaDon;
    SpinnerHoaDonAdapter spinnerHoaDonAdapter;
    HoaDon hoaDon;

    String maHoaDon,maDichVu;

    private LoaiDichVuDao loaiDichVuDao;
    private List<LoaiDichVu> listLoaiDichVu;
    SpinnerDichVuAdapter spinnerDichVuAdapter;
    LoaiDichVu loaiDichVu;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_don_dich_vu, container, false);
        fab_them_don_dv = view.findViewById(R.id.btn_add_dich_vu);
        recyclerView = view.findViewById(R.id.rcv_dich_vu);
        hoaDonDAO = new HoaDonDAO(getActivity());
        loaiDichVuDao = new LoaiDichVuDao(getActivity());

        fab_them_don_dv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();

            }
        });
        return view;
    }

    private void openDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_don_dich_vu, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        Window window = dialog.getWindow();
        if(window==null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        dialog_spn_hd_phong= dialog.findViewById(R.id.dialog_spn_hoa_don);
        dialog_spn_loai_dv = dialog.findViewById(R.id.dialog_spn_dich_vu);

        spinnerHoaDonAdapter = new SpinnerHoaDonAdapter(getContext(), (ArrayList<HoaDon>) hoaDonDAO.getAll());
        dialog_spn_hd_phong.setAdapter(spinnerHoaDonAdapter);
        dialog_spn_hd_phong.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                listHoaDon = hoaDonDAO.getAll();
                maHoaDon = String.valueOf(listHoaDon.get(position).getId());
//                hoaDon =
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerDichVuAdapter = new SpinnerDichVuAdapter(getContext(), (ArrayList<LoaiDichVu>) loaiDichVuDao.getAll());
        dialog_spn_loai_dv.setAdapter(spinnerDichVuAdapter);
        dialog_spn_loai_dv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                listLoaiDichVu = loaiDichVuDao.getAll();
                maDichVu = String.valueOf(listLoaiDichVu.get(position).getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}