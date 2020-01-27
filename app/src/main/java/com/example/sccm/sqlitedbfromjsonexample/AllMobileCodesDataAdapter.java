package com.example.sccm.sqlitedbfromjsonexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class AllMobileCodesDataAdapter extends RecyclerView.Adapter<AllMobileCodesDataAdapter.AllMobileCodesDataViewHolder>{

    private ArrayList<HashMap<String,String>> allMobileCodesArrayList;
    public Context mContext;

    public AllMobileCodesDataAdapter(ArrayList<HashMap<String, String>> allMobileCodesArrayList, Context context) {
        this.allMobileCodesArrayList = allMobileCodesArrayList;
        mContext = context;
    }

    @NonNull
    @Override
    public AllMobileCodesDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_mobile_codes_layout,parent,false);

        return new AllMobileCodesDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllMobileCodesDataViewHolder holder, int position) {

        final HashMap<String,String> hashMap = allMobileCodesArrayList.get(position);
        holder.mobile_country.setText(hashMap.get("name"));

    }

    @Override
    public int getItemCount() {
        return allMobileCodesArrayList.size();
    }

    public class AllMobileCodesDataViewHolder extends RecyclerView.ViewHolder{

        TextView mobile_country;

        public AllMobileCodesDataViewHolder(@NonNull View itemView) {
            super(itemView);

            mobile_country = itemView.findViewById(R.id.mobile_country);
        }
    }
}
