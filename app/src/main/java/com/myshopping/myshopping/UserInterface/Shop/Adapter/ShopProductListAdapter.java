package com.myshopping.myshopping.UserInterface.Shop.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myshopping.myshopping.R;
import com.myshopping.myshopping.UserInterface.Shop.Model.ProductListItem;

import java.util.List;

public class ShopProductListAdapter extends RecyclerView.Adapter<ShopProductListAdapter.ViewHolder> {
    private Context context;
    private List<ProductListItem> listItems;

    public ShopProductListAdapter(Context context, List<ProductListItem> listItems) {
        this.context = context;
        this.listItems = listItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.
                card_view_shop_product_list, viewGroup, false);
        return new ShopProductListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ProductListItem item = listItems.get(i);
        viewHolder.name.setText("ID: "+item.getName());
        viewHolder.price.setText("Price: "+item.getPrice());
        viewHolder.category.setText(item.getCategory());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView price;
        private TextView category;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.shopproductnametext);
            price = itemView.findViewById(R.id.shopproductpricetext);
            category = itemView.findViewById(R.id.shopproductcategorytext);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO
                }
            });
        }
    }
}
