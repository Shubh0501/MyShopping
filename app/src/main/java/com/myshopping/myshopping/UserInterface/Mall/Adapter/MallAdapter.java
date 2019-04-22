package com.myshopping.myshopping.UserInterface.Mall.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myshopping.myshopping.R;
import com.myshopping.myshopping.UserInterface.Mall.Model.MallShopsList;

import java.util.List;

public class MallAdapter extends RecyclerView.Adapter<MallAdapter.ViewHolder> {

    private Context context;
    private List<MallShopsList> listItems;

    public MallAdapter(Context context, List<MallShopsList> listItems) {
        this.context = context;
        this.listItems = listItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.
                card_view_mall_shop_list, viewGroup, false);
        return new MallAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        MallShopsList item = listItems.get(i);
        viewHolder.shop_name.setText("Shop Name: "+item.getShop_name());
        viewHolder.shop_owner_number.setText("Phone: "+item.getShop_owner_phone_number());
        viewHolder.shop_owner_name.setText("Owner: "+item.getShop_owner_name());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView shop_name;
        private TextView shop_owner_name;
        private TextView shop_owner_number;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            shop_owner_name =itemView.findViewById(R.id.mallshopownertext);
            shop_name = itemView.findViewById(R.id.mallshopnametext);
            shop_owner_number = itemView.findViewById(R.id.mallshopownerphonenumbertext);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO
                }
            });
        }
    }
}
