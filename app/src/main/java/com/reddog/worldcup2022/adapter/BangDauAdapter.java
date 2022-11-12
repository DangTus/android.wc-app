package com.reddog.worldcup2022.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.reddog.worldcup2022.R;
import com.reddog.worldcup2022.model.BangDau;

import java.util.List;

public class BangDauAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<BangDau> listBangDau;

    public BangDauAdapter(Context context, int layout, List<BangDau> listBangDau) {
        this.context = context;
        this.layout = layout;
        this.listBangDau = listBangDau;
    }

    @Override
    public int getCount() {
        return listBangDau.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        ImageView anhQuocGia;
        TextView Stt, tenQuocGia;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder = new ViewHolder();
            //anh xa
            holder.Stt = view.findViewById(R.id.stt);
            holder.tenQuocGia = view.findViewById(R.id.nameQuocGia);
            holder.anhQuocGia = view.findViewById(R.id.imgQuocGia);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        //gán giá trị
        BangDau bangDau = listBangDau.get(i);
        holder.Stt.setText(bangDau.getId());
        holder.tenQuocGia.setText(bangDau.getTen_quoc_gia());
        holder.anhQuocGia.setImageResource(bangDau.getHinh_anh());
        return view;
    }
}
