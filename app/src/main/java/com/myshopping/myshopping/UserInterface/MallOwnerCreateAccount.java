package com.myshopping.myshopping.UserInterface;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.myshopping.myshopping.R;

public class MallOwnerCreateAccount extends AppCompatActivity {

    private EditText name;
    private EditText phonenumber;
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
        name = (EditText) findViewById(R.id.MallOwnerNameNewAccount);
        phonenumber = (EditText) findViewById(R.id.MallOwnerPhoneNumberNewAccount);
        password = (EditText) findViewById(R.id.MallOwnerPasswordNewAccount);
        confirmpassword = (EditText) findViewById(R.id.MallOwnerConfirmPasswordNewAccount);
        createaccount = (Button) findViewById(R.id.MallOwnerCreateAccountButton);

        createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });
    }
}
