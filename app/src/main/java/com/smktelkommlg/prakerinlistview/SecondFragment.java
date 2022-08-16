package com.smktelkommlg.prakerinlistview;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SecondFragment extends Fragment {

    private static final String BASE_URL = "https://siip.ub.ac.id/api/";
    private final String EndPoint = "dcim/devices/";
    List<Result> resultsLists;
    ListView listView;
    ProgressBar progressBar;
    ListViewAdapter adapter;
    SwipeRefreshLayout swipeRefreshLayout;

    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_second, container, false);
        resultsLists = new ArrayList<>();
        listView = rootView.findViewById(R.id.listView);
        progressBar = rootView.findViewById(R.id.rolling);
        swipeRefreshLayout = rootView.findViewById(R.id.refresh1);

        loadResultsList();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadResultsList();
                adapter.clear();
                adapter.notifyDataSetChanged();
                listView.invalidateViews();
                listView.refreshDrawableState();
            }
        });


        // Instantiate the RequestQueue.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String idToDetail = String.valueOf(resultsLists.get(position).getId());

                Intent detailIntent = new Intent(getActivity(), DetailActivity.class);
                detailIntent.putExtra("idMoveIntent", idToDetail);
                startActivity(detailIntent);

            }
        });

        return rootView;
    }

    private void loadResultsList() {
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL + EndPoint + "?limit=500&offset=0", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                progressBar.setVisibility(View.GONE);
                Log.d("Json Response", response);
                try {

                    JSONObject obj = new JSONObject(response);
                    JSONArray heroArray = obj.getJSONArray("results");
                    for (int i = 0; i < heroArray.length(); i++) {
                        JSONObject heroObject = heroArray.getJSONObject(i);
                        Result superHero = new Result(heroObject.getString("name"), heroObject.getInt("id"), heroObject.getJSONObject("device_role").getString("name"), heroObject.getJSONObject("status").getString("value"), heroObject.getJSONObject("device_type").getJSONObject("manufacturer").getString("slug"));
                        resultsLists.add(superHero);


                    }
                    adapter = new ListViewAdapter(resultsLists, getActivity());
                    listView.setAdapter(adapter);
                    swipeRefreshLayout.setRefreshing(false);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap header = new HashMap();
                header.put("Content-Type", "application/json");
                header.put("Authorization", "Token eaaa9e72d1a4f4c75aef7a7492e07785d853b6b4");
                return header;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        queue.add(stringRequest);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Results");
        getContext();
    }
}