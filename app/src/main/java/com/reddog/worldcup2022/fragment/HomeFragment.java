package com.reddog.worldcup2022.fragment;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.reddog.worldcup2022.MatchDetailActivity;
import com.reddog.worldcup2022.R;
import com.reddog.worldcup2022.adapter.MatchAdapter;
import com.reddog.worldcup2022.adapter.NewsVdieoAdapter;
import com.reddog.worldcup2022.model.Match;
import com.reddog.worldcup2022.model.NewsVideo;
import com.reddog.worldcup2022.module.MatchModule;
import com.reddog.worldcup2022.module.NewsVideoModule;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class HomeFragment extends Fragment {
    private LinearLayout listGroupLayout;
    private AsyncHttpClient client;
    private RelativeLayout loadLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        anhXa(view);

        client = new AsyncHttpClient();

        loadLayout.setVisibility(View.VISIBLE);

        //lay ngay hien tai
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        //lay nhung tran hom nay
        String ngayHienTaiStr = sdf.format(cal.getTime());
        getDataMatchByDate(ngayHienTaiStr, "Hôm nay", 0);

        //lay nhung tran hom qua
        cal.add(Calendar.DAY_OF_MONTH, -1);
        String ngayHomQuaStr = sdf.format(cal.getTime());
        getDataMatchByDate(ngayHomQuaStr, "Kết quả hôm qua", 1);

        //lay nhung tran ngay mai
        //vi ngay hien tai bay gio la ngay hom qua nen cal.add +2
        cal.add(Calendar.DAY_OF_MONTH, +2);
        String ngayMaiStr = sdf.format(cal.getTime());
        getDataMatchByDate(ngayMaiStr, "Ngày mai", 2);

        //lay tin tuc video
        getDataListVideoNews(3);

        return view;
    }

    private void getDataMatchByDate(String date, String title, int index) {
        String url = getString(R.string.URL) + "match/date?date=" + date;
        client.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    if (response.getString("status").equals("Error")) {
                        Toast.makeText(getActivity(), response.getString("message"), Toast.LENGTH_SHORT).show();
                    } else if (response.getString("status").equals("Success")) {
                        List<Match> matchList = MatchModule.getMatch(response.getJSONArray("data"));

                        if (matchList.size() > 0) {
                            setLayoutGroupMatch(matchList, title, index);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(getActivity(), responseString, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getDataListVideoNews(int index) {
        String excludeKey = "Highlights";

        String url = "https://youtube.googleapis.com/youtube/v3/search";

        RequestParams params = new RequestParams();
        params.put("part", "snippet");
        params.put("maxResults", 5);
        params.put("q", "world cup 2022");
        params.put("key", "AIzaSyALr9to_aOjni6cDvMf13g8Wdb-zqpwfRM");
        params.put("channelId", "UCabsTV34JwALXKGMqHpvUiA");
        params.put("order", "date");

        client.get(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray items = response.getJSONArray("items");

                    List<NewsVideo> newsVideoList = NewsVideoModule.getListNewsVideo(items);

                    if (newsVideoList.size() > 0) {
                        setLayoutGroupNewsVieo(newsVideoList, "Tin tức", index);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(getActivity(), responseString, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setLayoutGroupMatch(List<Match> matchList, String title, int index) {
        LinearLayout groupMatchLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.layout_group_list, listGroupLayout, false);

        //anh xa
        final TextView txtIndex = groupMatchLayout.findViewById(R.id.index_textview);
        final TextView txtTitle = groupMatchLayout.findViewById(R.id.title_textview);
        final ListView lvMatch = groupMatchLayout.findViewById(R.id.row_listview);

        //set data
        txtIndex.setText(String.valueOf(index));
        txtTitle.setText(title);
        MatchAdapter adapter = new MatchAdapter(getActivity(), R.layout.row_tran_dau, matchList);
        lvMatch.setAdapter(adapter);

        //set su kien click
        lvMatch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), MatchDetailActivity.class);
                intent.putExtra("data_match", matchList.get(i));
                startActivity(intent);
            }
        });

        setHeightListView(lvMatch, matchList.size());
        addGroupLayout(groupMatchLayout, index);
    }

    private void setLayoutGroupNewsVieo(List<NewsVideo> newsVideoList, String title, int index) {
        LinearLayout groupNewsVideoLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.layout_group_list, listGroupLayout, false);

        //anh xa
        final TextView txtIndex = groupNewsVideoLayout.findViewById(R.id.index_textview);
        final TextView txtTitle = groupNewsVideoLayout.findViewById(R.id.title_textview);
        final ListView lvNewsVideo = groupNewsVideoLayout.findViewById(R.id.row_listview);

        //set data
        txtIndex.setText(String.valueOf(index));
        txtTitle.setText(title);
        NewsVdieoAdapter adapter = new NewsVdieoAdapter(getActivity(), R.layout.row_news_video, newsVideoList);
        lvNewsVideo.setAdapter(adapter);

        //set su kien click
        lvNewsVideo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Uri location = Uri.parse(newsVideoList.get(i).getLinkVideo());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);

                try {
                    startActivity(mapIntent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(getActivity(), "Please install youtube", Toast.LENGTH_SHORT).show();
                }
            }
        });

        setHeightListView(lvNewsVideo, newsVideoList.size());
        addGroupLayout(groupNewsVideoLayout, index);
    }

    private void setHeightListView(ListView lv, int listLen) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) lv.getLayoutParams();
        params.height = 360 * listLen + 64;
        lv.setLayoutParams(params);
    }

    private void addGroupLayout(LinearLayout groupLayout, int index) {
        int vitriThem = 0;
        int groupLayoutSize = listGroupLayout.getChildCount();
        for (; vitriThem < groupLayoutSize; vitriThem++) {
            LinearLayout grL = (LinearLayout) listGroupLayout.getChildAt(vitriThem);
            TextView txtIndex = grL.findViewById(R.id.index_textview);
            int ind = Integer.parseInt(txtIndex.getText().toString());

            if (index <= ind) {
                break;
            }
        }

        listGroupLayout.addView(groupLayout, vitriThem);

        if(listGroupLayout.getChildCount() == 4) {
            loadLayout.setVisibility(View.INVISIBLE);
        }
    }

    private void anhXa(View view) {
        listGroupLayout = view.findViewById(R.id.list_group_match_layout);
        loadLayout = view.findViewById(R.id.load_layout);
    }
}
