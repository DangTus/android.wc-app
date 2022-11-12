package com.reddog.worldcup2022.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import com.reddog.worldcup2022.R;
import com.reddog.worldcup2022.adapter.BangDauAdapter;
import com.reddog.worldcup2022.adapter.CategoryAdapter;
import com.reddog.worldcup2022.model.BangDau;
import com.reddog.worldcup2022.model.Category;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends ListFragment {
    ArrayList<BangDau> arrayBangDau;
    BangDauAdapter BDadapter;
    private View view;
    private Spinner spnCategory;
    private CategoryAdapter categoryAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        anhxa();
        BDadapter = new BangDauAdapter(getActivity(), R.layout.row_bang_dau, arrayBangDau);
        setListAdapter(BDadapter);
        spnCategory = view.findViewById(R.id.snp_category);
        categoryAdapter = new CategoryAdapter(getActivity(), R.layout.item_selected, getListCategory());
        spnCategory.setAdapter(categoryAdapter);
        spnCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return view;
    }

    private List<Category> getListCategory(){
        List<Category> list = new ArrayList<>();
        list.add(new Category("Bảng A"));
        list.add(new Category("Bảng B"));
        list.add(new Category("Bảng C"));
        list.add(new Category("Bảng D"));

        return list;
    }

    private void anhxa(){
        arrayBangDau = new ArrayList<>();
        arrayBangDau.add(new BangDau("1", "Qatar", R.drawable.qatar));
        arrayBangDau.add(new BangDau("2", "Ecuador", R.drawable.ecuador));
        arrayBangDau.add(new BangDau("3", "Senegal", R.drawable.senegal));
        arrayBangDau.add(new BangDau("4", "Hà Lan", R.drawable.ha_lan));
    }
}
