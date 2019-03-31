package com.myshopping.myshopping.UserInterface;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.myshopping.myshopping.R;

public class ShopOwnerCreateAccount extends AppCompatActivity {

    private EditText name;
    private EditText phonenumber;
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
        name = (EditText) findViewById(R.id.ShopOwnerNameNewAccount);
        phonenumber = (EditText) findViewById(R.id.ShopOwnerPhoneNumberNewAccount);
        password = (EditText) findViewById(R.id.ShopOwnerPasswordNewAccount);
        confirmpassword = (EditText) findViewById(R.id.ShopOwnerConfirmPasswordNewAccount);
        createaccount = (Button) findViewById(R.id.ShopOwnerCreateAccountButton);

        createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });
    }
}
