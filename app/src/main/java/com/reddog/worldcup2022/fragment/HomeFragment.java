package com.reddog.worldcup2022.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.reddog.worldcup2022.R;

public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ImageView hinh = view.findViewById(R.id.hinh);
        Glide.with(getActivity()).load("https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/Flag_of_Qatar.svg/5000px-Flag_of_Qatar.svg.png").fitCenter().into(hinh);

        return view;
    }
}
