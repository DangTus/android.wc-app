package com.reddog.worldcup2022.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.reddog.worldcup2022.R;
import com.reddog.worldcup2022.model.Match;
import com.reddog.worldcup2022.model.NewsVideo;

import java.util.List;

public class NewsVdieoAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<NewsVideo> newsVideoList;

    public NewsVdieoAdapter(Context context, int layout, List<NewsVideo> newsVideoList) {
        this.context = context;
        this.layout = layout;
        this.newsVideoList = newsVideoList;
    }

    @Override
    public int getCount() {
        return newsVideoList.size();
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
            holder.txtTitle = view.findViewById(R.id.title_textview);
            holder.imvThumbnail = view.findViewById(R.id.thumbnail_imageview);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        //gán giá trị
        NewsVideo newsVideo = newsVideoList.get(i);
        holder.txtTitle.setText(newsVideo.getTitle());
        Glide.with(context).load(newsVideo.getLinkThumbnail()).into(holder.imvThumbnail);

        return view;
    }

    private class ViewHolder {
        TextView txtTitle;
        ImageView imvThumbnail;
    }
}
