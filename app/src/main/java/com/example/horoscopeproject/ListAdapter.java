package com.example.horoscopeproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter implements Filterable {
    ArrayList<Item> lists,filteredlists;
    Context cont;

    public ListAdapter(ArrayList<Item> lists, Context cont) {
        this.lists = lists;
        this.filteredlists = lists;
        this.cont = cont;
    }

    @Override
    public int getCount() {
        return filteredlists.size();
    }

    @Override
    public Object getItem(int i) {
        return filteredlists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null){
            view = LayoutInflater.from(cont).inflate(R.layout.item_layout,viewGroup,false);
        }

        TextView tView = view.findViewById(R.id.textView);
        ImageView iView = view.findViewById(R.id.imageView);
        iView.setBackgroundResource(filteredlists.get(i).getImg());
        tView.setText(filteredlists.get(i).getDescription());
        return view;
    }

    @Override
    public Filter getFilter() {
        return new Filter(){
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                constraint = constraint.toString().toLowerCase();
                FilterResults result = new FilterResults();

                if (constraint != null && constraint.toString().length() > 0) {
                    ArrayList<Item> founded = new ArrayList<Item>();
                    for(Item item: lists){
                        if(item.getDescription().toLowerCase().contains(constraint)){
                            founded.add(item);
                        }
                    }

                    result.values = founded;
                    result.count = founded.size();
                }else {
                    result.values = lists;
                    result.count = lists.size();
                }
                return result;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredlists = (ArrayList<Item>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
