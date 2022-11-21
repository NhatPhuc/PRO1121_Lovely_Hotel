package phucnph22239.poly.lovely_hotel.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import phucnph22239.poly.lovely_hotel.Adapter.LoaiDichVuAdapter;
import phucnph22239.poly.lovely_hotel.DAO.LoaiDichVuDao;
import phucnph22239.poly.lovely_hotel.DTO.LoaiDichVu;
import phucnph22239.poly.lovely_hotel.R;


public class FragmentDichVu extends Fragment {
    private FloatingActionButton button;
    private RecyclerView recyclerView;
    private List<LoaiDichVu> list = new ArrayList<>();
    private LoaiDichVuAdapter adapter;
    public FragmentDichVu() {
        // Required empty public constructor
    }


    public static FragmentDichVu newInstance(String param1, String param2) {
        FragmentDichVu fragment = new FragmentDichVu();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.button = view.findViewById(R.id.btn_add_dich_vu);
        this.recyclerView = view.findViewById(R.id.rcv_dich_vu);
        this.adapter = new LoaiDichVuAdapter(getContext());

        LoaiDichVuDao dichVuDao = new LoaiDichVuDao(getContext());
        list = dichVuDao.getAll();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        adapter.setList(list);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialog();
            }
        });
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dich_vu, container, false);
    }

    private void opendialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_loai_dich_vu, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        TextInputLayout ed_loaidv =view.findViewById(R.id.ed_loaidichvu);
        TextInputLayout ed_loaidvPrice =view.findViewById(R.id.ed_loaidichvuPrice);
        Button btn_themdv =view.findViewById(R.id.btn_themdv);
        Button btn_huydv =view.findViewById(R.id.btn_huydv);
        LoaiDichVuDao loaiDichVuDao = new LoaiDichVuDao(getActivity());
        btn_themdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed_loaidv.getEditText().getText().length() == 0 || ed_loaidvPrice.getEditText().getText().length() == 0) {
                    Toast.makeText(getContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
                } else {
                    LoaiDichVu loaiDichVu = new LoaiDichVu();
                    loaiDichVu.setName(ed_loaidv.getEditText().getText().toString());
                    loaiDichVu.setPrice(Integer.parseInt(ed_loaidvPrice.getEditText().getText().toString()));
                    if (loaiDichVuDao.insert(loaiDichVu) > 0) {
                        Toast.makeText(getContext(), "Thêm loại dịch vụ thành công", Toast.LENGTH_SHORT).show();
                        ed_loaidv.getEditText().setText("");
                        ed_loaidvPrice.getEditText().setText("");
                        list.clear();
                        list.addAll(loaiDichVuDao.getAll());
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getContext(), "Thêm loại sách thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btn_huydv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}