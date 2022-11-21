package phucnph22239.poly.lovely_hotel.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import phucnph22239.poly.lovely_hotel.R;

public class FragmentThongKe extends Fragment {

    Button btnDoanhThu;
    ImageView btnTuNgay,btnDenNgay;
    EditText edtTuNgay,edtDenNgay;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    int mYear,mMonth,mDay;
    TextView tv;
    int temp=0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thongke, container, false);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnTuNgay = view.findViewById(R.id.doanhthu_img_tungay);
        btnDenNgay = view.findViewById(R.id.doanhthu_img_denngay);
        btnDoanhThu = view.findViewById(R.id.doanhthu_btn_tinh);

        edtTuNgay = view.findViewById(R.id.doanhthu_edt_tungay);
        edtDenNgay = view.findViewById(R.id.doanhthu_edt_denngay);


        tv = view.findViewById(R.id.doanhthu_tv_doanhthu);

        btnTuNgay.setOnClickListener(new View.OnClickListener() {
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

        btnDenNgay.setOnClickListener(new View.OnClickListener() {
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

        btnDoanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    DatePickerDialog.OnDateSetListener mDateTuNgay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfYear) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfYear;
            GregorianCalendar c = new GregorianCalendar(mYear,mMonth,mDay);
            edtTuNgay.setText(sdf.format(c.getTime()));
        }
    };

    DatePickerDialog.OnDateSetListener mDateDenNDen = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfYear) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfYear;
            GregorianCalendar c = new GregorianCalendar(mYear,mMonth,mDay);
            edtDenNgay.setText(sdf.format(c.getTime()));
        }
    };
}
