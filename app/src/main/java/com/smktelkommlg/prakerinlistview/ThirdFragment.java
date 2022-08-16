package com.smktelkommlg.prakerinlistview;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

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


public class ThirdFragment extends Fragment {

    private static final String BASE_URL = "https://siip.ub.ac.id/api/";
    private final String EndPoint = "dcim/cables/";
    List<Cables> resultsLists;
    ListView listView;
    ProgressBar progressBar;

    public ThirdFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_third, container, false);
        resultsLists = new ArrayList<>();
        listView = rootView.findViewById(R.id.listViewCables);
        progressBar = rootView.findViewById(R.id.rolling);
        loadResultsList();
        return rootView;
    }

    private void loadResultsList() {
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL+EndPoint+"?limit=200&offset=0", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                // Display the first 500 characters of the response string.
                Log.e("third", response);
                try {
                    //getting the whole json object from the response
                    JSONObject obj = new JSONObject(response);
                    //we have the array named hero inside the object
                    //so here we are getting that json array
                    JSONArray heroArray = obj.getJSONArray("results");
                    //now looping through all the elements of the json array
                    for (int i = 0; i < heroArray.length(); i++) {
                        //getting the json object of the particular index inside the array
                        JSONObject heroObject = heroArray.getJSONObject(i);
                        //creating a superHero object and giving them the values from json object
                        Cables superHero = new Cables(heroObject.getInt("id"), heroObject.getJSONObject("termination_a").getJSONObject("device").getString("name"), heroObject.getJSONObject("termination_b").getJSONObject("device").getString("name"), heroObject.getString("type"), heroObject.getJSONObject("status").getString("value"));
                        //adding the superHero to herolist
                        resultsLists.add(superHero);
                    }
                    CablesAdapter adapter = new CablesAdapter(resultsLists, getActivity().getApplicationContext());
                    //adding the adapter to listview
                    listView.setAdapter(adapter);
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
    public void onViewCreated (@NonNull View view, @Nullable Bundle savedInstanceState){

        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Cables");
        getContext();
    }
}