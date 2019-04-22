package com.myshopping.myshopping.UserInterface.Shop;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.myshopping.myshopping.R;
import com.myshopping.myshopping.Util.Utils;

public class ShopAccountFragment extends Fragment {
    private View view;
    private TextView username;
    private TextView name;
    private TextView owner_name;
    private TextView owner_phone_number;
    private TextView password;
    private Button show_pass;
    private String star = "*";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_shop_account, container, false);
        username = view.findViewById(R.id.shopusernametext);
        name = view.findViewById(R.id.shopnametext);
        owner_name = view.findViewById(R.id.shopownernametext);
        owner_phone_number = view.findViewById(R.id.shopownerphonenumbertext);
        password = view.findViewById(R.id.shoppasswordtext);
        show_pass = view.findViewById(R.id.shopshowpassword);

        SharedPreferences preferences = getActivity().getSharedPreferences(Utils.APPLICATION_NAME,
                Context.MODE_PRIVATE);

        String shop_username = preferences.getString(Utils.SHOP_USERNAME, "Error");
        String shop_name = preferences.getString(Utils.SHOP_NAME, "Error");
        String shop_owner_name = preferences.getString(Utils.SHOP_OWNER_NAME, "Error");
        String shop_owner_phone = preferences.getString(Utils.SHOP_OWNER_PHONE_NUMBER, "Error");
        final String shop_password = preferences.getString(Utils.SHOP_PASSWORD, "Error");
        int pass_len = shop_password.length();
        for(int i = 0; i < pass_len-1; i++){
            star = star + "*";
        }

        username.setText(shop_username);
        name.setText(shop_name);
        owner_name.setText(shop_owner_name);
        owner_phone_number.setText(shop_owner_phone);
        password.setText(star);

        show_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(show_pass.getText().toString().equals("Show Password")){
                    password.setText(shop_password);
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
