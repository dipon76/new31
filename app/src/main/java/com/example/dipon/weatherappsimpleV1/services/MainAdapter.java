package com.example.dipon.weatherappsimpleV1.services;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dipon.weatherappsimpleV1.Data.Day;
import com.example.dipon.weatherappsimpleV1.R;

import java.util.List;


/**
 * Created by Dipon on 3/28/2017.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.AdapterHolder> {

    public LayoutInflater inflater;
    public List<Day> dayList ;
    public Context context;

    private ItemClickCallback itemClickCallback;

    public interface ItemClickCallback {
        void onItemClick(View v,int p);
    }

    public void setItemClickCallback(final ItemClickCallback itemClickCallback) {
        this.itemClickCallback = itemClickCallback;
    }

    public MainAdapter(List<Day> dayList, Context c) {
        this.inflater = LayoutInflater.from(c);
        context = c;
        this.dayList = dayList;
    }

    @Override
    public AdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.card_item,parent,false);

        return new AdapterHolder(view);


    }

    @Override
    public void onBindViewHolder(AdapterHolder holder, int position) {
        Day day = dayList.get(position);
        holder.day.setText(day.day);
        holder.text.setText(day.text);
        int resourceId = context.getResources().getIdentifier("drawable/icon" + day.code,null,context.getPackageName());

        Drawable weatherIcon = context.getResources().getDrawable(resourceId);

        holder.icon.setImageDrawable(weatherIcon);


    }

    @Override
    public int getItemCount() {
        if(dayList == null) return  0;
        return dayList.size();
    }

    public class AdapterHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView day;
        private TextView text;
        private ImageView icon;
        private View container;

        public AdapterHolder(View itemView) {
            super(itemView);


            day = (TextView) itemView.findViewById(R.id.day);
            text = (TextView) itemView.findViewById(R.id.text);
            icon = (ImageView) itemView.findViewById(R.id.weatherIconImageDetail);
            container = itemView.findViewById(R.id.cont_item_root);
            container.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.cont_item_root){
                itemClickCallback.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
