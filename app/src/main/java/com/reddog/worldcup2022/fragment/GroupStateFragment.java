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

import com.bumptech.glide.Glide;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.reddog.worldcup2022.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.zip.Inflater;

import cz.msebera.android.httpclient.Header;

public class GroupStateFragment extends Fragment {

    private LinearLayout lContent, lGroup;
    private TableLayout tblBXH;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_group_state, container, false);

        anhxa(view);

        setBXHTable(inflater);
        setListGroupButton();

        return view;
    }

    private void setListGroupButton() {
        for(int i=1; i<=8; i++) {
            Button button = new Button(getActivity());

            button.setText("Bảng "+i);

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

    private void setBXHTable(LayoutInflater inflater) {
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "https://reddog-api-wc-2022.herokuapp.com/team/get-by-group/1";

        client.get(url, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray group = response.getJSONArray("Group A");

                    for (int i=0; i<group.length(); i++) {
                        JSONObject object = group.getJSONObject(i);

                        //create table row
                        TableRow tableRow = (TableRow) inflater.inflate(R.layout.item_table_row, tblBXH, false);
                        tbRowTeam tbTeam = new tbRowTeam();
                        tbTeam.setDataTbRow(object, tableRow);

                        //add table row to table layout
                        tblBXH.addView(tableRow);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void anhxa(View view){
        lContent = view.findViewById(R.id.contentLayout);
        lGroup = view.findViewById(R.id.list_group);
        tblBXH = view.findViewById(R.id.bxh_tblayout);
    }

    class tbRowTeam {
        TextView teamPosition, teamName, teamPlayed, teamWon, teamDrawn, teamLost, teamGoalDiffience, teamPoints;
        ImageView teamImage;

        TableRow anhXa(TableRow tableRow) {
            teamPosition = tableRow.findViewById(R.id.position);
            teamImage = tableRow.findViewById(R.id.image);
            teamName = tableRow.findViewById(R.id.name);
            teamPlayed = tableRow.findViewById(R.id.played);
            teamWon = tableRow.findViewById(R.id.won);
            teamDrawn = tableRow.findViewById(R.id.drawn);
            teamLost = tableRow.findViewById(R.id.lost);
            teamGoalDiffience = tableRow.findViewById(R.id.goal_diffience);
            teamPoints = tableRow.findViewById(R.id.points);

            return tableRow;
        }

        TableRow setDataTbRow(JSONObject object, TableRow tbRow) {
            tbRow = anhXa(tbRow);

            try {
                teamName.setText(object.getString("name"));
                Glide.with(getActivity()).load("https:" + object.getString("image")).into(teamImage);
                Toast.makeText(getActivity(), object.getString("image"), Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return tbRow;
        }
    }
}
