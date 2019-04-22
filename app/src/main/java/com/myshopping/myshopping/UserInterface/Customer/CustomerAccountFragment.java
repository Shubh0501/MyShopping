package com.myshopping.myshopping.UserInterface.Customer;

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

public class CustomerAccountFragment extends Fragment{

    private View view;
    private TextView name;
    private TextView phone_number;
    private TextView pass;
    private Button show_pass;
    private String star = "*";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_customer_account, container, false);

        name = view.findViewById(R.id.customernametext);
        phone_number = view.findViewById(R.id.customerphonenumbertext);
        pass = view.findViewById(R.id.customerpasswordtext);
        show_pass = view.findViewById(R.id.customershowpassword);

        SharedPreferences preferences = getActivity().getSharedPreferences(Utils.APPLICATION_NAME,
                Context.MODE_PRIVATE);
        String cus_name = preferences.getString(Utils.CUSTOMER_NAME, "Error");
        String cus_phone_num = preferences.getString(Utils.CUSTOMER_PHONE_NUMBER, "Error");
        final String cus_pass = preferences.getString(Utils.CUSTOMER_PASSWORD, "Error");
        int len_pas = cus_name.length();

        name.setText(cus_name);
        phone_number.setText(cus_phone_num);
        star = "";
        for(int i = 0; i < len_pas-1; i++){
            star = star + "*";
        }
        pass.setText(star);

        show_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(show_pass.getText().toString().equals("Show Password")){
                    pass.setText(cus_pass);
                    show_pass.setText("Hide Password");
                }
                else{
                    pass.setText(star);
                    show_pass.setText("Show Password");
                }
            }
        });

        return view;
    }
}
