package com.example.ranjith.tutor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Listdisplay extends AppCompatActivity {
    String names;
    String name;
    ArrayList<String> plist;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listdisplay);
        Intent i=getIntent();
        names=i.getStringExtra("Name");
        listView = (ListView) findViewById(R.id.listView);

    }
    public void display() {
        String tag_string_req = "req_display_details";
        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_DISPLAY_DETAIL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {


                try {
                    JSONObject det = new JSONObject(response);
                    boolean error = det.getBoolean("error");
                    if (!error) {
                        JSONObject jobj = new JSONObject(response);
                        JSONArray arrData = jobj.getJSONArray("Details");

                        for (int i = 0; i < arrData.length(); i++) {

                            JSONObject jdata = arrData.getJSONObject(i);

                            //here u can get all field like this

                             name= jdata.getString("Name");
                            plist.add(name);


                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                }

        }
                , new Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError error) {
                // Log.e(TAG, "Registration Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();

            }
        })

        {


            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("Domain", name);


                return params;
            }

        };

        ArrayAdapter adapter = new ArrayAdapter<String>(Listdisplay.this,R.layout.activity_listdisplay,plist);
        listView.setAdapter(adapter
        );

        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);


    }



}


