package com.myshopping.myshopping.UserInterface.Shop;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ScrollView;

import com.myshopping.myshopping.R;

public class ShopOwnerProfile extends AppCompatActivity {

    private FragmentManager manager;
    private FragmentTransaction transaction;
    private ScrollView fragmentcontainer;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_shop_sell_list:
                    fragmentcontainer.removeAllViews();
                    transaction = manager.beginTransaction();
                    ShopSellListFragment sellListFragment = new ShopSellListFragment();
                    transaction.add(fragmentcontainer.getId(), sellListFragment);
                    transaction.commit();
                    return true;
                case R.id.navigation_shop_products:
                    fragmentcontainer.removeAllViews();
                    transaction = manager.beginTransaction();
                    ShopProductsFragment productsFragment = new ShopProductsFragment();
                    transaction.add(fragmentcontainer.getId(), productsFragment);
                    transaction.commit();
                    return true;
                case R.id.navigation_shop_employee_list:
                    fragmentcontainer.removeAllViews();
                    transaction = manager.beginTransaction();
                    ShopEmployeeListFragment employeeListFragment = new ShopEmployeeListFragment();
                    transaction.add(fragmentcontainer.getId(), employeeListFragment);
                    transaction.commit();
                    return true;
                case R.id.navigation_shop_account:
                    fragmentcontainer.removeAllViews();
                    transaction = manager.beginTransaction();
                    ShopAccountFragment accountFragment = new ShopAccountFragment();
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
        setContentView(R.layout.activity_shop_owner_profile);
        BottomNavigationView navView = findViewById(R.id.shop_nav_view);
        fragmentcontainer = findViewById(R.id.shopfragmentcontainer);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        manager = getSupportFragmentManager();
    }
}
