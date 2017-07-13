package com.example.ranjith.tutor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
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

public class studentdisplay extends AppCompatActivity {
    EditText e_domain_search;
    final ArrayList<String> domainlist=new ArrayList<>();
    String var;
    Spinner e_domain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentdisplay);
        e_domain_search=(EditText)findViewById(R.id.domain_search);
        e_domain=(Spinner)findViewById(R.id.domain);
        String tag_string_req = "req_searcharea";

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_SEARCH_DOMAIN, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {



                try {
                    JSONObject jobj = new JSONObject(response);
                    JSONArray arrData=jobj.getJSONArray("Domain");
                    boolean error =jobj.getBoolean("error");
                    if(!error)
                    {

                        Toast.makeText(getApplicationContext(),"ghgh",Toast.LENGTH_LONG).show();
                        for (int i = 0; i < arrData.length(); i++)
                        {

                            JSONObject jdata=arrData.getJSONObject(i);

                            //here u can get all field like this

                            String  area=jdata.getString("Domain");

                            domainlist.add(area);


                        }
                    }

                    else
                    {
                        Toast.makeText(getApplicationContext(),"Sry No Area available",Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                final ArrayAdapter<String> areas=new ArrayAdapter<String>(studentdisplay.this,android.R.layout.simple_list_item_1,
                        domainlist);
                e_domain.setAdapter(areas);
                e_domain.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                         var=areas.getItem(position);
                        e_domain_search.setText(var);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


            }
        }, new Response.ErrorListener() {



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

                return params;
            }

        };
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);



    }
    public void display(View v)
    {
        Intent i=new Intent(this,Listdisplay.class);
        i.putExtra("var",var);
        startActivity(i);




    }




}

