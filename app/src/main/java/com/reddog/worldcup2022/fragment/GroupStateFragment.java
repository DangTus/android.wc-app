package com.reddog.worldcup2022.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import com.bumptech.glide.Glide;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.reddog.worldcup2022.R;
import com.reddog.worldcup2022.adapter.MatchAdapter;
import com.reddog.worldcup2022.model.Match;
import com.reddog.worldcup2022.module.GroupModule;
import com.reddog.worldcup2022.module.StageModule;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import cz.msebera.android.httpclient.Header;

public class GroupStateFragment extends ListFragment {

    private LinearLayout lGroup;
    private TableLayout tblBXH;
    private AsyncHttpClient client;
    private LayoutInflater inflater;
    private ArrayList<Match> arrayMatch;
    private MatchAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_group_state, container, false);

        anhxa(view);
        this.inflater = inflater;

        client = new AsyncHttpClient();

        //set data table group
        setBXHTable("Group_A");
        getListGroup();

        //set data match
//        arrayMatch = new ArrayList<>();
//        adapter = new MatchAdapter(getActivity(), R.layout.row_tran_dau, arrayMatch);
//        setListAdapter(adapter);
//        getDataMatch();

        return view;
    }

    private void getListGroup() {
        String url = "https://reddog-api-wc-2022.herokuapp.com/stage/get-all";
        client.get(url, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                List<String> groupNameList = StageModule.getNameStage(response, "Group_stage");
                setListGroupButton(groupNameList);
            }
        });
    }

    private void setListGroupButton(List<String> listGroup) {
        for (String idGroup: listGroup) {
            Button button = new Button(getActivity());

            String nameGroup = GroupModule.idToName(idGroup);
            button.setText(nameGroup);

            lGroup.addView(button);

            //set margin
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) button.getLayoutParams();
            params.setMargins(16, 0, 16, 0);
            button.setLayoutParams(params);

            //set on click
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setDefaultButton();
                    setCheckedButton(button);
                    setBXHTable(idGroup);
                }
            });
        }

        //set default button
        setDefaultButton();
        //set checked for the first button
        setCheckedButton((Button) lGroup.getChildAt(0));
    }

    private void setDefaultButton() {
        for (int i=0; i<lGroup.getChildCount(); i++) {
            Button button = (Button) lGroup.getChildAt(i);
            button.setTextColor(getActivity().getResources().getColor(R.color.white));
            button.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_main));
        }
    }

    private void setCheckedButton(Button btn) {
        btn.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_main_checked));
        btn.setTextColor(getActivity().getResources().getColor(R.color.black));
    }

    private void setBXHTable(String groupID) {
        tblBXH.removeAllViews();
        tblBXH.addView(inflater.inflate(R.layout.item_table_row_header, tblBXH, false));

        String url = "https://reddog-api-wc-2022.herokuapp.com/team/get-by-group/" + groupID;
        client.get(url, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    String trangThai = response.getString("status");

                    if(trangThai.equals("success")) {
                        JSONArray team = response.getJSONArray("team");

                        int teamLen = team.length();
                        for (int i=0; i<teamLen; i++) {
                            JSONObject object = team.getJSONObject(i);

                            //create table row
                            TableRow tableRow = (TableRow) inflater.inflate(R.layout.item_table_row, tblBXH, false);
                            //set data table row
                            tableRow = setDataTableRow(tableRow, object);
                            //add table row to table layout
                            tblBXH.addView(tableRow);
                        }
                    } else {
                        Toast.makeText(getActivity(), "Vui long thu lai sau", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private TableRow setDataTableRow(TableRow tableRow, JSONObject object) throws JSONException {
        //anh xa
        TextView teamPosition = tableRow.findViewById(R.id.position);
        ImageView teamImage = tableRow.findViewById(R.id.image);
        TextView teamName = tableRow.findViewById(R.id.name);
        TextView teamPlayed = tableRow.findViewById(R.id.played);
        TextView teamWon = tableRow.findViewById(R.id.won);
        TextView teamDrawn = tableRow.findViewById(R.id.drawn);
        TextView teamLost = tableRow.findViewById(R.id.lost);
        TextView teamGoalDiffience = tableRow.findViewById(R.id.goal_diffience);
        TextView teamPoints = tableRow.findViewById(R.id.points);

        //setData
        teamPosition.setText(object.getString("position"));
        teamName.setText(object.getString("name"));
        Glide.with(getActivity()).load("https:" + object.getString("image")).into(teamImage);
        teamPlayed.setText(object.getString("played"));
        teamWon.setText(object.getString("won"));
        teamDrawn.setText(object.getString("drawn"));
        teamLost.setText(object.getString("lost"));
        teamGoalDiffience.setText(object.getString("goal_difference"));
        teamPoints.setText(object.getString("points"));

        return tableRow;
    }

    private void getDataMatch() {
        String url = getString(R.string.URL) + "match/stage/Group_stage/group_A";
        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    if (!response.getString("status").equals("success")){
                        Toast.makeText(getActivity(), response.getString("message"), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), response.getString("thanh cong"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(getActivity(), responseString, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void anhxa(View view){
        lGroup = view.findViewById(R.id.list_group);
        tblBXH = view.findViewById(R.id.bxh_tblayout);
    }
}
