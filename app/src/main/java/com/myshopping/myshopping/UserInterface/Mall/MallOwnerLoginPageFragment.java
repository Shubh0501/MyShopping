package com.myshopping.myshopping.UserInterface.Mall;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.android.volley.toolbox.Volley;
import com.myshopping.myshopping.AdatptersAndInterface.CallBackInterface;
import com.myshopping.myshopping.R;
import com.myshopping.myshopping.Util.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import static com.myshopping.myshopping.R.id.CustomerLoginButton;
import static com.myshopping.myshopping.R.id.MallOwnerLoginButton;

public class MallOwnerLoginPageFragment extends Fragment implements View.OnClickListener {

    private View rootview;
    private EditText username;
    private EditText password;
    private Button loginbutton;
    private Button createnewaccountbutton;
    private CallBackInterface callBackInterface;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_mall_owner_login_page, container, false);
        connect_attributes();
        return rootview;
    }

    private void connect_attributes() {
        username = (EditText) rootview.findViewById(R.id.MallOwnerUsernameEditText);
        password = (EditText) rootview.findViewById(R.id.MallOwnerPasswordEditText);
        loginbutton = (Button) rootview.findViewById(R.id.MallOwnerLoginButton);
        createnewaccountbutton = (Button) rootview.findViewById(R.id.MallOwnerCreateNewAccount);
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
                case R.id.MallOwnerLoginButton:
                    if(username.getText().toString().equals("")||password.getText().toString().equals("")){
                        Toast.makeText(getContext(), Utils.FILL_ALL_DETAILS, Toast.LENGTH_SHORT).show();
                    }
                    else {
                        RequestQueue queue = Volley.newRequestQueue(getContext());
                        JSONObject jsonObject = new JSONObject();
                        try {
                            jsonObject.put("username", username.getText().toString());
                            jsonObject.put("password", password.getText().toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String url = Utils.MALL_LOGIN;
                        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        String object = "";
                                        try {
                                            object = response.getString("status");
                                        } catch (JSONException e) {
                                            Toast.makeText(getContext(), Utils.ERROR_GETTING_DATA, Toast.LENGTH_SHORT).show();
                                        }
                                        if (object.equals("TRUE")) {
                                            String name = "";
                                            String owner_name = "";
                                            String owner_phone_number = "";
                                            try {
                                                name = response.getString("name");
                                                owner_name = response.getString("owner_name");
                                                owner_phone_number = response.getString("owner_phone_number");
                                            } catch (JSONException e) {

                                            }
                                            SharedPreferences preferences = getActivity().
                                                    getSharedPreferences(Utils.APPLICATION_NAME, getActivity().MODE_PRIVATE);
                                            preferences.edit().putString(Utils.MALL_OWNER_PHONE_NUMBER, owner_phone_number).apply();
                                            preferences.edit().putString(Utils.MALL_NAME, name).apply();
                                            preferences.edit().putString(Utils.MALL_PASSWORD, password.getText().toString()).apply();
                                            preferences.edit().putString(Utils.LAST_LOGIN, "mall").apply();
                                            preferences.edit().putString(Utils.MALL_OWNER_NAME, owner_name).apply();
                                            preferences.edit().putString(Utils.MALL_USERNAME, username.getText().toString()).apply();
                                            callBackInterface.MallOwnerCallBack(R.id.MallOwnerLoginButton);
                                        } else {
                                            Toast.makeText(getContext(), Utils.WRONG_CREDENTIALS, Toast.LENGTH_LONG).show();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getContext(), Utils.ERROR_CONNECTING_TO_INTERNET, Toast.LENGTH_LONG).show();
                            }
                        });
                        queue.add(request);
                    }
                    break;

                case R.id.MallOwnerCreateNewAccount:
                    callBackInterface.MallOwnerCallBack(R.id.MallOwnerCreateNewAccount);
                    break;
            }
        }
        else{
            Toast.makeText(rootview.getContext(), Utils.ERROR_CONNECTING_TO_INTERNET,
                    Toast.LENGTH_LONG).show();
        }
    }
}
