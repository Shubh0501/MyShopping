package com.myshopping.myshopping.UserInterface.Customer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.myshopping.myshopping.R;
import com.myshopping.myshopping.Util.Utils;

import org.json.JSONException;
import org.json.JSONObject;

public class CustomerCreateNewAccount extends AppCompatActivity {

    private EditText customername;
    private EditText customerphonenumber;
    private EditText customerpassword;
    private EditText customerconfirmpassword;
    private Button customercreateaccountbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_create_new_account);
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        connect_attributes();
    }

    private void connect_attributes() {
        customername = (EditText) findViewById(R.id.CustomerNameNewAccount);
        customerphonenumber = (EditText) findViewById(R.id.CustomerPhoneNumberNewAccount);
        customerpassword = (EditText) findViewById(R.id.CustomerPasswordNewAccount);
        customerconfirmpassword = (EditText) findViewById(R.id.CustomerConfirmPasswordNewAccount);
        customercreateaccountbutton = (Button) findViewById(R.id.CustomerCreateAccountButton);
        customercreateaccountbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(customername.getText().toString().equals("")||customerphonenumber.getText().toString().equals("")||
                customerpassword.getText().toString().equals("")){
                    Toast.makeText(CustomerCreateNewAccount.this, Utils.FILL_ALL_DETAILS,
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    String pass = customerpassword.getText().toString();
                    String con_pass = customerconfirmpassword.getText().toString();
                    if (pass.equals(con_pass)) {
                        String url = Utils.CUSTOMER_NEW_ACCOUNT;
                        RequestQueue queue = Volley.newRequestQueue(CustomerCreateNewAccount.this);
                        JSONObject json = new JSONObject();
                        try {
                            json.put("phone_number", customerphonenumber.getText().toString());
                            json.put("name", customername.getText().toString());
                            json.put("password", customerpassword.getText().toString());
                        } catch (JSONException e) {
                            Toast.makeText(CustomerCreateNewAccount.this, e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, json,
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        try {
                                            String phone_number = response.getString("phone_number");
                                            String name = response.getString("name");
                                            Intent intent = new Intent(CustomerCreateNewAccount.this,
                                                    CustomerProfile.class);
                                            SharedPreferences preferences = getSharedPreferences(Utils.APPLICATION_NAME, MODE_PRIVATE);
                                            preferences.edit().putString(Utils.CUSTOMER_PHONE_NUMBER, phone_number).apply();
                                            preferences.edit().putString(Utils.CUSTOMER_NAME, name).apply();
                                            preferences.edit().putString(Utils.CUSTOMER_PASSWORD, customerpassword.getText().toString()).apply();
                                            preferences.edit().putString(Utils.LAST_LOGIN, "customer").apply();
                                            startActivity(intent);
                                            finish();
                                        } catch (JSONException e) {
                                            Toast.makeText(CustomerCreateNewAccount.this,
                                                    e.getMessage(), Toast.LENGTH_LONG).show();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(CustomerCreateNewAccount.this, Utils.ERROR_CREATING_NEW_ACCOUNT,
                                        Toast.LENGTH_SHORT).show();

                            }
                        });
                        request.setRetryPolicy
                                (new DefaultRetryPolicy(5000, DefaultRetryPolicy.
                                        DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                        queue.add(request);
                    } else {
                        Toast.makeText(CustomerCreateNewAccount.this, Utils.PASSWORD_NOT_MATCH,
                                Toast.LENGTH_SHORT).show();
                        customerpassword.setText("");
                        customerconfirmpassword.setText("");
                    }
                }
            }
        });
    }
}