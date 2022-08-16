package com.smktelkommlg.prakerinlistview;



import static java.lang.Integer.getInteger;
import static java.lang.Integer.parseInt;
import static java.lang.Integer.valueOf;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class DashboardFragment extends Fragment {

    TextView ngengg;

    public static String base_url = String.format("https://siip.ub.ac.id/api/") ;
    //listview object


    public DashboardFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);
        //initializing listview and hero list
        ngengg = rootView.findViewById(R.id.count);
        String pepes = "1000";
        float jebbbb = Float.parseFloat(pepes);
        getActivity().setTitle("Dashboard");


        PieChart pieChart = rootView.findViewById(R.id.chart);

        ArrayList<PieEntry> visitors = new ArrayList<>();
        visitors.add(new PieEntry(200, "Active"));
        visitors.add(new PieEntry(100, "Disable"));
        visitors.add(new PieEntry(50, "Failure"));

        PieDataSet pieDataSet = new PieDataSet(visitors, "Status");
        pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(12f);


        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.animateXY(1000,1000);
        pieChart.setEntryLabelColor(00000);
        runDetailActivity();


        //this method will fetch and parse the data

        return rootView;
    }

    public void runDetailActivity (){


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, base_url+"dcim/devices/", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONObject jsonObject = response;
                try {
                    int test = jsonObject.getInt("count");
                    ngengg.setText(String.valueOf(test));

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(),"Failed To Get Data|Internal Error", Toast.LENGTH_SHORT).show();
                }


            }

        }, error -> Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT)){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap header = new HashMap();
                header.put("Content-Type", "application/json");
                header.put("Authorization", "Token eaaa9e72d1a4f4c75aef7a7492e07785d853b6b4");

                return header;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonObjectRequest);
    }
}