package com.example.ranjith.tutor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Student extends AppCompatActivity {
    private EditText username;
    private TextView userbgroup;
    private EditText userage;
    private EditText usermobile;
    private EditText userexp;
    private EditText userfees;
    private EditText userdomain;
    private EditText useremail;
    private EditText userpassword;
    private EditText userpassword1;
    String m_name, m_age, m_mobile, m_fees, m_exp, m_domain, m_password, m_retype, m_blood,m_email;
    String var;
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        username = (EditText) findViewById(R.id.username);
        userage = (EditText) findViewById(R.id.userage);
        usermobile = (EditText) findViewById(R.id.usermobile);
        useremail = (EditText) findViewById(R.id.useremail);
        userpassword = (EditText) findViewById(R.id.userpassword);
        userpassword1 = (EditText) findViewById(R.id.userpassword1);
    }

    public void doRegister(View view) {


        m_name = username.getText().toString();

        m_age = userage.getText().toString();
        if (m_age == null) {
            m_age = 0 + "";
        }
        m_mobile = usermobile.getText().toString();
        m_email = useremail.getText().toString();
        m_password = userpassword.getText().toString();

        m_retype = userpassword1.getText().toString();
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(m_email);

        if (matcher.find()) {
            //Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
            if (Integer.parseInt(m_age) > 18) {
                if (m_password.equals(m_retype)) {
                    if (m_password.length() >= 6) {

                        store();
                        Intent intent=new Intent(this,StudentLogin.class);
                        startActivity(intent);
                    }
                    else
                    {

                        Toast.makeText(getApplicationContext(),"Check Your Password",Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Re-enter Your Password",Toast.LENGTH_LONG).show();

                }
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Check Your Mail",Toast.LENGTH_LONG).show();
        }
    }














    public void store()
    {
        String tag_string_req = "req_register";
        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_TREGISTER, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

//Toast.makeText(getApplicationContext(),"jgkkt",Toast.LENGTH_LONG).show();
//                //Log.d(TAG, "register Response: " + response.toString());
//
//                    //JSONObject det = new JSONObject(response);
//                   // JSONObject jObj = det.getJSONObject("Details");
//
//                   /// boolean error = jObj.getBoolean("error");
//                  // Toast.makeText(getApplicationContext(),error+"",Toast.LENGTH_LONG).show();
//
//
//
//                        //name = jObj.getString("User");
//                        //email = jObj.getString("Password");
//                        Toast.makeText(getApplicationContext(),"hi",Toast.LENGTH_LONG).show();
//                        Intent i=new Intent(MainActivity.this,Main2Activity.class);
//                        i.putExtra("Name",response);
//                        startActivity(i);
//
//



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

                params.put("Name", m_name);
                params.put("Email", m_email);
                params.put("Password", m_password);
                params.put("Age", m_age);
                params.put("Mobile", m_mobile);




                return params;
            }

        };
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

    }



}



