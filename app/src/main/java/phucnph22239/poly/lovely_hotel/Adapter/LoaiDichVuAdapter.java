package phucnph22239.poly.lovely_hotel.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import phucnph22239.poly.lovely_hotel.DAO.LoaiDichVuDao;
import phucnph22239.poly.lovely_hotel.DTO.LoaiDichVu;
import phucnph22239.poly.lovely_hotel.R;

public class LoaiDichVuAdapter extends RecyclerView.Adapter<LoaiDichVuAdapter.LoaiDichVuHoler> {
    private Activity context;
    private List<LoaiDichVu> list = new ArrayList<>();
    LoaiDichVuDao loaiDichVuDao;

    public LoaiDichVuAdapter(Activity context) {
        this.context = context;
        notifyDataSetChanged();
    }

    public void setList(List<LoaiDichVu> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public LoaiDichVuHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_loai_dich_vu,parent,false);

        return new LoaiDichVuHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiDichVuHoler holder, int position) {
        LoaiDichVu loaiDichVu = list.get(position);
        if(loaiDichVu == null){
            return;
        }
        holder.e1.setText("Tên dịch vụ : " + loaiDichVu.getName());
        holder.e2.setText("Giá : " + loaiDichVu.getPrice());

        holder.btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loaiDichVuDao = new LoaiDichVuDao(context);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete")
                        .setTitle("Bạn có muốn xóa không")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (loaiDichVuDao.Delete(String.valueOf(loaiDichVu.getId()))>0){
                                    Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                                }
                                list.clear();
                                list.addAll(loaiDichVuDao.getAll());
                                notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("CANNEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                Dialog dialog = builder.create();
                dialog.show();
            }
        });

        holder.btn_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(Gravity.CENTER,loaiDichVu);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(list != null){
            return list.size();
        }
        return 0;
    }

    public class LoaiDichVuHoler extends RecyclerView.ViewHolder{
        TextView e1,e2;
        LinearLayout btn_sua;
        ImageView btn_xoa;
        public LoaiDichVuHoler(@NonNull View itemView) {
            super(itemView);
            e1 = itemView.findViewById(R.id.tv_namedv);
            e2 = itemView.findViewById(R.id.tv_pricedv);
            btn_sua = itemView.findViewById(R.id.item_dich_vu_rcy_sua);
            btn_xoa = itemView.findViewById(R.id.item_dich_vu_rcy_btn_delete);
        }
    }

    private void openDialog(int gravity, LoaiDichVu loaiDichVu){
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_loai_dich_vu);

        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        if(Gravity.CENTER == gravity){
            dialog.setCancelable(true);
        }else{
            dialog.setCancelable(false);
        }
        TextView tvTitle = dialog.findViewById(R.id.dialog_loai_dich_vu_title);

        TextInputLayout til_ten = dialog.findViewById(R.id.ed_loaidichvu);
        TextInputLayout til_gia = dialog.findViewById(R.id.ed_loaidichvuPrice);

        EditText edtTen = dialog.findViewById(R.id.dialog_loai_dich_vu_edt_ten);
        EditText edtGia = dialog.findViewById(R.id.dialog_loai_dich_vu_edt_gia);

        Button btnadd = dialog.findViewById(R.id.btn_themdv);
        Button btncancel = dialog.findViewById(R.id.btn_huydv);

        tvTitle.setText("Sửa Dịch Vụ");
        btnadd.setText("Xác Nhận");
        btncancel.setText("Hủy");

        loaiDichVuDao = new LoaiDichVuDao(context);
        edtTen.setText(loaiDichVu.getName());
        edtGia.setText(""+loaiDichVu.getPrice());

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = 0;
                if (edtTen.getText().toString().equals("")){
                    temp++;
                    til_ten.setError("Tên không được để trống");
                }
                if (edtGia.getText().toString().equals("")){
                    temp++;
                    til_gia.setError("Giá không được để trống");
                }
                if (temp == 0){
                    loaiDichVu.setName(edtTen.getText().toString());
                    loaiDichVu.setPrice(Integer.parseInt(edtGia.getText().toString()));
                    if (loaiDichVuDao.Update(loaiDichVu) > 0){
                        dialog.dismiss();

                        list.clear();
                        list.addAll(loaiDichVuDao.getAll());
                        notifyDataSetChanged();

                        Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    temp=0;
                }
            }
        });
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
