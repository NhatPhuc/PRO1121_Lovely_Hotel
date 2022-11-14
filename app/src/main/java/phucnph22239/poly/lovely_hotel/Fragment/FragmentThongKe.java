package phucnph22239.poly.lovely_hotel.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Calendar;

import phucnph22239.poly.lovely_hotel.R;

public class FragmentThongKe extends Fragment {
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thongke, container, false);
        EditText edt_start = view.findViewById(R.id.ed_ngayDau);
        EditText edt_end = view.findViewById(R.id.ed_ngayKT);
        Button btn_thongke = view.findViewById(R.id.btnThongke);
        Button btn_ngayDau = view.findViewById(R.id.btnNgayBD);
        Button btn_ngaycuoi = view.findViewById(R.id.btnNgayKT);
        Calendar calendar = Calendar.getInstance();
        btn_ngayDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String ngay="";
                        String thang="";
                        if(dayOfMonth<10){
                            ngay="0"+dayOfMonth;
                        }else {
                            ngay=String.valueOf(dayOfMonth);
                        }
                        if((month+1)<10){
                            thang="0"+(month+1);
                        }else {
                            thang=String.valueOf((month+1));
                        }
                        edt_start.setText(year+"/"+thang+"/"+ngay);
                    }
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
        btn_ngaycuoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String ngay="";
                        String thang="";
                        if(dayOfMonth<10){
                            ngay="0"+dayOfMonth;
                        }else {
                            ngay=String.valueOf(dayOfMonth);
                        }
                        if((month+1)<10){
                            thang="0"+(month+1);
                        }else {
                            thang=String.valueOf((month+1));
                        }
                        edt_end.setText(year+"/"+thang+"/"+ngay);
                    }
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
        return view;
    }
}
