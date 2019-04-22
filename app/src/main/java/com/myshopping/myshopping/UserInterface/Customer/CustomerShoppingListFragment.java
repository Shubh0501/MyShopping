package com.myshopping.myshopping.UserInterface.Customer;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.myshopping.myshopping.R;
import com.myshopping.myshopping.UserInterface.Customer.Adapter.CustomerAdapter;
import com.myshopping.myshopping.UserInterface.Customer.Model.ListItem;
import com.myshopping.myshopping.Util.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CustomerShoppingListFragment extends Fragment {

    private View view;
    private RecyclerView list;
    private RecyclerView.Adapter adapter;
    private List<ListItem> items;
    private final JSONObject json = new JSONObject();
    private JSONArray shopping_list;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_customer_shopping_list, container, false);
        list = view.findViewById(R.id.customerrecyclerview);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list.setHasFixedSize(true);
        list.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        items = new ArrayList<>();
        adapter = new CustomerAdapter(this.getActivity(), items);
        list.setAdapter(adapter);

        SharedPreferences preferences = getActivity().getSharedPreferences
                (Utils.APPLICATION_NAME, getActivity().MODE_PRIVATE);
        String customer_phone_number = preferences.getString(Utils.CUSTOMER_PHONE_NUMBER, "0000000000");
        String url = Utils.GET_SHOPPING_LIST;
        RequestQueue queue = Volley.newRequestQueue(getContext());
        try {
            json.put("customer_phone_number", customer_phone_number);
        } catch (JSONException e) {
            Toast.makeText(getContext(), Utils.ERROR_GETTING_DATA, Toast.LENGTH_SHORT).show();
        }
        JSONArray array = new JSONArray();
        array.put(json);
        final JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, url, array,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        shopping_list = response;
                        for(int i = 0; i < response.length(); i++){
                            ListItem item = new ListItem();
                            JSONObject here = null;
                            try {
                                here = response.getJSONObject(i);
                            } catch (JSONException e) {
                                Toast.makeText(getContext(), Utils.ERROR_FETCHING_DATA,
                                        Toast.LENGTH_SHORT).show();
                            }
                            try {
                                String shop_username = here.getString("shop_username");
                                String datetime = here.getString("datetime");
                                String transaction_id = here.getString("transaction_id");
                                item.setTransaction_id(transaction_id);
                                item.setDate(datetime);
                                item.setShop_name(shop_username);
                            } catch (JSONException e) {
                                Toast.makeText(getContext(), Utils.ERROR_GETTING_DATA,
                                        Toast.LENGTH_SHORT).show();
                            }
                            items.add(item);
                        }
                        adapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), Utils.ERROR_FETCHING_DATA,
                                Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(request);
    }
}
