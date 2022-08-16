package com.smktelkommlg.prakerinlistview;


import static com.smktelkommlg.prakerinlistview.DashboardFragment.base_url;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import at.markushi.ui.CircleButton;

public class DetailActivity extends AppCompatActivity {

    FloatingActionButton tombol;
    FloatingActionButton tombolDelete;
    AlertDialog.Builder dialog;
    AlertDialog.Builder dialogDelete;
    AlertDialog dialog2;
    AlertDialog dialogDelete2;
    LayoutInflater inflater;
    LayoutInflater inflaterDelete;
    View dialogView;
    View dialogViewDelete;
    Button cancel;
    Button simpan;
    Button cancelDelete;
    Button delete;
    String[] status = {"offline", "active", "planned", "staged", "failed", "inventory", "decommissioning"};

    int idDetail;
    String name;
    String model;
    String role;
    String tenant;
    String tenant2;
    String platform;
    String serial;
    String asset;
    String site;
    String location;
    String location2;
    String rack;
    String rack2;
    String status1;
    String airflow;
    String airflow2;
    String primaryIp4;
    String primaryIp4_2;
    String primaryIp6;
    String face;
    String face2;

    String URLline = "https://siip.ub.ac.id/api/dcim/devices/";
    String urlDeviceType = "https://siip.ub.ac.id/api/dcim/device-types/?limit=1000";
    String urlRole = "https://siip.ub.ac.id/api/dcim/device-roles/";
    String urlTenant = "https://siip.ub.ac.id/api/tenancy/tenants/";
    String urlPlatform = "https://siip.ub.ac.id/api/dcim/platforms/";
    String urlSite = "https://siip.ub.ac.id/api/dcim/sites/?limit=1000";
    String urlLocation = "https://siip.ub.ac.id/api/dcim/locations/?limit=1000";
    String urlRack = "https://siip.ub.ac.id/api/dcim/racks/?limit=1000";

    public String username;
    public String serialId;
    public String statusId;
    public String deviceTypeId;
    public String roleId;
    public String tenantId;
    public String platformId;
    public String rackId;
    public String siteId;
    public String locationId;

    public int cek1;
    public String cek2;
    public int cek3;
    public String cek4;
    public int cek5;
    public String cek6;
    public int cek7;
    public String cek8;
    public int cek9;
    public String cek10;
    public int cek11;
    public String cek12;

    ArrayList<String> listNamaDeviceType = new ArrayList<>();
    ArrayList<String> listIdDeviceType = new ArrayList<>();
    ArrayList<String> listNamaRole = new ArrayList<>();
    ArrayList<String> listIdRole = new ArrayList<>();
    ArrayList<String> listNamaTenant = new ArrayList<>();
    ArrayList<String> listIdTenant = new ArrayList<>();
    ArrayList<String> listNamaPlatform = new ArrayList<>();
    ArrayList<String> listIdPlatform = new ArrayList<>();
    ArrayList<String> listNamaSite = new ArrayList<>();
    ArrayList<String> listIdSite = new ArrayList<>();
    ArrayList<String> listNamaLocation = new ArrayList<>();
    ArrayList<String> listIdLocation = new ArrayList<>();
    ArrayList<String> listNamaLocationHandler = new ArrayList<>();
    ArrayList<String> listIdLocationHandler = new ArrayList<>();
    ArrayList<String> listNamaRack = new ArrayList<>();
    ArrayList<String> listIdRack = new ArrayList<>();
    ArrayList<String> listNamaRackHandler = new ArrayList<>();
    ArrayList<String> listIdRackHandler = new ArrayList<>();

    public static AutoCompleteTextView autoCompleteDeviceName;
    public static AutoCompleteTextView autoCompleteType;
    public static AutoCompleteTextView autoCompleteRole;
    public static AutoCompleteTextView autoCompleteTenant;
    public static AutoCompleteTextView autoCompletePlatform;
    public static AutoCompleteTextView autoCompleteSerial;
    public static AutoCompleteTextView autoCompleteTag;
    public static AutoCompleteTextView autoCompleteSite;
    public static AutoCompleteTextView autoCompleteLocation;
    public static AutoCompleteTextView autoCompleteRack;
    public static AutoCompleteTextView autoCompleteStatus;
    public static AutoCompleteTextView autoCompleteAirflow;
    public static AutoCompleteTextView autoCompleteIPv4;
    public static AutoCompleteTextView autoCompleteIPv6;
    
    TextInputLayout inputDeviceName;
    TextInputLayout inputDeviceType;
    TextInputLayout inputDeviceRole;
    TextInputLayout inputDeviceTenant;
    TextInputLayout inputDevicePlatform;
    TextInputLayout inputDeviceSerial;
    TextInputLayout inputDeviceTag;
    TextInputLayout inputDeviceSite;
    TextInputLayout inputDeviceLocation;
    TextInputLayout inputDeviceRack;
    TextInputLayout inputDeviceStatus;
    TextInputLayout inputDeviceAirflow;
    TextInputLayout inputDeviceIPv4;
    TextInputLayout inputDeviceIPv6;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
            this.setTitle("Detail");
            tombol = findViewById(R.id.tombolUpdate);
            tombolDelete = findViewById(R.id.tombolDelete);
            autoCompleteStatus = findViewById(R.id.autoCompleteDeviceStatusPUT);
            runDetailActivity();


            tombol.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    runUpdate();
                    showDataRole();
                    showDataType();
                    showDataTenant();
                    showDataPlatform();
                    showDataSite();
                    showDataStatus();
                    handlerSelectLocation();
                    handlerSelectRack();

                    autoCompleteDeviceName.setText(name);
                    autoCompleteType.setText(model);
                    autoCompleteRole.setText(role);
                    autoCompleteSite.setText(site);
                    autoCompleteStatus.setText(status1);

                    if (tenant.equals("null")){autoCompleteTenant.setText("null");
                    }else{autoCompleteTenant.setText(tenant2);}

                    if (serial.equals("")){autoCompleteSerial.setText("null");
                    }else{autoCompleteSerial.setText(serial);}

                    if (asset.equals("null")){autoCompleteTag.setText("null");
                    }else{autoCompleteTag.setText(asset);}

                    if (location.equals("null")){autoCompleteLocation.setText("null");
                    }else{autoCompleteLocation.setText(location2);}

                    if (rack.equals("null")){autoCompleteRack.setText("null");
                    }else{autoCompleteRack.setText(rack2);}

                    if (airflow.equals("null")){autoCompleteAirflow.setText("null");
                    }else{autoCompleteAirflow.setText(airflow2);}

                    if (primaryIp4.equals("null")){autoCompleteIPv4.setText("null");
                    }else{autoCompleteIPv4.setText(primaryIp4_2);}

                    if (primaryIp6.equals("null")){autoCompleteIPv6.setText("null");
                    }else{autoCompleteIPv6.setText(primaryIp6);}


                }
            });    
    
            tombolDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    runDelete();
                }
            });
    }

    private void runDelete() {
        dialogDelete = new AlertDialog.Builder(DetailActivity.this);
        inflaterDelete = getLayoutInflater();
        dialogViewDelete = inflaterDelete.inflate(R.layout.delete_dialog, null);
        dialogDelete.setView(dialogViewDelete);
        dialogDelete2 = dialogDelete.create();
        dialogDelete2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cancelDelete = dialogViewDelete.findViewById(R.id.cancelDelete);
        delete = dialogViewDelete.findViewById(R.id.delete);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteUser();
                dialogDelete2.dismiss();
//                finish();
//                overridePendingTransition( 0, 0);
//                startActivity(getIntent());
//                overridePendingTransition( 0, 0);

            }
        });

        cancelDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDelete2.dismiss();
            }
        });

        dialogDelete2.show();


    }

    private void deleteUser() {
        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, URLline + idDetail +"/",
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(DetailActivity.this,"berhasil",Toast.LENGTH_LONG).show();


                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(DetailActivity.this,"Error",Toast.LENGTH_LONG).show();
                }
            }){
        @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            HashMap header = new HashMap();
            header.put("Content-Type", "application/json; charset=utf-8");
            header.put("Authorization", "Token eaaa9e72d1a4f4c75aef7a7492e07785d853b6b4");
            header.put("Accept", "application/json");
            return header;
        }
        @Override
        public byte[] getBody() throws AuthFailureError {
            try {
                // request body goes here
                JSONObject body = new JSONObject();
                body.put("name", username);
                body.put("device_type", cek4);
                body.put("device_role", cek2);
                body.put("tenant", cek8);
                body.put("platform", null);
                body.put("serial", serialId);
                body.put("asset_tag", null);
                body.put("site", cek6);
                body.put("location", cek12);
                body.put("rack", cek10);
                body.put("position", null);
                body.put("parent_device", null);
                body.put("status", statusId);
                body.put("airflow", null);
                body.put("primary_ip4", null);
                body.put("primary_ip6", null);

                String requestBody = body.toString();
                return requestBody.getBytes("utf-8");
            } catch (UnsupportedEncodingException | JSONException uee) {
                VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", "utf-8");
                return null;
            }
        }
    };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showDataStatus() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.dropdown_item, status);
        autoCompleteStatus.setAdapter(adapter);
        autoCompleteStatus.setThreshold(1);
    }

    private void showDataRack(String dataLocation) {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlRack + "&location=" + dataLocation, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    autoCompleteRack.setEnabled(true);
                    inputDeviceRack.setEnabled(true);
                    autoCompleteRack.setText("");
                    JSONObject object = new JSONObject(response);
                    JSONArray array = object.getJSONArray("results");
                    for (int i = 0; i < array.length(); i++){
                        JSONObject jsonObject = array.getJSONObject(i);
                        String name = jsonObject.getString("name");
                        String id = jsonObject.getString("id");
                        listIdRack.add(id);
                        listNamaRack.add(name);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(DetailActivity.this, R.layout.dropdown_item, listNamaRack);
                    autoCompleteRack.setAdapter(arrayAdapter);
                    autoCompleteRack.setThreshold(1);
                    autoCompleteRack.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                autoCompleteRack.setEnabled(false);
                inputDeviceRack.setEnabled(false);

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap header = new HashMap();
                header.put("Content-Type", "application/json; charset=utf-8");
                header.put("Authorization", "Token eaaa9e72d1a4f4c75aef7a7492e07785d853b6b4");
                header.put("Accept", "application/json");
                return header;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showDataLocation(String dataSite) {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlLocation + "&site=" + dataSite, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    autoCompleteLocation.setEnabled(true);
                    inputDeviceLocation.setEnabled(true);
                    autoCompleteLocation.setText("");
                    JSONObject object = new JSONObject(response);
                    JSONArray array = object.getJSONArray("results");
                    for (int i = 0; i < array.length(); i++){
                        JSONObject jsonObject = array.getJSONObject(i);
                        String name = jsonObject.getString("name");
                        String id = jsonObject.getString("id");
                        listIdLocation.add(id);
                        listNamaLocation.add(name);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(DetailActivity.this, R.layout.dropdown_item, listNamaLocation);
                    autoCompleteLocation.setAdapter(arrayAdapter);
                    autoCompleteLocation.setThreshold(1);
                    autoCompleteLocation.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            listIdRack.clear();
                            listNamaRack.clear();
                            String location_slug = s.toString().toLowerCase(Locale.ROOT).replace(" ", "-");
                            showDataRack(location_slug);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                autoCompleteLocation.setEnabled(false);
                inputDeviceLocation.setEnabled(false);

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap header = new HashMap();
                header.put("Content-Type", "application/json; charset=utf-8");
                header.put("Authorization", "Token eaaa9e72d1a4f4c75aef7a7492e07785d853b6b4");
                header.put("Accept", "application/json");
                return header;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showDataSite() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlSite, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray array = object.getJSONArray("results");
                    for (int i = 0; i < array.length(); i++){
                        JSONObject jsonObject = array.getJSONObject(i);
                        String name = jsonObject.getString("name");
                        String id = jsonObject.getString("id");
                        listIdSite.add(id);
                        listNamaSite.add(name);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(DetailActivity.this, R.layout.dropdown_item, listNamaSite);
                    autoCompleteSite.setAdapter(arrayAdapter);
                    autoCompleteSite.setThreshold(1);
                    autoCompleteSite.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            listIdLocation.clear();
                            listNamaLocation.clear();
                            listIdRack.clear();
                            listNamaRack.clear();
                            String site_slug = s.toString().toLowerCase(Locale.ROOT).replace(" ", "-");
                            showDataLocation(site_slug);


                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap header = new HashMap();
                header.put("Content-Type", "application/json; charset=utf-8");
                header.put("Authorization", "Token eaaa9e72d1a4f4c75aef7a7492e07785d853b6b4");
                header.put("Accept", "application/json");
                return header;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void handlerSelectLocation(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlLocation, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray array = object.getJSONArray("results");
                    for (int i = 0; i < array.length(); i++){
                        JSONObject jsonObject = array.getJSONObject(i);
                        String name = jsonObject.getString("name");
                        String id = jsonObject.getString("id");
                        listIdLocationHandler.add(id);
                        listNamaLocationHandler.add(name);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap header = new HashMap();
                header.put("Content-Type", "application/json; charset=utf-8");
                header.put("Authorization", "Token eaaa9e72d1a4f4c75aef7a7492e07785d853b6b4");
                header.put("Accept", "application/json");
                return header;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void handlerSelectRack(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlRack, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray array = object.getJSONArray("results");
                    for (int i = 0; i < array.length(); i++){
                        JSONObject jsonObject = array.getJSONObject(i);
                        String name = jsonObject.getString("name");
                        String id = jsonObject.getString("id");
                        listIdRackHandler.add(id);
                        listNamaRackHandler.add(name);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap header = new HashMap();
                header.put("Content-Type", "application/json; charset=utf-8");
                header.put("Authorization", "Token eaaa9e72d1a4f4c75aef7a7492e07785d853b6b4");
                header.put("Accept", "application/json");
                return header;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showDataPlatform() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlPlatform, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listIdPlatform.clear();
                listNamaPlatform.clear();
                try {
                    JSONObject object = new JSONObject(response);
                    String jumlah = object.getString("count");
                    if(jumlah.equals("0")){
                        autoCompletePlatform.setText("Tidak ada data");
                        autoCompletePlatform.setEnabled(false);
                    }
                    JSONArray array = object.getJSONArray("results");
                    for (int i = 0; i < array.length(); i++){
                        JSONObject jsonObject = array.getJSONObject(i);
                        String name = jsonObject.getString("name");
                        String id = jsonObject.getString("id");
                        listIdPlatform.add(id);
                        listNamaPlatform.add(name);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(DetailActivity.this, R.layout.dropdown_item, listNamaPlatform);
                    autoCompletePlatform.setAdapter(arrayAdapter);
                    autoCompletePlatform.setThreshold(1);
                    autoCompletePlatform.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            platformId = listIdPlatform.get(position);
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap header = new HashMap();
                header.put("Content-Type", "application/json; charset=utf-8");
                header.put("Authorization", "Token eaaa9e72d1a4f4c75aef7a7492e07785d853b6b4");
                header.put("Accept", "application/json");
                return header;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showDataTenant() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlTenant, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listIdTenant.clear();
                listNamaTenant.clear();
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray array = object.getJSONArray("results");
                    for (int i = 0; i < array.length(); i++){
                        JSONObject jsonObject = array.getJSONObject(i);
                        String name = jsonObject.getString("name");
                        String id = jsonObject.getString("id");
                        listIdTenant.add(id);
                        listNamaTenant.add(name);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(DetailActivity.this, R.layout.dropdown_item, listNamaTenant);
                    autoCompleteTenant.setAdapter(arrayAdapter);
                    autoCompleteTenant.setThreshold(1);
                    autoCompleteTenant.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            tenantId = listIdTenant.get(position);
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap header = new HashMap();
                header.put("Content-Type", "application/json; charset=utf-8");
                header.put("Authorization", "Token eaaa9e72d1a4f4c75aef7a7492e07785d853b6b4");
                header.put("Accept", "application/json");
                return header;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showDataType() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlDeviceType, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listIdDeviceType.clear();
                listNamaDeviceType.clear();
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray array = object.getJSONArray("results");
                    for (int i = 0; i < array.length(); i++){
                        JSONObject jsonObject = array.getJSONObject(i);
                        String name = jsonObject.getString("display");
                        String id = jsonObject.getString("id");
                        listIdDeviceType.add(id);
                        listNamaDeviceType.add(name);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(DetailActivity.this, R.layout.dropdown_item, listNamaDeviceType);
                    autoCompleteType.setAdapter(arrayAdapter);
                    autoCompleteType.setThreshold(1);
                    autoCompleteType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            deviceTypeId = listIdDeviceType.get(position);
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap header = new HashMap();
                header.put("Content-Type", "application/json; charset=utf-8");
                header.put("Authorization", "Token eaaa9e72d1a4f4c75aef7a7492e07785d853b6b4");
                header.put("Accept", "application/json");
                return header;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showDataRole() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlRole, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listIdRole.clear();
                listNamaRole.clear();
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray array = object.getJSONArray("results");
                    for (int i = 0; i < array.length(); i++){
                        JSONObject jsonObject = array.getJSONObject(i);
                        String name = jsonObject.getString("name");
                        String id = jsonObject.getString("id");
                        listIdRole.add(id);
                        listNamaRole.add(name);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(DetailActivity.this, R.layout.dropdown_item, listNamaRole);
                    autoCompleteRole.setAdapter(arrayAdapter);
                    autoCompleteRole.setThreshold(1);
                    autoCompleteRole.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            roleId = listIdRole.get(position);
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap header = new HashMap();
                header.put("Content-Type", "application/json; charset=utf-8");
                header.put("Authorization", "Token eaaa9e72d1a4f4c75aef7a7492e07785d853b6b4");
                header.put("Accept", "application/json");
                return header;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void runUpdate() {
        dialog = new AlertDialog.Builder(DetailActivity.this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.update_dialog, null);
        dialog.setView(dialogView);
        dialog2 = dialog.create();
        dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cancel = dialogView.findViewById(R.id.cancel);
        simpan = dialogView.findViewById(R.id.simpan);

        autoCompleteDeviceName = dialogView.findViewById(R.id.autoCompleteDeviceNamePUT);
        autoCompleteType = dialogView.findViewById(R.id.autoCompleteDeviceTypePUT);
        autoCompleteRole = dialogView.findViewById(R.id.autoCompleteDeviceRolePUT);
        autoCompleteTenant = dialogView.findViewById(R.id.autoCompleteDeviceTenantPUT);
        autoCompletePlatform = dialogView.findViewById(R.id.autoCompleteDevicePlatformPUT);
        autoCompleteSerial = dialogView.findViewById(R.id.autoCompleteDeviceSerialPUT);
        autoCompleteTag = dialogView.findViewById(R.id.autoCompleteDeviceTagPUT);
        autoCompleteSite = dialogView.findViewById(R.id.autoCompleteDeviceSitePUT);
        autoCompleteLocation = dialogView.findViewById(R.id.autoCompleteDeviceLocationPUT);
        autoCompleteRack = dialogView.findViewById(R.id.autoCompleteDeviceRackPUT);
        autoCompleteStatus = dialogView.findViewById(R.id.autoCompleteDeviceStatusPUT);
        autoCompleteAirflow = dialogView.findViewById(R.id.autoCompleteDeviceAirflowPUT);
        autoCompleteIPv4 = dialogView.findViewById(R.id.autoCompleteDeviceIPv4PUT);
        autoCompleteIPv6 = dialogView.findViewById(R.id.autoCompleteDeviceIPv6PUT);

        inputDeviceName = dialogView.findViewById(R.id.inputdeviceNamePUT);
        inputDeviceType = dialogView.findViewById(R.id.inputdeviceTypePUT);
        inputDeviceRole = dialogView.findViewById(R.id.inputdeviceRolePUT);
        inputDeviceTenant = dialogView.findViewById(R.id.inputdeviceTenantPUT);
        inputDevicePlatform = dialogView.findViewById(R.id.inputdevicePlatformPUT);
        inputDeviceSerial = dialogView.findViewById(R.id.inputdeviceSerialPUT);
        inputDeviceTag = dialogView.findViewById(R.id.inputdeviceTagPUT);
        inputDeviceSite = dialogView.findViewById(R.id.inputdeviceSitePUT);
        inputDeviceLocation = dialogView.findViewById(R.id.inputdeviceLocationPUT);
        inputDeviceRack = dialogView.findViewById(R.id.inputdeviceRackPUT);
        inputDeviceStatus = dialogView.findViewById(R.id.inputdeviceStatusPUT);
        inputDeviceAirflow = dialogView.findViewById(R.id.inputdeviceAirflowPUT);
        inputDeviceIPv4 = dialogView.findViewById(R.id.inputdeviceIPv4PUT);
        inputDeviceIPv6 = dialogView.findViewById(R.id.inputdeviceIPv6PUT);



        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
                dialog2.dismiss();
                finish();
                overridePendingTransition( 0, 0);
                startActivity(getIntent());
                overridePendingTransition( 0, 0);

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog2.dismiss();
            }
        });

        dialog2.show();
        dialog2.getWindow().setLayout(900,1700);

    }

    private void loginUser() {
        username = autoCompleteDeviceName.getText().toString();
        serialId = autoCompleteSerial.getText().toString();
//        asset = autoCompleteTag.getText().toString();
        statusId = autoCompleteStatus.getText().toString();
        deviceTypeId = autoCompleteType.getText().toString();
        roleId = autoCompleteRole.getText().toString();
        siteId = autoCompleteSite.getText().toString();
        tenantId = autoCompleteTenant.getText().toString();
        rackId = autoCompleteRack.getText().toString();
        locationId = autoCompleteLocation.getText().toString();
        cek1 = listNamaRole.indexOf(roleId);
        cek2 = listIdRole.get(cek1);
        cek3 = listNamaDeviceType.indexOf(deviceTypeId);
        cek4 = listIdDeviceType.get(cek3);
        cek5 = listNamaSite.indexOf(siteId);
        cek6 = listIdSite.get(cek5);
        cek7 = listNamaTenant.indexOf(tenantId);
        cek8 = listIdTenant.get(cek7);
        cek9 = listNamaRackHandler.indexOf(rackId);
        cek10 = listIdRackHandler.get(cek9);
        cek11 = listNamaLocationHandler.indexOf(locationId);
        cek12 = listIdLocationHandler.get(cek11);

        StringRequest stringRequest = new StringRequest(Request.Method.PUT, URLline + idDetail +"/",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(DetailActivity.this,"berhasil",Toast.LENGTH_LONG).show();
                        Log.d("ini berhasil: ", response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DetailActivity.this,"Error",Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap header = new HashMap();
                header.put("Content-Type", "application/json; charset=utf-8");
                header.put("Authorization", "Token eaaa9e72d1a4f4c75aef7a7492e07785d853b6b4");
                header.put("Accept", "application/json");
                return header;
            }
            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    // request body goes here
                    JSONObject body = new JSONObject();
                    body.put("name", username);
                    body.put("device_type", cek4);
                    body.put("device_role", cek2);
                    body.put("tenant", cek8);
                    body.put("platform", null);
                    body.put("serial", serialId);
                    body.put("asset_tag", null);
                    body.put("site", cek6);
                    body.put("location", cek12);
                    body.put("rack", cek10);
                    body.put("position", null);
                    body.put("parent_device", null);
                    body.put("status", statusId);
                    body.put("airflow", null);
                    body.put("primary_ip4", null);
                    body.put("primary_ip6", null);

                    String requestBody = body.toString();
                    return requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException | JSONException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", "utf-8");
                    return null;
                }
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void runDetailActivity() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, base_url+"dcim/devices?id="+getIntent().getStringExtra("idMoveIntent"), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("results");

                    for (int i = 0; i < jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                         idDetail = jsonObject.getInt("id");
                        TextView id = findViewById(R.id.textViewId1);
                        id.setText(String.valueOf(idDetail));

                         name = jsonObject.getString("name");
                        TextView name1 = findViewById(R.id.textViewName1);
                        name1.setText(name);

                        String manufacturer1 = jsonObject.getJSONObject("device_type").getJSONObject("manufacturer").getString("name");
                        TextView manufacturer2 = findViewById(R.id.textViewManufacturer1);
                        manufacturer2.setText(manufacturer1);

                         model = jsonObject.getJSONObject("device_type").getString("model");
                        TextView model1 = findViewById(R.id.textViewModel1);
                        model1.setText(model);

                        role = jsonObject.getJSONObject("device_role").getString("name");
                        TextView role1 = findViewById(R.id.textViewRole);
                        role1.setText(role);

                         status1 = jsonObject.getJSONObject("status").getString("value");
                        TextView status2 = findViewById(R.id.textViewStatus);
                        status2.setText(status1);

                         platform = jsonObject.getString("platform");
                        TextView platform1 = findViewById(R.id.textViewPlatform1);
                        if (platform.equals("null")){
                            platform1.setText("Null");
                        }else{
                            platform1.setText(platform);
                        }

                         site = jsonObject.getJSONObject("site").getString("name");
                        TextView site1 = findViewById(R.id.textViewSite1);
                        site1.setText(site);

                         tenant = jsonObject.getString("tenant");
                        TextView tenant1 = findViewById(R.id.textViewTenant1);
                        if (tenant.equals("null")){
                            tenant1.setText("Null");
                        }else{
                             tenant2 = jsonObject.getJSONObject("tenant").getString("name");
                            tenant1.setText(tenant2);
                        }

                         asset = jsonObject.getString("asset_tag");
                        TextView asset1 = findViewById(R.id.textViewAsset1);
                        if (asset.equals("null")){
                            asset1.setText("Null");
                        }else{
                            asset1.setText(asset);
                        }

                         serial = jsonObject.getString("serial");
                        TextView serial1 = findViewById(R.id.textViewSerial1);
                        if (serial.equals("")){
                            serial1.setText("Null");
                        }else{
                            serial1.setText(serial);
                        }

                        TextView location1 = findViewById(R.id.textViewLocation1);
                         location = jsonObject.getString("location");
                        if (location.equals("null")){
                            location1.setText("Null");
                        }else{
                             location2 = jsonObject.getJSONObject("location").getString("name");
                            location1.setText(location2);
                        }

                         rack = jsonObject.getString("rack");
                        TextView rack1 = findViewById(R.id.textViewRack1);
                        if (rack.equals("null")){
                            rack1.setText("Null");
                        }else{
                             rack2 = jsonObject.getJSONObject("rack").getString("name");
                            rack1.setText(rack2);
                        }

                        String position = jsonObject.getString("position");
                        TextView position1 = findViewById(R.id.textViewPosition1);
                        if (position.equals("null")){
                            position1.setText("Null");
                        }else{
                            position1.setText(position);
                        }

                        String parentDevice = jsonObject.getString("parent_device");
                        TextView parentDevice1 = findViewById(R.id.textViewParentDevice1);
                        if (parentDevice.equals("null")){
                            parentDevice1.setText("Null");
                        }else{
                            String parentDevice2 = jsonObject.getJSONObject("parent_device").getString("name");
                            parentDevice1.setText(parentDevice2);
                        }

                         face = jsonObject.getString("face");
                        TextView face1 = findViewById(R.id.textViewFace1);
                        if (face.equals("null")){
                            face1.setText("Null");
                        }else{
                             face2 = jsonObject.getJSONObject("face").getString("value");
                            face1.setText(face2);
                        }

                         airflow = jsonObject.getString("airflow");
                        TextView airflow1 = findViewById(R.id.textViewAirflow1);
                        if (airflow.equals("null")){
                            airflow1.setText("Null");
                        }else{
                             airflow2 = jsonObject.getJSONObject("airflow").getString("label");
                            airflow1.setText(airflow2);
                        }

                        String primaryIp = jsonObject.getString("primary_ip");
                        TextView primaryIp1 = findViewById(R.id.textViewPrimaryIp1);
                        if (primaryIp.equals("null")){
                            primaryIp1.setText("Null");
                        }else{
                            String primaryIp2 = jsonObject.getJSONObject("primary_ip").getString("display");
                            primaryIp1.setText(primaryIp2);
                        }

                         primaryIp4 = jsonObject.getString("primary_ip4");
                        TextView primaryIp4_1 = findViewById(R.id.textViewPrimaryIp4_1);
                        if (primaryIp4.equals("null")){
                            primaryIp4_1.setText("Null");
                        }else{
                             primaryIp4_2 = jsonObject.getJSONObject("primary_ip4").getString("display");
                            primaryIp4_1.setText(primaryIp4_2);
                        }

                         primaryIp6 = jsonObject.getString("primary_ip6");
                        TextView primaryIp6_1 = findViewById(R.id.textViewPrimaryIp6_1);
                        if (primaryIp6.equals("null")){
                            primaryIp6_1.setText("Null");
                        }else{
                            primaryIp6_1.setText(primaryIp6);
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DetailActivity.this, error.getMessage(), Toast.LENGTH_SHORT);
            }

        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap header = new HashMap();
                header.put("Content-Type", "application/json");
                header.put("Authorization","Token eaaa9e72d1a4f4c75aef7a7492e07785d853b6b4");

                return header;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }

}

