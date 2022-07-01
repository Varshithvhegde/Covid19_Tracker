package com.example.covid19tracker;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class JAdapter extends RecyclerView.Adapter<JAdapter.ViewHolder> {
    private ArrayList<Model> modelList;
    private final Context context;

    public JAdapter(ArrayList<Model> modelList, Context context) {
        this.modelList = modelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.testing,parent,false);
        return new JAdapter.ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Model model= modelList.get(position);
        holder.dName.setText(model.getDname());
        holder.deaths.setText(""+Math.toIntExact((Long)model.getDeath()));
        holder.recovered.setText(""+Math.toIntExact((Long)model.getCured()));
        holder.cases.setText(""+Math.toIntExact((Long)model.getActive()));
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        protected final TextView  cases;
        private final TextView  recovered;
        private final TextView deaths;
        private final TextView dName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cases = itemView.findViewById(R.id.IdCases);
            recovered = itemView.findViewById(R.id.IdRecovered);
            deaths = itemView.findViewById(R.id.IdDeaths);
            dName = itemView.findViewById(R.id.IdDist);
        }
    }

    public void filterList(ArrayList<Model> filterers) {
        modelList=filterers;
        notifyDataSetChanged();
    }
}
