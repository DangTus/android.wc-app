package com.reddog.worldcup2022.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.reddog.worldcup2022.R;
import com.reddog.worldcup2022.model.Match;

import java.util.List;

public class MatchAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Match> matchList;

    public MatchAdapter(Context context, int layout, List<Match> matchList) {
        this.context = context;
        this.layout = layout;
        this.matchList = matchList;
    }

    @Override
    public int getCount() {
        return matchList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);

            holder = new ViewHolder();

            //anh xa
            holder.txtTenDoiNha = view.findViewById(R.id.ten_doi_nha);
            holder.txtTenDoiKhach = view.findViewById(R.id.ten_doi_khach);
            holder.txtNgay = view.findViewById(R.id.day);
            holder.txtGio = view.findViewById(R.id.tvGio);

            holder.txt_tsDoiNha = view.findViewById(R.id.ti_so_doi_nha);
            holder.txt_tsDoiKhach = view.findViewById(R.id.ti_so_doi_khach);

            holder.imgDoiNha = view.findViewById(R.id.img_doi_nha);
            holder.imgDoiKhach = view.findViewById(R.id.img_doi_khach);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        //gán giá trị
        Match m = matchList.get(i);
        holder.txtTenDoiNha.setText(m.getHomeT().getName());
        holder.txtTenDoiKhach.setText(m.getAwayT().getName());
        holder.txtNgay.setText(m.getDate());
        holder.txtGio.setText(m.getTime());
        holder.txt_tsDoiNha.setText(String.valueOf(m.getHomeScore()));
        holder.txt_tsDoiKhach.setText(String.valueOf(m.getAwayScore()));
        //chua anh xa ti so

        Glide
                .with(context)
                .load(m.getHomeT().getLogo())
                .into(holder.imgDoiNha);

        Glide
                .with(context)
                .load(m.getAwayT().getLogo())
                .into(holder.imgDoiKhach);

        return view;
    }

    private class ViewHolder {
        TextView txtNgay, txtGio, txtTenDoiNha, txtTenDoiKhach, txt_tsDoiNha, txt_tsDoiKhach;
        ImageView imgDoiNha, imgDoiKhach;
    }
}
