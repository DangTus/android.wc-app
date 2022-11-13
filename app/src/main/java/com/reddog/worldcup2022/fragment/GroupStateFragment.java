package com.reddog.worldcup2022.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.reddog.worldcup2022.R;
import com.reddog.worldcup2022.adapter.CategoryAdapter;
import com.reddog.worldcup2022.model.BangDau;
import com.reddog.worldcup2022.model.Category;

import java.util.ArrayList;
import java.util.List;

public class GroupStateFragment extends Fragment {

    private ArrayList<BangDau> arrayBangDau;
    private Spinner spnCategory;
    private CategoryAdapter categoryAdapter;
    private LinearLayout lContent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_group_state, container, false);

        anhxa(view);

        setTable(inflater);

        //spiner
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

    private void setTable(LayoutInflater inflater) {
        for (int i=1; i<=8; i++) {
            //create table layout
            TableLayout tableLayout = (TableLayout) inflater.inflate(R.layout.item_table_layout, lContent, false);

            for (int j=1; j<=4; j++) {
                //create table row
                TableRow tableRow = (TableRow) inflater.inflate(R.layout.item_table_row, tableLayout, false);

                //add table row to table layout
                tableLayout.addView(tableRow);
            }

            //add table layout to lContent
            lContent.addView(tableLayout);
        }
    }

    private List<Category> getListCategory(){
        List<Category> list = new ArrayList<>();
        list.add(new Category("Bảng A"));
        list.add(new Category("Bảng B"));
        list.add(new Category("Bảng C"));
        list.add(new Category("Bảng D"));
        list.add(new Category("Bảng E"));
        list.add(new Category("Bảng F"));
        list.add(new Category("Bảng G"));
        list.add(new Category("Bảng H"));

        return list;
    }

    private void anhxa(View view){
        lContent = view.findViewById(R.id.contentLayout);
        spnCategory = view.findViewById(R.id.snp_category);
    }
}
