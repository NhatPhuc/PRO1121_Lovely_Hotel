package phucnph22239.poly.lovely_hotel.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import phucnph22239.poly.lovely_hotel.Fragment.Fragment1;
import phucnph22239.poly.lovely_hotel.Fragment.Fragment2;
import phucnph22239.poly.lovely_hotel.Fragment.FragmentDoiMatKhau;
import phucnph22239.poly.lovely_hotel.Fragment.FragmentThongKe;

public class viewpagerAdapter extends FragmentStatePagerAdapter {
    public viewpagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Fragment1();
            case 1:
                return new FragmentDoiMatKhau();
            case 2:
                return new FragmentThongKe();
            default:
                return new Fragment1();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
