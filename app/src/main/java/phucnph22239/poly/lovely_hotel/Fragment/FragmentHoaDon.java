package phucnph22239.poly.lovely_hotel.Fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import phucnph22239.poly.lovely_hotel.Adapter.HoaDonAdapter;
import phucnph22239.poly.lovely_hotel.Adapter.SpinnerKhachHangAdapter;
import phucnph22239.poly.lovely_hotel.Adapter.SpinnerPhongAdapter;
import phucnph22239.poly.lovely_hotel.DAO.HoaDonDAO;
import phucnph22239.poly.lovely_hotel.DAO.KhachHangDAO;
import phucnph22239.poly.lovely_hotel.DAO.PhongDao;
import phucnph22239.poly.lovely_hotel.DTO.HoaDon;
import phucnph22239.poly.lovely_hotel.DTO.KhachHang;
import phucnph22239.poly.lovely_hotel.DTO.Phong;
import phucnph22239.poly.lovely_hotel.R;


public class FragmentHoaDon extends Fragment {


    public FragmentHoaDon() {
        // Required empty public constructor
    }

    public static FragmentHoaDon newInstance(String param1, String param2) {
        FragmentHoaDon fragment = new FragmentHoaDon();

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
        return inflater.inflate(R.layout.fragment_hoa_don, container, false);
    }


    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    FloatingActionButton fabAdd;
    ImageView btnTuNgayHD,btnDenNgayHD;
    EditText edTuNgayHD,edDenNgayHD,edTienMat,edGhiChu;
    CheckBox chkTrangThai;
    HoaDon hoaDon;
    HoaDonAdapter hoaDonAdapter;
    HoaDonDAO hoaDonDAO;
    RecyclerView recyclerView;
    List<HoaDon> listHoaDon;

    String maKhacHang,maPhong;

    Spinner spnKhachHang;
    List<KhachHang> listKhachHang;
    KhachHangDAO khachHangDAO;
    SpinnerKhachHangAdapter spinnerKhachHangAdapter;

    Spinner spnPhong;
    List<Phong> listPhong;
    PhongDao phongDao;
    SpinnerPhongAdapter spinnerPhongAdapter;
    Phong phong;
    SearchView searchView;

    int mYear,mMonth,mDay;
    Calendar c = Calendar.getInstance();

    TextView tv_tienPhong,tv_tienDV,tv_tongTien;

    int tienPhong,tienDV,tienDenBu,tongTien;

    ImageButton btn_refresh;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rcv_hoa_don);
        fabAdd = view.findViewById(R.id.btn_add_hoa_don);
        searchView=view.findViewById(R.id.sv_tim_hoa_don);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                FinterList(newText);
                return true;
            }
        });
        listKhachHang = new ArrayList<>();
        khachHangDAO = new KhachHangDAO(getActivity());

        listPhong  = new ArrayList<>();
        phongDao = new PhongDao(getActivity());

        loadTable();

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(),0);
            }
        });
    }

    private void loadTable(){
        hoaDonDAO = new HoaDonDAO(getActivity());
        listHoaDon = hoaDonDAO.getAll();
        hoaDonAdapter = new HoaDonAdapter(getActivity(), (ArrayList<HoaDon>) listHoaDon);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(hoaDonAdapter);
    }

    DatePickerDialog.OnDateSetListener mDateTuNgay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfYear) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfYear;
            GregorianCalendar c = new GregorianCalendar(mYear,mMonth,mDay);
            edTuNgayHD.setText(sdf.format(c.getTime()));
        }
    };

    DatePickerDialog.OnDateSetListener mDateDenNDen = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfYear) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfYear;
            GregorianCalendar c = new GregorianCalendar(mYear,mMonth,mDay);
            edDenNgayHD.setText(sdf.format(c.getTime()));
        }
    };

    private void openDialog(final Context context, final int type){
        Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_them_hoa_don);
        Window window = dialog.getWindow();
        if(window==null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button btnAdd = dialog.findViewById(R.id.btn_luuhd);
        Button btnCancel = dialog.findViewById(R.id.btn_huyhd);

        btnTuNgayHD = dialog.findViewById(R.id.img_bill_start_date);
        btnDenNgayHD = dialog.findViewById(R.id.img_bill_end_date);
        edTuNgayHD = dialog.findViewById(R.id.ed_start_date);
        edDenNgayHD = dialog.findViewById(R.id.ed_end_date);
        edTienMat = dialog.findViewById(R.id.ed_lost_total);
        spnKhachHang = dialog.findViewById(R.id.spn_guest);
        spnPhong = dialog.findViewById(R.id.spn_room);
        chkTrangThai = dialog.findViewById(R.id.chk_bill_status);
        edGhiChu = dialog.findViewById(R.id.tv_bill_note);

        tv_tienPhong = dialog.findViewById(R.id.tv_bill_room_total);
        tv_tienDV = dialog.findViewById(R.id.tv_bill_service_total);
        tv_tongTien = dialog.findViewById(R.id.tv_bill_total);
        btn_refresh = dialog.findViewById(R.id.btn_refresh);

        spinnerKhachHangAdapter = new SpinnerKhachHangAdapter(getContext(),khachHangDAO.getAll());
        spnKhachHang.setAdapter(spinnerKhachHangAdapter);
        spnKhachHang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                listKhachHang = khachHangDAO.getAll();
                maKhacHang = String.valueOf(listKhachHang.get(position).getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerPhongAdapter = new SpinnerPhongAdapter(getContext(), (ArrayList<Phong>) phongDao.getAll());
        spnPhong.setAdapter(spinnerPhongAdapter);
        spnPhong.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                listPhong = phongDao.getAll();
                maPhong = String.valueOf(listPhong.get(position).getId());
                phong = phongDao.getAll().get(position);
                tienPhong = phong.getPrice();
                tv_tienPhong.setText(tienPhong+" VNĐ");

                tienDV = 0;
                tv_tienDV.setText("Tiền dịch vụ: "+tienDV+" VNĐ");

                tongTien = tienPhong+tienDV;
                tv_tongTien.setText("Tổng tiền hóa đơn: "+tongTien+" VNĐ");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnTuNgayHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(getActivity(),0,mDateTuNgay,mYear,mMonth,mDay);
                d.show();
            }
        });
        btnDenNgayHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(getActivity(),0,mDateDenNDen,mYear,mMonth,mDay);
                d.show();
            }
        });

        Intent intent = getActivity().getIntent();
        String user = intent.getStringExtra("user");

        String datetime = sdf.format(c.getTime());

        listHoaDon = hoaDonDAO.getAll();




//        tienDenBu = Integer.parseInt(String.valueOf(edTienMat.getText()));
        btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    tienDenBu = Integer.parseInt(String.valueOf(edTienMat.getText()));
                }catch (Exception e){
                    Toast.makeText(context,"Nhập tiền đền bù (Nếu có)",Toast.LENGTH_LONG).show();
                }
                tongTien = tienPhong+tienDV+tienDenBu;
                tv_tongTien.setText("Tổng tiền hóa đơn: "+tongTien+" VNĐ");
            }
        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(),"Bấm add",Toast.LENGTH_SHORT).show();

                if (validate()>0){
                    hoaDon = new HoaDon();
                    hoaDon.setManager_id(user);
                    hoaDon.setGuest_id(Integer.parseInt(maKhacHang));
                    hoaDon.setRoom_id(Integer.parseInt(maPhong));
                    hoaDon.setStart_date(edTuNgayHD.getText().toString());
                    hoaDon.setEnd_date(edDenNgayHD.getText().toString());
                    hoaDon.setService_total(tienDV);
                    hoaDon.setRoom_total(tienPhong);
                    hoaDon.setLost_total(Integer.parseInt(String.valueOf(edTienMat.getText())));
                    if (chkTrangThai.isChecked()){
                        hoaDon.setStatus(1);
                    }else{
                        hoaDon.setStatus(0);
                    }
                    hoaDon.setNote(String.valueOf(edGhiChu));
                    hoaDon.setBill_date(datetime);
                    hoaDon.setBill_total(tongTien);

                    if (hoaDonDAO.insert(hoaDon)>0){
                        Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_LONG).show();
                        Log.d("zzzzzzzz", hoaDon.getManager_id());
                        dialog.dismiss();
                        loadTable();
                    }else{
                        Toast.makeText(getActivity(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Bấm hủy",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        dialog.show();
    }
    private void FinterList(String text) {
        List<HoaDon> filteredList=new ArrayList<>();
        for (HoaDon hoaDon: listHoaDon){
            if (hoaDon.getStart_date().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(hoaDon);
            }

        }
        if (filteredList.isEmpty()){
            Toast.makeText(this.getContext(), "no data", Toast.LENGTH_SHORT).show();
        }else {
            hoaDonAdapter.setFilteredList(filteredList);
        }
    }

    public int validate(){
        int check =1;
        if (edTuNgayHD.getText().length()==0 || edDenNgayHD.getText().length()==0||edTienMat.getText().length()==0){
            Toast.makeText(getContext(),"Hãy nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}