package com.myshopping.myshopping.UserInterface.Shop;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.myshopping.myshopping.UserInterface.Shop.Adapter.ShopProductListAdapter;
import com.myshopping.myshopping.UserInterface.Shop.Model.ProductListItem;
import com.myshopping.myshopping.Util.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ShopProductsFragment extends Fragment {
    private View view;
    private RecyclerView list;
    private RecyclerView.Adapter adapter;
    private List<ProductListItem> items;
    private final JSONObject json = new JSONObject();
    private JSONArray product_list;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_shop_products, container, false);
        list = view.findViewById(R.id.shopproductlistrecyclerview);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        list.setHasFixedSize(true);
        list.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        items = new ArrayList<>();
        adapter = new ShopProductListAdapter(this.getActivity(), items);
        list.setAdapter(adapter);

        SharedPreferences preferences = getActivity().getSharedPreferences
                (Utils.APPLICATION_NAME, getActivity().MODE_PRIVATE);
        String shop_username = preferences.getString(Utils.SHOP_USERNAME, "XXXX");
        String url = Utils.GET_PRODUCT_LIST;
        RequestQueue queue = Volley.newRequestQueue(getContext());
        try {
            json.put("shop_username", shop_username);
        } catch (JSONException e) {
            Toast.makeText(getContext(), Utils.ERROR_GETTING_DATA, Toast.LENGTH_SHORT).show();
        }
        JSONArray array = new JSONArray();
        array.put(json);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, url, array,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        product_list = response;
                        for(int i = 0; i < response.length(); i++){
                            ProductListItem item = new ProductListItem();
                            JSONObject here = null;
                            try {
                                here = response.getJSONObject(i);
                            } catch (JSONException e) {
                                Toast.makeText(getContext(), Utils.ERROR_FETCHING_DATA,
                                        Toast.LENGTH_SHORT).show();
                            }
                            try {
                                String name = here.getString("name");
                                String price = here.getString("price");
                                String category = here.getString("category");
                                item.setName(name);
                                item.setPrice(price);
                                item.setCategory(category);
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
