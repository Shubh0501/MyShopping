package com.myshopping.myshopping.UserInterface;

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

import com.myshopping.myshopping.AdatptersAndInterface.CallBackInterface;
import com.myshopping.myshopping.R;

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
                    callBackInterface.MallOwnerCallBack(R.id.MallOwnerLoginButton);
                    break;

                case R.id.MallOwnerCreateNewAccount:
                    callBackInterface.MallOwnerCallBack(R.id.MallOwnerCreateNewAccount);
                    break;
            }
        }
        else{
            Toast.makeText(rootview.getContext(), "Error connecting to the internet",
                    Toast.LENGTH_LONG).show();
        }
    }
}