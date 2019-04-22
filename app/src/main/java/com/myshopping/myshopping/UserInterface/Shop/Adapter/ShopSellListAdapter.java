package com.myshopping.myshopping.UserInterface.Shop.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myshopping.myshopping.R;
import com.myshopping.myshopping.UserInterface.Shop.Model.SellingListItem;

import java.util.List;

public class ShopSellListAdapter extends RecyclerView.Adapter<ShopSellListAdapter.ViewHolder> {

    private Context context;
    private List<SellingListItem> listItems;

    public ShopSellListAdapter(Context context, List list){
        this.context = context;
        this.listItems = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.
                card_view_shop_sell_list, viewGroup, false);
        return new ShopSellListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        SellingListItem item = listItems.get(i);
        viewHolder.transaction_id.setText("ID: "+item.getTransaction_id());
        viewHolder.customer_phone_number.setText("Customer: "+item.getCustomer_phone_number());
        viewHolder.date.setText("Date: "+item.getDate());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView transaction_id;
        private TextView customer_phone_number;
        private TextView date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            transaction_id =itemView.findViewById(R.id.shopselltransactionidtext);
            customer_phone_number = itemView.findViewById(R.id.shopsellcustomerphonetext);
            date = itemView.findViewById(R.id.shopselldatetext);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO
                }
            });
        }
    }
}
