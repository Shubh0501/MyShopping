package com.myshopping.myshopping.UserInterface.Mall;

import android.content.Context;
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
import android.widget.TextView;

import com.myshopping.myshopping.R;
import com.myshopping.myshopping.Util.Utils;

public class MallAccountFragment extends Fragment {
    private View view;
    private TextView username;
    private TextView name;
    private TextView owner_name;
    private TextView owner_phone_number;
    private TextView password;
    private Button show_pass;
    private String star = "*";
    private String m_pass;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mall_account, container, false);
        username = view.findViewById(R.id.mallusernametext);
        name = view.findViewById(R.id.mallnametext);
        owner_name = view.findViewById(R.id.mallownernametext);
        owner_phone_number = view.findViewById(R.id.mallownerphonenumbertext);
        password = view.findViewById(R.id.mallpasswordtext);
        show_pass = view.findViewById(R.id.mallshowpassword);

        SharedPreferences preferences = getActivity().getSharedPreferences(Utils.APPLICATION_NAME,
                Context.MODE_PRIVATE);

        String shop_username = preferences.getString(Utils.MALL_USERNAME, "Error");
        String shop_name = preferences.getString(Utils.MALL_NAME, "Error");
        String shop_owner_name = preferences.getString(Utils.MALL_OWNER_NAME, "Error");
        String shop_owner_phone = preferences.getString(Utils.MALL_OWNER_PHONE_NUMBER, "Error");
        m_pass = preferences.getString(Utils.MALL_PASSWORD, "Error");
        Log.e("pass", m_pass);
        int pass_len = m_pass.length();
        for(int i = 0; i < pass_len-1; i++){
            star = star + "*";
        }
        Log.e("star", star);

        username.setText(shop_username);
        name.setText(shop_name);
        owner_name.setText(shop_owner_name);
        owner_phone_number.setText(shop_owner_phone);
        password.setText(star);

        show_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(show_pass.getText().toString().equals("Show Password")){
                    password.setText(m_pass);
                    show_pass.setText("Hide Password");
                }
                else{
                    password.setText(star);
                    show_pass.setText("Show Password");
                }
            }
        });

        return view;
    }
}
