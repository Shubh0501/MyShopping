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
import com.myshopping.myshopping.UserInterface.Shop.Adapter.ShopEmployeeListAdapter;
import com.myshopping.myshopping.UserInterface.Shop.Model.EmployeeListItem;
import com.myshopping.myshopping.Util.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ShopEmployeeListFragment extends Fragment {
    private View view;
    private RecyclerView list;
    private RecyclerView.Adapter adapter;
    private List<EmployeeListItem> items;
    private final JSONObject json = new JSONObject();
    private JSONArray employee_list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_shop_employee_list, container, false);
        list = view.findViewById(R.id.shopemployeelistrecyclerview);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list.setHasFixedSize(true);
        list.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        items = new ArrayList<>();
        adapter = new ShopEmployeeListAdapter(this.getActivity(), items);
        list.setAdapter(adapter);

        SharedPreferences preferences = getActivity().getSharedPreferences
                (Utils.APPLICATION_NAME, getActivity().MODE_PRIVATE);
        String shop_username = preferences.getString(Utils.SHOP_USERNAME, "XXXX");
        String url = Utils.GET_EMPLOYEE_LIST;
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
                        employee_list = response;
                        for(int i = 0; i < response.length(); i++){
                            EmployeeListItem item = new EmployeeListItem();
                            JSONObject here = null;
                            try {
                                here = response.getJSONObject(i);
                            } catch (JSONException e) {
                                Toast.makeText(getContext(), Utils.ERROR_FETCHING_DATA,
                                        Toast.LENGTH_SHORT).show();
                            }
                            try {
                                String emp_id = here.getString("emp_id");
                                String phone_number = here.getString("phone_number");
                                String name = here.getString("name");
                                String position = here.getString("position");
                                item.setEmp_id(emp_id);
                                item.setEmp_name(name);
                                item.setPhone_number(phone_number);
                                item.setPosition(position);
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
