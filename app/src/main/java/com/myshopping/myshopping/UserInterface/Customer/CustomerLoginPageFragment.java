package com.myshopping.myshopping.UserInterface.Customer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.myshopping.myshopping.AdatptersAndInterface.CallBackInterface;
import com.myshopping.myshopping.R;
import com.myshopping.myshopping.Util.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import static com.myshopping.myshopping.R.id.CustomerLoginButton;
import static com.myshopping.myshopping.R.id.shortcut;
import static com.myshopping.myshopping.R.id.start;

public class CustomerLoginPageFragment extends Fragment implements View.OnClickListener {

    private View rootview;
    private EditText username;
    private EditText password;
    private Button loginbutton;
    private Button createnewaccountbutton;
    private CallBackInterface callBackInterface;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_customer_login_page, container, false);
        connect_attributes();
        return rootview;
    }

    private void connect_attributes() {
        username = rootview.findViewById(R.id.CustomerUsernameEditText);
        password = rootview.findViewById(R.id.CustomerPasswordEditText);
        loginbutton = rootview.findViewById(CustomerLoginButton);
        createnewaccountbutton = rootview.findViewById(R.id.CustomerCreateNewAccount);
        loginbutton.setOnClickListener(this);
        createnewaccountbutton.setOnClickListener(this);
    }

    public void setCallBackInterface(CallBackInterface callBackInterface){
        this.callBackInterface = callBackInterface;
    }

    @Override
    public void onClick(View v) {
        if(callBackInterface != null) {
            switch (v.getId()) {
                case R.id.CustomerLoginButton:
                    if(username.getText().toString().equals("")|| password.getText().toString().equals("")){
                        Toast.makeText(getContext(), Utils.FILL_ALL_DETAILS, Toast.LENGTH_SHORT).show();
                    }
                    else {
                        RequestQueue queue = Volley.newRequestQueue(getContext());
                        JSONObject jsonObject = new JSONObject();
                        try {
                            jsonObject.put("phone_number", username.getText().toString());
                            Log.e("username", username.getText().toString());
                            Log.e("password", password.getText().toString());
                            jsonObject.put("password", password.getText().toString());
                        } catch (JSONException e) {
                            Toast.makeText(getContext(), Utils.ERROR_GETTING_DATA, Toast.LENGTH_SHORT).show();
                        }
                        String url = Utils.CUSTOMER_LOGIN;
                        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        String object = "";
                                        try {
                                            object = response.getString("status");
                                        } catch (JSONException e) {

                                        }
                                        if (object.equals("TRUE")) {
                                            String name = "";
                                            try {
                                                name = response.getString("name");
                                            } catch (JSONException e) {

                                            }
                                            SharedPreferences preferences = getActivity().
                                                    getSharedPreferences(Utils.APPLICATION_NAME, getActivity().MODE_PRIVATE);
                                            preferences.edit().putString(Utils.CUSTOMER_PHONE_NUMBER, username.getText().toString()).apply();
                                            preferences.edit().putString(Utils.CUSTOMER_NAME, name).apply();
                                            preferences.edit().putString(Utils.CUSTOMER_PASSWORD, password.getText().toString()).apply();
                                            preferences.edit().putString(Utils.LAST_LOGIN, "customer").apply();
                                            callBackInterface.CustomerCallBack(R.id.CustomerLoginButton);
                                        } else {
                                            Toast.makeText(getContext(), Utils.WRONG_CREDENTIALS, Toast.LENGTH_LONG).show();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getContext(), Utils.ERROR_CONNECTING_TO_INTERNET,
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                        queue.add(request);
                    }
                    break;

                case R.id.CustomerCreateNewAccount:
                    callBackInterface.CustomerCallBack(R.id.CustomerCreateNewAccount);
                    break;
            }
        }
        else{
            Toast.makeText(rootview.getContext(), Utils.ERROR_CONNECTING_TO_INTERNET, Toast.LENGTH_LONG)
                    .show();
        }
    }
}
