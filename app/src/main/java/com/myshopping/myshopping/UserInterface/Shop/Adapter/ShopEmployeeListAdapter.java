package com.myshopping.myshopping.UserInterface.Shop.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myshopping.myshopping.R;
import com.myshopping.myshopping.UserInterface.Shop.Model.EmployeeListItem;

import org.w3c.dom.Text;

import java.util.List;

public class ShopEmployeeListAdapter extends RecyclerView.Adapter<ShopEmployeeListAdapter.ViewHolder> {
    private Context context;
    private List<EmployeeListItem> listItems;

    public ShopEmployeeListAdapter(Context context, List<EmployeeListItem> listItems) {
        this.context = context;
        this.listItems = listItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.
                card_view_shop_employee_list, viewGroup, false);
        return new ShopEmployeeListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        EmployeeListItem item = listItems.get(i);
        viewHolder.id.setText("ID: "+item.getEmp_id());
        viewHolder.name.setText(item.getEmp_name());
        viewHolder.position.setText(item.getPosition());
        viewHolder.phone_number.setText("Phone: "+item.getPhone_number());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView id;
        private TextView name;
        private TextView position;
        private TextView phone_number;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.shopemployeeidtext);
            name = itemView.findViewById(R.id.shopemployeenametext);
            position = itemView.findViewById(R.id.shopemployeepositiontext);
            phone_number = itemView.findViewById(R.id.shopemployeephonenumbertext);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO
                }
            });
        }
    }
}
