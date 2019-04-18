package com.myshopping.myshopping.UserInterface.Mall;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ScrollView;

import com.myshopping.myshopping.R;

public class MallOwnerProfile extends AppCompatActivity {

    private FragmentManager manager;
    private FragmentTransaction transaction;
    private ScrollView fragmentcontainer;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_mall_shop_list:
                    fragmentcontainer.removeAllViews();
                    transaction = manager.beginTransaction();
                    MallShopListFragment shopListFragment = new MallShopListFragment();
                    transaction.add(fragmentcontainer.getId(), shopListFragment);
                    transaction.commit();
                    return true;
                case R.id.navigation_mall_account:
                    fragmentcontainer.removeAllViews();
                    transaction = manager.beginTransaction();
                    MallAccountFragment accountFragment = new MallAccountFragment();
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
        setContentView(R.layout.activity_mall_owner_profile);
        BottomNavigationView navView = findViewById(R.id.mall_nav_view);
        fragmentcontainer = findViewById(R.id.mallfragmentcontainer);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        manager = getSupportFragmentManager();
    }
}
