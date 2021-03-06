package com.myshopping.myshopping.UserInterface.Shop;

import android.app.Dialog;
import android.app.DownloadManager;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.myshopping.myshopping.R;
import com.myshopping.myshopping.UserInterface.Shop.Adapter.ShopSellListAdapter;
import com.myshopping.myshopping.UserInterface.Shop.Model.SellingListItem;
import com.myshopping.myshopping.Util.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ShopSellListFragment extends Fragment implements View.OnClickListener {

    private View view;
    private RecyclerView list;
    private Button newselling;
    private RecyclerView.Adapter adapter;
    private List<SellingListItem> items;
    private final JSONObject json = new JSONObject();
    private JSONArray selling_list;
    private String shop_username;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_shop_sell_list, container, false);
        list = view.findViewById(R.id.recyclerviewshopselllist);
        newselling = view.findViewById(R.id.shopaddnewsellbutton);
        newselling.setOnClickListener(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list.setHasFixedSize(true);
        list.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        items = new ArrayList<>();
        adapter = new ShopSellListAdapter(this.getActivity(), items);
        list.setAdapter(adapter);

        SharedPreferences preferences = getActivity().getSharedPreferences
                (Utils.APPLICATION_NAME, getActivity().MODE_PRIVATE);
        shop_username = preferences.getString(Utils.SHOP_USERNAME, "xxx");
        String url = Utils.GET_SELLING_LIST;
        RequestQueue queue = Volley.newRequestQueue(getContext());
        try {
            json.put("shop_username", shop_username);
        } catch (JSONException e) {
            Toast.makeText(getContext(), Utils.ERROR_GETTING_DATA, Toast.LENGTH_SHORT).show();
        }
        JSONArray array = new JSONArray();
        array.put(json);
        final JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, url, array,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        selling_list = response;
                        for(int i = 0; i < response.length(); i++){
                            SellingListItem item = new SellingListItem();
                            JSONObject here = null;
                            try {
                                here = response.getJSONObject(i);
                            } catch (JSONException e) {
                                Toast.makeText(getContext(), Utils.ERROR_FETCHING_DATA,
                                        Toast.LENGTH_SHORT).show();
                            }
                            try {
                                String customer_phone_number = here.getString("customer_phone_number");
                                String datetime = here.getString("datetime");
                                String transaction_id = here.getString("transaction_id");
                                item.setTransaction_id(transaction_id);
                                item.setDate(datetime);
                                item.setCustomer_phone_number(customer_phone_number);
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

    @Override
    public void onClick(View v) {
        final Dialog dialog = new Dialog(this.getActivity());
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setContentView(R.layout.dialog_new_sell_list_form);
        final EditText transaction_id = dialog.findViewById(R.id.dialogtransactionidet);
        final EditText customer_number = dialog.findViewById(R.id.dialogcustomerphonenumberet);
        final EditText product_id = dialog.findViewById(R.id.dialogproductidet);
        final EditText product_quantity = dialog.findViewById(R.id.dialogproductquantityet);
        final EditText date = dialog.findViewById(R.id.dialogdateet);
        Button save = dialog.findViewById(R.id.dialogsavebutton);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String transaction = transaction_id.getText().toString();
                final String number = customer_number.getText().toString();
                String product = product_id.getText().toString();
                int quantity = Integer.parseInt(product_quantity.getText().toString());
                final String datetime = date.getText().toString();

                String url = Utils.NEW_SHOPPING;
                RequestQueue queue = Volley.newRequestQueue(getContext());
                JSONObject object = new JSONObject();
                try {
                    object.put("transaction_id", transaction);
                    object.put("customer_phone_number", number);
                    object.put("shop_username", shop_username);
                    object.put("product_id", product);
                    object.put("quantity", quantity);
                    object.put("datetime", datetime);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                final JSONObject temp = object;
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, object,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                SellingListItem item = new SellingListItem();
                                item.setCustomer_phone_number(number);
                                item.setDate(datetime);
                                item.setTransaction_id(transaction);
                                items.add(item);
                                selling_list.put(temp);
                                adapter.notifyDataSetChanged();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getContext(), Utils.ERROR_CONNECTING_TO_INTERNET,
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                queue.add(request);
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
