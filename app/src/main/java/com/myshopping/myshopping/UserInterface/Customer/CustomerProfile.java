package com.myshopping.myshopping.UserInterface.Customer;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.ScrollView;

import com.myshopping.myshopping.R;

public class CustomerProfile extends AppCompatActivity {

    private FragmentManager manager;
    private FragmentTransaction transaction;
    private ScrollView fragmentcontainer;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_customer_shopping_list:
                    fragmentcontainer.removeAllViews();
                    transaction = manager.beginTransaction();
                    CustomerShoppingListFragment shoppingListFragment= new CustomerShoppingListFragment();
                    transaction.add(fragmentcontainer.getId(), shoppingListFragment);
                    transaction.commit();
                    return true;

                case R.id.navigation_customer_add_shopping_card:
                    fragmentcontainer.removeAllViews();
                    transaction = manager.beginTransaction();
                    CustomerAddShoppingCardFragment shoppingCardFragment= new CustomerAddShoppingCardFragment();
                    transaction.add(fragmentcontainer.getId(), shoppingCardFragment);
                    transaction.commit();
                    return true;

                case R.id.navigation_customer_account:
                    fragmentcontainer.removeAllViews();
                    transaction = manager.beginTransaction();
                    CustomerAccountFragment accountFragment = new CustomerAccountFragment();
                    transaction.add(fragmentcontainer.getId(), accountFragment);
                    transaction.commit();

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        fragmentcontainer = findViewById(R.id.customerfragmentcontainer);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        manager = getSupportFragmentManager();
    }

}
