package com.myshopping.myshopping.UserInterface.Customer.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myshopping.myshopping.R;
import com.myshopping.myshopping.UserInterface.Customer.Model.ShoppingCardList;

import java.util.List;

public class CustomerShoppingCardAdapter extends RecyclerView.Adapter<CustomerShoppingCardAdapter.ViewHolder> {

    private Context context;
    private List<ShoppingCardList> listItems;

    public CustomerShoppingCardAdapter(Context context, List<ShoppingCardList> listItems) {
        this.context = context;
        this.listItems = listItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.
                card_view_customer_shopping_card, viewGroup, false);
        return new CustomerShoppingCardAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ShoppingCardList item = listItems.get(i);
        viewHolder.credits.setText(item.getCredits());
        viewHolder.bank_name.setText("Bank: "+item.getBank_name());
        viewHolder.card_number.setText("Number: "+item.getCard_number());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView credits;
        private TextView card_number;
        private TextView bank_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            credits = itemView.findViewById(R.id.shoppingcardcreditstext);
            card_number = itemView.findViewById(R.id.shoppingcardcardnumbertext);
            bank_name = itemView.findViewById(R.id.shoppingcardbanknametext);
        }
    }
}
