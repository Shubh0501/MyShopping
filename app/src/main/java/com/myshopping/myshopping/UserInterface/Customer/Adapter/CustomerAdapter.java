package com.myshopping.myshopping.UserInterface.Customer.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myshopping.myshopping.R;
import com.myshopping.myshopping.UserInterface.Customer.Model.ListItem;

import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder> {

    private Context context;
    private List<ListItem> listItems;

    public CustomerAdapter(Context context, List list){
        this.context = context;
        this.listItems = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.
                card_view_customer_recycler_view, viewGroup, true);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Log.e("phone", "adapter");
        ListItem item = listItems.get(i);
        viewHolder.product_name.setText(item.getProduct_name());
        viewHolder.shop_name.setText(item.getShop_name());
        viewHolder.quantity.setText("Quantity: "+item.getProduct_quantity());
        viewHolder.date.setText("Date: "+item.getDate());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView product_name;
        private TextView shop_name;
        private TextView date;
        private TextView quantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            product_name =itemView.findViewById(R.id.customerproductnametext);
            shop_name = itemView.findViewById(R.id.customershopnametext);
            date = itemView.findViewById(R.id.customerdatetext);
            quantity = itemView.findViewById(R.id.customerquantitytext);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            ListItem item = listItems.get(position);

            //TODO START NEW ACTIVITY TO SHOW MORE DETAILS
        }
    }
}
