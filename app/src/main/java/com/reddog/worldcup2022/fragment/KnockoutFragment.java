package com.reddog.worldcup2022.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.reddog.worldcup2022.R;
import com.reddog.worldcup2022.adapter.MatchAdapter;
import com.reddog.worldcup2022.model.Match;
import com.reddog.worldcup2022.module.GroupModule;
import com.reddog.worldcup2022.module.MatchModule;
import com.reddog.worldcup2022.module.StageModule;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class KnockoutFragment extends ListFragment {
    private LinearLayout lStage;
    private AsyncHttpClient client;
    private List<Match> arrayMatch;
    private MatchAdapter adapter;
    private RelativeLayout loadLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_knockout_stage, container, false);

        anhxa(view);

        client = new AsyncHttpClient();

        //start loadding
        loadLayout.setVisibility(View.VISIBLE);

        getListGroup();

        //create adapter match
        arrayMatch = new ArrayList<>();
        adapter = new MatchAdapter(getActivity(), R.layout.row_tran_dau, arrayMatch);
        setListAdapter(adapter);

        return view;
    }

    private void getListGroup() {
        String url = getString(R.string.URL) + "stage/get-all";
        client.get(url, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                List<String> groupNameList = StageModule.getNameStage(response, "Knockout_stage");
                setListStageButton(groupNameList);
            }
        });
    }

    private void setListStageButton(List<String> listGroup) {
        for (String idGroup : listGroup) {
            Button button = (Button) getLayoutInflater().inflate(R.layout.layout_button_stage, lStage, false);

            String nameGroup = GroupModule.idToName(idGroup);
            button.setText(nameGroup);

            lStage.addView(button);

            //set on click
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (loadLayout.getVisibility() == View.INVISIBLE) {
                        //start loadding
                        loadLayout.setVisibility(View.VISIBLE);

                        setDefaultButton();
                        setCheckedButton(button);
                        setDataMatch(idGroup);
                    }
                }
            });
        }

        //set default button
        setDefaultButton();
        //set checked for the first button
        setCheckedButton((Button) lStage.getChildAt(0));
        //set data match default
        setDataMatch("Round_of_16");
    }

    private void setDefaultButton() {
        for (int i = 0; i < lStage.getChildCount(); i++) {
            Button button = (Button) lStage.getChildAt(i);
            button.setTextColor(getActivity().getResources().getColor(R.color.white));
            button.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_main));
        }
    }

    private void setCheckedButton(Button btn) {
        btn.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_main_checked));
        btn.setTextColor(getActivity().getResources().getColor(R.color.black));
    }

    private void setDataMatch(String idStage) {
        arrayMatch.clear();
        adapter.notifyDataSetChanged();

        String url = getString(R.string.URL) + "match/stage/Knockout_stage/" + idStage;
        client.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    if (!response.getString("status").equals("Success")) {
                        Toast.makeText(getActivity(), response.getString("message"), Toast.LENGTH_SHORT).show();
                    } else {
                        arrayMatch = MatchModule.getMatch(response.getJSONArray("data"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                adapter = new MatchAdapter(getActivity(), R.layout.row_tran_dau, arrayMatch);
                setListAdapter(adapter);
                //end loadding
                loadLayout.setVisibility(View.INVISIBLE);
                //set height list view
                setHeightListView();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(getActivity(), responseString, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setHeightListView() {
        getListView().post(new Runnable() {
            @Override
            public void run() {
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) getListView().getLayoutParams();
                params.height = 360 * arrayMatch.size() + 64;
                getListView().setLayoutParams(params);
            }
        });
    }

    private void anhxa (View view){
        loadLayout = view.findViewById(R.id.load_layout);
        lStage = view.findViewById(R.id.list_stage);
    }
}
