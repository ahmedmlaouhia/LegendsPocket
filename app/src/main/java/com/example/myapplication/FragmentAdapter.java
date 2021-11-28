package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentAdapter extends FragmentStateAdapter {
    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new firstF();
            case 1:
                return new secondF();
            case 2:
                return new thirdF();
            case 3:
                return new fourthF();
            case 4:
                return new fifthF();
        }
        return new thirdF();
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
