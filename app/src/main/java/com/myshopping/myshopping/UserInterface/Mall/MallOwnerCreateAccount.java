package com.myshopping.myshopping.UserInterface.Mall;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class MallOwnerCreateAccount extends AppCompatActivity {

    private EditText username;
    private EditText name;
    private EditText mall_owner_name;
    private EditText mall_owner_phone_number;
    private EditText password;
    private EditText confirmpassword;
    private Button createaccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall_owner_create_account);
        connect_attributes();
    }

    private void connect_attributes() {
        username = findViewById(R.id.MallUserNameNewAccount);
        name = findViewById(R.id.MallNameNewAccount);
        mall_owner_name = findViewById(R.id.MallOwnerNameNewAccount);
        mall_owner_phone_number = findViewById(R.id.MallOwnerPhoneNumberNewAccount);
        password = findViewById(R.id.MallPasswordNewAccount);
        confirmpassword = findViewById(R.id.MallConfirmPasswordNewAccount);
        createaccount = findViewById(R.id.MallCreateAccountButton);

        createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("")||name.getText().toString().equals("")||
                mall_owner_name.getText().toString().equals("")||mall_owner_phone_number.getText().toString().equals("")||
                password.getText().toString().equals("")){
                    Toast.makeText(MallOwnerCreateAccount.this, Utils.FILL_ALL_DETAILS,
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    String pass = password.getText().toString();
                    String con_pass = confirmpassword.getText().toString();
                    if (pass.equals(con_pass)) {
                        String url = Utils.MALL_NEW_ACCOUNT;
                        RequestQueue queue = Volley.newRequestQueue(MallOwnerCreateAccount.this);
                        JSONObject json = new JSONObject();
                        try {
                            json.put("username", username.getText().toString());
                            json.put("name", name.getText().toString());
                            json.put("mall_owner_name", mall_owner_name.getText().toString());
                            json.put("mall_owner_phone_number", mall_owner_phone_number.getText().toString());
                            json.put("password", password.getText().toString());
                        } catch (JSONException e) {
                            Toast.makeText(MallOwnerCreateAccount.this, e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, json,
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        try {
                                            String phone_number = response.getString("mall_owner_phone_number");
                                            String mall_o_name = response.getString("mall_owner_name");
                                            String username = response.getString("username");
                                            String name = response.getString("name");
                                            Intent intent = new Intent(MallOwnerCreateAccount.this,
                                                    MallOwnerProfile.class);
                                            SharedPreferences preferences = getSharedPreferences
                                                    (Utils.APPLICATION_NAME, MODE_PRIVATE);
                                            preferences.edit().putString(Utils.MALL_OWNER_PHONE_NUMBER, phone_number).apply();
                                            preferences.edit().putString(Utils.MALL_NAME, name).apply();
                                            preferences.edit().putString(Utils.MALL_PASSWORD, password.getText().toString()).apply();
                                            preferences.edit().putString(Utils.LAST_LOGIN, "mall").apply();
                                            preferences.edit().putString(Utils.MALL_OWNER_NAME, mall_o_name).apply();
                                            preferences.edit().putString(Utils.MALL_USERNAME, username).apply();
                                            startActivity(intent);
                                            finish();
                                        } catch (JSONException e) {
                                            Toast.makeText(MallOwnerCreateAccount.this,
                                                    e.getMessage(), Toast.LENGTH_LONG).show();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MallOwnerCreateAccount.this, Utils.ERROR_CREATING_NEW_ACCOUNT,
                                        Toast.LENGTH_LONG).show();
                            }
                        });
                        queue.add(request);
                    } else {
                        Toast.makeText(MallOwnerCreateAccount.this, Utils.PASSWORD_NOT_MATCH,
                                Toast.LENGTH_LONG).show();
                        password.setText("");
                        confirmpassword.setText("");
                    }
                }
            }
        });
    }
}
