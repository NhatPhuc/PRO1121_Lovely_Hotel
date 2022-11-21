package phucnph22239.poly.lovely_hotel.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import phucnph22239.poly.lovely_hotel.Adapter.loaiphongAdapter;
import phucnph22239.poly.lovely_hotel.DAO.loaiphongDAO;
import phucnph22239.poly.lovely_hotel.DTO.loaiphong;
import phucnph22239.poly.lovely_hotel.R;
import phucnph22239.poly.lovely_hotel.click_interface.LoaiphongClick;


public class FragmentLoaiPhong extends Fragment {
    private RecyclerView rcv_loai_phong;
    private loaiphongDAO loaiphongDAO;
    private List<loaiphong> list=new ArrayList<>();
    private loaiphongAdapter adapter;
    private TextInputLayout ed_loaiphong;
    private Button btn_themlp,btn_huylp;
    private FloatingActionButton button;
    private loaiphong loaiphong;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_loai_phong, container, false);
        rcv_loai_phong=view.findViewById(R.id.rcv_loai_phong);
        button=view.findViewById(R.id.btn_add_loai_phong);
        loaiphongDAO= new loaiphongDAO(getContext());
        list=loaiphongDAO.getAll();
        adapter=new loaiphongAdapter(getContext(),list);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialog();
            }
        });
        adapter.setLoaiphongClick(new LoaiphongClick() {
            @Override
            public void onClick(loaiphong loaiphong) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View view1 = LayoutInflater.from(getContext()).inflate(R.layout.dialog_loai_phong, null);
                builder.setView(view1);
                Dialog dialog = builder.create();
                dialog.show();
                ed_loaiphong=view1.findViewById(R.id.ed_loaiphong);
                btn_themlp=view1.findViewById(R.id.btn_themlp);
                btn_themlp.setText("sửa");
                btn_huylp=view1.findViewById(R.id.btn_huylp);
                ed_loaiphong.getEditText().setText(loaiphong.getName());
                btn_themlp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ed_loaiphong.getEditText().getText().length() == 0) {
                            Toast.makeText(getContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
                        } else {
                            loaiphong.setName(ed_loaiphong.getEditText().getText().toString());
                            if (loaiphongDAO.upate(loaiphong) > 0) {
                                Toast.makeText(getContext(), "Sửa loại sách thành công", Toast.LENGTH_SHORT).show();
                                ed_loaiphong.getEditText().setText("");
                                list.clear();
                                list.addAll(loaiphongDAO.getAll());
                                adapter.notifyDataSetChanged();
                            } else {
                                Toast.makeText(getContext(), "Sửa loại sách thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
                btn_huylp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
        rcv_loai_phong.setLayoutManager(new LinearLayoutManager(getContext()));
        rcv_loai_phong.setAdapter(adapter);
        return view;
    }
    private void opendialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_loai_phong, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        ed_loaiphong=view.findViewById(R.id.ed_loaiphong);
        btn_themlp=view.findViewById(R.id.btn_themlp);
        btn_huylp=view.findViewById(R.id.btn_huylp);
        btn_themlp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed_loaiphong.getEditText().getText().length() == 0) {
                    Toast.makeText(getContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
                } else {
                    loaiphong = new loaiphong();
                    loaiphong.setName(ed_loaiphong.getEditText().getText().toString());
                    if (loaiphongDAO.insert(loaiphong) > 0) {
                        Toast.makeText(getContext(), "Thêm loại sách thành công", Toast.LENGTH_SHORT).show();
                        ed_loaiphong.getEditText().setText("");
                        list.clear();
                        list.addAll(loaiphongDAO.getAll());
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getContext(), "Thêm loại sách thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btn_huylp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}