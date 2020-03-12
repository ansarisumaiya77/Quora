package com.example.quora.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quora.Models.Earthquake;
import com.example.quora.Models.Family;
import com.example.quora.R;

import java.util.ArrayList;

public class EarthquakeAdapter extends RecyclerView.Adapter<EarthquakeAdapter.EarthquakeViewHolder>
{
    Context context;
    ArrayList<Earthquake> earthquakes;

    public EarthquakeAdapter(Context context, ArrayList<Earthquake> earthquakes_list) {
        this.context = context;
        this.earthquakes = earthquakes_list;
    }

    @NonNull
    @Override
    public EarthquakeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.resource_file_family, parent, false);
        EarthquakeViewHolder holder = new EarthquakeViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull EarthquakeViewHolder holder, int position) {
        holder.setData(earthquakes.get(position));
    }

    @Override
    public int getItemCount() {
        return earthquakes.size();
    }

    public class EarthquakeViewHolder extends RecyclerView.ViewHolder
    {
        TextView name;
        public EarthquakeViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.earthquake_place);

        }

        public void setData(Earthquake earth)
        {
            name.setText(earth.getPlace());
        }
    }

}
