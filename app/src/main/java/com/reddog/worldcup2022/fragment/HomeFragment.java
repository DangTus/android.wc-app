package com.reddog.worldcup2022.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.reddog.worldcup2022.R;
import com.reddog.worldcup2022.adapter.MatchAdapter;
import com.reddog.worldcup2022.model.Match;
import com.reddog.worldcup2022.module.DayModule;
import com.reddog.worldcup2022.module.MatchModule;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class HomeFragment extends Fragment {
    private ListView lvSapDienRa;
    private List<Match> matchListSapDienRa, matchListHomNay;
    private MatchAdapter adapterHomNay, adapterSapDienra;
    private AsyncHttpClient client;
    private RelativeLayout loadLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        anhXa(view);

        client = new AsyncHttpClient();

        //set list match sap dien ra
        getDataMatchListSapDienRa();

        return view;
    }

    private void getDataMatchListSapDienRa() {
        loadLayout.setVisibility(View.VISIBLE);

        String url = getString(R.string.URL) + "match/top/10?status=false";
        client.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    if (!response.getString("status").equals("Success")) {
                        Toast.makeText(getActivity(), response.getString("message"), Toast.LENGTH_SHORT).show();
                    } else {
                        matchListSapDienRa = MatchModule.getMatch(response.getJSONArray("data"));

                        //get data & set layout match list hom nay
                        matchListHomNay = new ArrayList<>();
                        getDataMatchListHomNay();
                        setLayoutMatchListHomNay();

                        //remove nhung tran hom nay
                        matchListSapDienRa.removeAll(matchListHomNay);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //end loadding
                loadLayout.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(getActivity(), responseString, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getDataMatchListHomNay() {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy - MM - dd");
            String ngayHienTaiStr = format.format(new Date());
            Date ngayHienTai = format.parse(ngayHienTaiStr);

            for (Match match : matchListSapDienRa) {
                Date ngayDienRa = DayModule.stringToDate(match.getDate());

                if(ngayHienTai.compareTo(ngayDienRa) == 0) {
                    matchListHomNay.add(match);
                } else if(ngayHienTai.compareTo(ngayDienRa) < 0) {
                    return;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void setLayoutMatchListHomNay() {
        adapterHomNay = new MatchAdapter(getActivity(), R.layout.row_tran_dau, matchListHomNay);
        lvSapDienRa.setAdapter(adapterHomNay);

        setHeightListView(lvSapDienRa, matchListHomNay);
    }

    private void setHeightListView(ListView lv, List<Match> matchList) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) lv.getLayoutParams();
        params.height = 360 * matchList.size() + 64;
        lv.setLayoutParams(params);
    }

    private void anhXa(View view) {
        lvSapDienRa = view.findViewById(R.id.sapdienra_listview);
        loadLayout = view.findViewById(R.id.load_layout);
    }
}
