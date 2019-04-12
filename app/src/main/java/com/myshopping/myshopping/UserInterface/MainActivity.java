package com.myshopping.myshopping.UserInterface;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.myshopping.myshopping.AdatptersAndInterface.CallBackInterface;
import com.myshopping.myshopping.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CallBackInterface {

    private ScrollView frameLayout;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private Button customerbutton;
    private Button shopownerbutton;
    private Button mallownerbutton;
    private Volley volley;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connect_attributes();
        volley = new Volley();
    }

    private void connect_attributes() {
        frameLayout = (ScrollView) findViewById(R.id.FragmentContainer);
        manager = getSupportFragmentManager();
        customerbutton = (Button) findViewById(R.id.CustomerButton);
        shopownerbutton = (Button) findViewById(R.id.ShopOwnerButton);
        mallownerbutton = (Button) findViewById(R.id.MallOwnerButton);
        customerbutton.setOnClickListener(this);
        shopownerbutton.setOnClickListener(this);
        mallownerbutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        transaction = manager.beginTransaction();
        customerbutton.setVisibility(View.INVISIBLE);
        shopownerbutton.setVisibility(View.INVISIBLE);
        mallownerbutton.setVisibility(View.INVISIBLE);
        switch(v.getId()) {
            case R.id.CustomerButton :

                CustomerLoginPageFragment customerLoginPageFragment = new CustomerLoginPageFragment();
                customerLoginPageFragment.setCallBackInterface(this);
                transaction.add(frameLayout.getId(), customerLoginPageFragment);
                transaction.commit();
                break;

            case R.id.ShopOwnerButton :
                ShopOwnerLoginPageFragment shopOwnerLoginPageFragment = new ShopOwnerLoginPageFragment();
                shopOwnerLoginPageFragment.setCallBackInterface(this);
                transaction.add(frameLayout.getId(), shopOwnerLoginPageFragment);
                transaction.commit();
                break;

            case R.id.MallOwnerButton :
                MallOwnerLoginPageFragment mallOwnerLoginPageFragment = new MallOwnerLoginPageFragment();
                mallOwnerLoginPageFragment.setCallBackInterface(this);
                transaction.add(frameLayout.getId(), mallOwnerLoginPageFragment);
                transaction.commit();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = manager.findFragmentById(R.id.FragmentContainer);
        if(fragment != null){
            transaction = manager.beginTransaction();
            transaction.remove(fragment);
            transaction.commit();
            Fragment fragment1 = manager.findFragmentById(R.id.FragmentContainer);
            customerbutton.setVisibility(View.VISIBLE);
            shopownerbutton.setVisibility(View.VISIBLE);
            mallownerbutton.setVisibility(View.VISIBLE);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public void CustomerCallBack(int id) {
         switch (id){
             case R.id.CustomerLoginButton:
                 RequestQueue queue = volley.newRequestQueue(this);
                 String url = "localhost:5000/CustomerLogin";
                 StringRequest request = new StringRequest(Request.Method.GET, url,
                         new Response.Listener<String>() {
                             @Override
                             public void onResponse(String response) {
                                 if(response.equals("TRUE")){
                                     startActivity(new Intent(MainActivity.this,
                                             CustomerProfile.class));
                                     finish();
                                 }
                                 else{
                                     Toast.makeText(MainActivity.this,
                                             "Password incorrect. Please try again !",
                                             Toast.LENGTH_LONG).show();
                                 }
                             }
                         },
                         new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MainActivity.this,
                                        "Phone Number not registered. Please create new account!",
                                        Toast.LENGTH_LONG).show();
                            }
                 });
                 queue.add(request);

             case R.id.CustomerCreateNewAccount:
                 startActivity(new Intent(MainActivity.this, CustomerCreateAccount.class));
                 finish();
                 break;
         }
    }

    @Override
    public void ShopOwnerCallBack(int id) {
        switch (id){
            case R.id.ShopOwnerLoginButton:
                startActivity(new Intent(MainActivity.this, ShopOwnerProfile.class));
                finish();
                break;

            case R.id.ShopOwnerCreateNewAccount:
                startActivity(new Intent(MainActivity.this, ShopOwnerCreateAccount.class));
                finish();
                break;
        }
    }

    @Override
    public void MallOwnerCallBack(int id) {
        switch (id){
            case R.id.MallOwnerLoginButton:
                startActivity(new Intent(MainActivity.this, MallOwnerProfile.class));
                finish();
                break;

            case R.id.MallOwnerCreateNewAccount:
                startActivity(new Intent(MainActivity.this, MallOwnerCreateAccount.class));
                finish();
                break;
        }
    }
}
