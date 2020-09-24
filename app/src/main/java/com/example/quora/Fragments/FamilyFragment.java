package com.example.quora.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quora.Adapters.FamilyAdapter;
import com.example.quora.Models.Family;
import com.example.quora.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FamilyFragment extends Fragment {

    View rootView;
    static ArrayList<Family> fame;
    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference reference;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_family, container,false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Initializations
        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();

        //GetFamilyData
        fame = new ArrayList<>();
        reference = db.getReference("family/"+auth.getCurrentUser().getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                fame.clear();
                for (DataSnapshot snap : dataSnapshot.getChildren())
                {
                    Family family = snap.getValue(Family.class);
                    Log.i("Information", family.getFirstname());
                    fame.add(family);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        AdapterBinding
        FamilyAdapter adapter = new FamilyAdapter(getActivity(),fame);
        RecyclerView recyclerView = view.findViewById(R.id.family_view);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }



}
