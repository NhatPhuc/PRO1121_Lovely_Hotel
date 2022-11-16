package phucnph22239.poly.lovely_hotel.Fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import phucnph22239.poly.lovely_hotel.R;


public class FragmentPhong extends Fragment {

    FloatingActionButton fab;

    public FragmentPhong() {
        // Required empty public constructor
    }

    public static FragmentPhong newInstance() {
        FragmentPhong fragment = new FragmentPhong();
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
        return inflater.inflate(R.layout.fragment_phong, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fab = view.findViewById(R.id.fab_add_phong);

        fab.setOnClickListener(v -> {
            openDialog();
        });
    }

    private void openDialog() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_them_phong);
        Button btn_add,btn_cancel;

        btn_add = dialog.findViewById(R.id.dialog_btn_themphong);
        btn_cancel = dialog.findViewById(R.id.dialog_btn_huythemphong);

        btn_cancel.setOnClickListener(v -> {
            dialog.dismiss();
        });

    }
}