package com.myshopping.myshopping.UserInterface;

import android.content.Intent;
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
        username = (EditText) findViewById(R.id.MallUserNameNewAccount);
        name = (EditText) findViewById(R.id.MallNameNewAccount);
        mall_owner_name = (EditText) findViewById(R.id.MallOwnerNameNewAccount);
        mall_owner_phone_number = (EditText) findViewById(R.id.MallOwnerPhoneNumberNewAccount);
        password = findViewById(R.id.MallPasswordNewAccount);
        confirmpassword = findViewById(R.id.MallConfirmPasswordNewAccount);
        createaccount = findViewById(R.id.MallCreateAccountButton);

        createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = password.getText().toString();
                String con_pass = confirmpassword.getText().toString();
                if(pass.equals(con_pass)){
                    String url = "localhost:5000/MallNewAccount";
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
                                        intent.putExtra("phone_number", phone_number);
                                        intent.putExtra("name", name);
                                        intent.putExtra("mall_owner_name", mall_o_name);
                                        intent.putExtra("phone_number", phone_number);
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
                            Toast.makeText(MallOwnerCreateAccount.this, "Sorry, Error" +
                                            " while creating a new account. Please try again",
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                    queue.add(request);
                }
                else{
                    Toast.makeText(MallOwnerCreateAccount.this, "Password does not match",
                            Toast.LENGTH_LONG).show();
                    password.setText("");
                    confirmpassword.setText("");
                }
            }
        });
    }
}
