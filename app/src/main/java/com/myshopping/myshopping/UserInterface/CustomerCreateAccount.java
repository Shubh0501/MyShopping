package com.myshopping.myshopping.UserInterface;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.myshopping.myshopping.R;

public class CustomerCreateAccount extends AppCompatActivity {

    private EditText customername;
    private EditText customerphonenumber;
    private EditText customerpassword;
    private EditText customerconfirmpassword;
    private Button customercreateaccountbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_create_account);
        connect_attributes();
    }

    private void connect_attributes() {
        customername = (EditText) findViewById(R.id.CustomerNameNewAccount);
        customerphonenumber = (EditText) findViewById(R.id.CustomerPhoneNumberNewAccount);
        customerpassword = (EditText) findViewById(R.id.CustomerPasswordNewAccount);
        customerconfirmpassword = (EditText) findViewById(R.id.CustomerConfirmPasswordNewAccount);
        customercreateaccountbutton = (Button) findViewById(R.id.CustomerCreateAccountButton);

        customercreateaccountbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });
    }
}
