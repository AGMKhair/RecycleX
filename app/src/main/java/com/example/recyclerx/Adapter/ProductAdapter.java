package com.example.recyclerx.Adapter;


import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recyclerx.Activity.ProductDetailsActivity;
import com.example.recyclerx.Model.ProductData;
import com.example.recyclerx.R;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{
    private ProductData[] listdata;

    // RecyclerView recyclerView;
    public ProductAdapter(ProductData[] listdata) {
        this.listdata = listdata;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.model_design_main, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final ProductData myListData = listdata[position];

        holder.itemName.setText(listdata[position].getName());
        holder.itemQuantity.setText(listdata[position].getQuantity());
        holder.itemPrice.setText(listdata[position].getPrice());
        holder.productImage.setImageResource(listdata[position].getImgId());

        //holder.itemName.setText(listdata[position].getName());

        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int  s = Integer.parseInt(holder.itemSelectCount.getText().toString())-1;
                if(s==0)
                {
                    holder.minus.setVisibility(View.INVISIBLE);
                    holder.itemSelectCount.setVisibility(View.INVISIBLE);
                    holder.itemSelectCount.setText("0");
                }
                else
                {
                    holder.itemSelectCount.setVisibility(View.VISIBLE);
                    holder.itemSelectCount.setText(String.valueOf(s));
                }


            }
        });

        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int  s = Integer.parseInt(holder.itemSelectCount.getText().toString())+1;

                    holder.minus.setVisibility(View.VISIBLE);
                    holder.itemSelectCount.setVisibility(View.VISIBLE);
                    holder.itemSelectCount.setText(String.valueOf(s));
            }
        });

        //holder.itemQuantity.setText();

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                view.getContext().startActivity(new Intent(view.getContext(), ProductDetailsActivity.class).putExtra("ImageId",listdata[position].getImgId()).putExtra("ProductName",listdata[position].getName()).putExtra("Price",listdata[position].getPrice()));

                Toast.makeText(view.getContext(),"click on item: "+myListData.getName(),Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public int getItemCount() {

        try {
            return listdata.length;

        }catch (Exception e)
        {
            return 0;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView productImage,minus,plus;
        public TextView itemName,itemQuantity,itemPrice,itemSelectCount;
        public RelativeLayout relativeLayout;

        public ViewHolder(View itemView)
        {
            super(itemView);

            this.productImage= (ImageView) itemView.findViewById(R.id.item_image_show_id);
            this.minus = (ImageView) itemView.findViewById(R.id.item_minus_id);
            minus.setVisibility(View.INVISIBLE);
            this.plus = (ImageView) itemView.findViewById(R.id.item_plus_id);

            this.itemName = (TextView) itemView.findViewById(R.id.item_name_show_main_page_id);
            this.itemQuantity = (TextView) itemView.findViewById(R.id.item_quantity_show_main_page_id);
            this.itemPrice = (TextView) itemView.findViewById(R.id.item_price_show_main_page_id);
            this.itemSelectCount = (TextView) itemView.findViewById(R.id.item_select_quantity_show_main_page_id);
            this.itemSelectCount.setVisibility(View.INVISIBLE);
            this.itemSelectCount.setText("0");


            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relativeLayout);

        }


    }
}