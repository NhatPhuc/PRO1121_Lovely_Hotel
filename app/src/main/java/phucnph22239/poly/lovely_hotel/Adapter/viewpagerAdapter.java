package phucnph22239.poly.lovely_hotel.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import phucnph22239.poly.lovely_hotel.Fragment.FragmentDoiMatKhau;
import phucnph22239.poly.lovely_hotel.Fragment.FragmentHoaDon;
import phucnph22239.poly.lovely_hotel.Fragment.FragmentPhong;
import phucnph22239.poly.lovely_hotel.Fragment.FragmentThongKe;
import phucnph22239.poly.lovely_hotel.Fragment.Fragment_don_dich_vu;

public class viewpagerAdapter extends FragmentStatePagerAdapter {
    public viewpagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FragmentPhong();
            case 1:
                return new FragmentHoaDon();
            case 2:
                return new Fragment_don_dich_vu();
            case 4:
                return new FragmentThongKe();
            default:
                return new FragmentPhong();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }
}
