package com.baway.yuekao5.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baway.yuekao5.R;
import com.baway.yuekao5.bean.HouseBean;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * @author 任珏
 * @类的用途
 * @date 2017/5/1 17:18
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    private Context context;
    private List<HouseBean.Result.Rows> rows;

    public MyAdapter(Context context, List<HouseBean.Result.Rows> rows) {
        this.context = context;
        this.rows = rows;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.recycler_item,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Glide.with(context).load(rows.get(position).info.default_image).into(holder.item_iv);
        holder.item_name.setText(rows.get(position).info.loupan_name);
        holder.item_region.setText(rows.get(position).info.region_title+"-"+rows.get(position).info.sub_region_title);
        String new_price_back = rows.get(position).info.new_price_back;
        String new_price_value = rows.get(position).info.new_price_value;
        if (new_price_back!=null&&new_price_value!=null){
            holder.item_value.setText(new_price_value+new_price_back);
        }else{
            int value = rows.get(position).info.recommend_price.value;
            holder.item_value.setText(value+rows.get(position).info.recommend_price.back);
        }
        String tags = rows.get(position).info.tags;
        if (!tags.equals("")){
            String[] split = tags.split(",");
            holder.item_tag1.setText(split[0]);
            holder.item_tag2.setText(split[1]);
            holder.item_tag3.setText(split[2]);
        }
        holder.item_sale.setText(rows.get(position).info.sale_title);
    }

    @Override
    public int getItemCount() {
        return rows.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private final ImageView item_iv;
        private final TextView item_name;
        private final TextView item_region;
        private final TextView item_value;
        private final TextView item_tag1;
        private final TextView item_tag2;
        private final TextView item_tag3;
        private final TextView item_sale;

        public MyViewHolder(View itemView) {
            super(itemView);
            item_iv = (ImageView) itemView.findViewById(R.id.item_iv);
            item_name = (TextView) itemView.findViewById(R.id.item_name);
            item_region = (TextView) itemView.findViewById(R.id.item_region);
            item_value = (TextView) itemView.findViewById(R.id.item_value);
            item_tag1 = (TextView) itemView.findViewById(R.id.item_tag1);
            item_tag2 = (TextView) itemView.findViewById(R.id.item_tag2);
            item_tag3 = (TextView) itemView.findViewById(R.id.item_tag3);
            item_sale = (TextView) itemView.findViewById(R.id.item_sale);
        }
    }
}
