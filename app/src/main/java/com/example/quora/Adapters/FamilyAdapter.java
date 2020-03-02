package com.example.quora.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quora.Models.Family;
import com.example.quora.R;

import java.util.ArrayList;

public class FamilyAdapter extends RecyclerView.Adapter<FamilyAdapter.FamilyViewHolder>
{
    Context context;
    ArrayList<Family> families;

    public FamilyAdapter(Context context, ArrayList<Family> families) {
        this.context = context;
        this.families = families;
    }

    @NonNull
    @Override
    public FamilyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.resource_file_family, parent, false);
        FamilyViewHolder holder = new FamilyViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FamilyViewHolder holder, int position) {
        holder.setData(families.get(position));
    }

    @Override
    public int getItemCount() {
        return families.size();
    }

    public class FamilyViewHolder extends RecyclerView.ViewHolder
    {
        TextView name, contact, relation;
        public FamilyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txt_name);
            contact = itemView.findViewById(R.id.txt_phone);
            relation = itemView.findViewById(R.id.txt_relation);

        }

        public void setData(Family family)
        {
            name.setText(family.getFirstname()+" "+family.getLastname());
            contact.setText(family.getContact());
            relation.setText(family.getRelation());
        }
    }

}
