package com.myshopping.myshopping.UserInterface;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.myshopping.myshopping.HerokuService.CustomerNewAccount;
import com.myshopping.myshopping.HerokuService.CustomerNewAccountService;
import com.myshopping.myshopping.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://myshoppingapp.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create()).build();

        final CustomerNewAccountService service = retrofit.create
                (CustomerNewAccountService.class);

        customercreateaccountbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = customername.getText().toString();
                String phonenumber = customerphonenumber.getText().toString();
                String password = customerpassword.getText().toString();
                String confirmpass = customerconfirmpassword.getText().toString();
                if(!password.equals(confirmpass)){
                    Toast.makeText(CustomerCreateAccount.this,
                            "Password does not match !", Toast.LENGTH_LONG)
                            .show();
                }
                else {
                    CustomerNewAccount newAccount = new CustomerNewAccount(name, phonenumber,
                            password);
                    Call<CustomerNewAccount> call = service.create(newAccount);
                    call.enqueue(new Callback<CustomerNewAccount>() {
                        @Override
                        public void onResponse(Call<CustomerNewAccount> call, Response<CustomerNewAccount> response) {
                            CustomerNewAccount customerNewAccount = response.body();
                            startActivity(new Intent(CustomerCreateAccount.this,
                                    CustomerProfile.class));
                            finish();
                        }

                        @Override
                        public void onFailure(Call<CustomerNewAccount> call, Throwable t) {
                            Toast.makeText(CustomerCreateAccount.this, t.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                }

            }
        });
    }
}
