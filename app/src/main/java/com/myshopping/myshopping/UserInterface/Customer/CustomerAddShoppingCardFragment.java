package com.myshopping.myshopping.UserInterface.Customer;

import android.app.Dialog;
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
import com.myshopping.myshopping.UserInterface.Customer.Adapter.CustomerShoppingCardAdapter;
import com.myshopping.myshopping.UserInterface.Customer.Model.ShoppingCardList;
import com.myshopping.myshopping.Util.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CustomerAddShoppingCardFragment extends Fragment implements View.OnClickListener {
    private View view;
    private RecyclerView list;
    private Button new_card;
    private RecyclerView.Adapter adapter;
    private List<ShoppingCardList> items;
    private final JSONObject json = new JSONObject();
    private JSONArray shopping_card_list;
    private String customer_phone_number;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_customer_add_shopping_card, container, false);
        list = view.findViewById(R.id.customershoppingcardrecyclerview);
        new_card = view.findViewById(R.id.customeraddnewshoppingcardbutton);
        new_card.setOnClickListener(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list.setHasFixedSize(true);
        list.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        items = new ArrayList<>();
        adapter = new CustomerShoppingCardAdapter(this.getActivity(), items);
        list.setAdapter(adapter);

        SharedPreferences preferences = getActivity().getSharedPreferences
                (Utils.APPLICATION_NAME, getActivity().MODE_PRIVATE);
        customer_phone_number = preferences.getString(Utils.CUSTOMER_PHONE_NUMBER, "0000000000");
        String url = Utils.GET_SHOPPING_CARD;
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
                        shopping_card_list = response;
                        for(int i = 0; i < response.length(); i++){
                            ShoppingCardList item = new ShoppingCardList();
                            JSONObject here = null;
                            try {
                                here = response.getJSONObject(i);
                            } catch (JSONException e) {
                                Toast.makeText(getContext(), Utils.ERROR_FETCHING_DATA,
                                        Toast.LENGTH_SHORT).show();
                            }
                            try {
                                String card_number = here.getString("card_number");
                                String bank_name = here.getString("bank_name");
                                String credits = here.getString("credits");
                                item.setCard_number(card_number);
                                item.setBank_name(bank_name);
                                item.setCredits(credits);
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
        final Dialog dialog = new Dialog(getContext());
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setContentView(R.layout.dialog_customer_new_shopping_card);
        final EditText cardnumber = dialog.findViewById(R.id.dialogcardnumberet);
        final EditText bankname = dialog.findViewById(R.id.dialogbanknameet);
        final EditText credits = dialog.findViewById(R.id.dialogcreditset);
        Button save = dialog.findViewById(R.id.dialogaddcardbutton);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String card = cardnumber.getText().toString();
                final String bank = bankname.getText().toString();
                final String cre = credits.getText().toString();
                String url = Utils.SHOPPING_CARD_FORM;
                RequestQueue queue = Volley.newRequestQueue(getContext());
                JSONObject object = new JSONObject();
                try {
                    object.put("card_number", card);
                    object.put("bank_name", bank);
                    object.put("credits", cre);
                    object.put("customer_id", customer_phone_number);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                final JSONObject temp = object;
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, object,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                ShoppingCardList item = new ShoppingCardList();
                                item.setCard_number(card);
                                item.setBank_name(bank);
                                item.setCredits(cre);
                                items.add(item);
                                shopping_card_list.put(temp);
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
