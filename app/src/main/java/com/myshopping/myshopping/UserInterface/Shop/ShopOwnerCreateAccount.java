package com.myshopping.myshopping.UserInterface.Shop;

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

public class ShopOwnerCreateAccount extends AppCompatActivity {

    private EditText username;
    private EditText name;
    private EditText shop_owner_name;
    private EditText shop_owner_phone_number;
    private EditText password;
    private EditText confirmpassword;
    private Button createaccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_owner_create_account);
        connect_attributes();
    }

    private void connect_attributes() {
        username = findViewById(R.id.ShopUserNameNewAccount);
        name = findViewById(R.id.ShopNameNewAccount);
        shop_owner_name = findViewById(R.id.ShopOwnerNameNewAccount);
        shop_owner_phone_number = findViewById(R.id.ShopOwnerPhoneNumberNewAccount);
        password = findViewById(R.id.ShopPasswordNewAccount);
        confirmpassword = findViewById(R.id.ShopConfirmPasswordNewAccount);
        createaccount = findViewById(R.id.ShopCreateAccountButton);

        createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("")||name.getText().toString().equals("")||
                shop_owner_phone_number.getText().toString().equals("")|| shop_owner_name.getText().toString().equals("")||
                password.getText().toString().equals("")){
                    Toast.makeText(ShopOwnerCreateAccount.this, Utils.FILL_ALL_DETAILS,
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    String pass = password.getText().toString();
                    String con_pass = confirmpassword.getText().toString();
                    if (pass.equals(con_pass)) {
                        String url = Utils.SHOP_NEW_ACCOUNT;
                        RequestQueue queue = Volley.newRequestQueue(ShopOwnerCreateAccount.this);
                        JSONObject json = new JSONObject();
                        try {
                            json.put("username", username.getText().toString());
                            json.put("shop_owner_phone_number", shop_owner_phone_number.getText().toString());
                            json.put("name", name.getText().toString());
                            json.put("password", password.getText().toString());
                            json.put("shop_owner_name", shop_owner_name.getText().toString());
                        } catch (JSONException e) {
                            Toast.makeText(ShopOwnerCreateAccount.this, e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, json,
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        try {
                                            String phone_number = response.getString("shop_owner_phone_number");
                                            String name = response.getString("name");
                                            String username = response.getString("username");
                                            String shop_o_name = response.getString("shop_owner_name");
                                            Intent intent = new Intent(ShopOwnerCreateAccount.this,
                                                    ShopOwnerProfile.class);
                                            SharedPreferences preferences = getSharedPreferences
                                                    (Utils.APPLICATION_NAME, MODE_PRIVATE);
                                            preferences.edit().putString(Utils.SHOP_OWNER_PHONE_NUMBER, phone_number).apply();
                                            preferences.edit().putString(Utils.SHOP_NAME, name).apply();
                                            preferences.edit().putString(Utils.SHOP_PASSWORD, password.getText().toString()).apply();
                                            preferences.edit().putString(Utils.LAST_LOGIN, "shop").apply();
                                            preferences.edit().putString(Utils.SHOP_OWNER_NAME, shop_o_name).apply();
                                            preferences.edit().putString(Utils.SHOP_USERNAME, username).apply();
                                            startActivity(intent);
                                            finish();
                                        } catch (JSONException e) {
                                            Toast.makeText(ShopOwnerCreateAccount.this,
                                                    e.getMessage(), Toast.LENGTH_LONG).show();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(ShopOwnerCreateAccount.this, Utils.ERROR_CREATING_NEW_ACCOUNT,
                                        Toast.LENGTH_LONG).show();
                            }
                        });
                        queue.add(request);
                    } else {
                        Toast.makeText(ShopOwnerCreateAccount.this, Utils.PASSWORD_NOT_MATCH,
                                Toast.LENGTH_LONG).show();
                        password.setText("");
                        confirmpassword.setText("");
                    }
                }
            }
        });
    }
}
