package com.myshopping.myshopping.UserInterface.Customer.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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
                card_view_customer_recycler_view, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ListItem item = listItems.get(i);
        viewHolder.transaction_id.setText("ID: "+item.getTransaction_id());
        viewHolder.shop_name.setText("Shop: "+item.getShop_name());
        viewHolder.date.setText("Date: "+item.getDate());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView transaction_id;
        private TextView shop_name;
        private TextView date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            transaction_id =itemView.findViewById(R.id.customertransactionidtext);
            shop_name = itemView.findViewById(R.id.customershopnametext);
            date = itemView.findViewById(R.id.customerdatetext);

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
