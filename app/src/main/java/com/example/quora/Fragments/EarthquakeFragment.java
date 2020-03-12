package com.example.quora.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quora.Adapters.EarthquakeAdapter;
import com.example.quora.Adapters.FamilyAdapter;
import com.example.quora.Models.Earthquake;
import com.example.quora.Models.Family;
import com.example.quora.R;
import com.example.quora.RequestDataClasses.EarthquakeData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EarthquakeFragment extends Fragment {

    View rootView;
    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<Earthquake> earthquakes_list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_earthquake, container,false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Initializations
        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();

        recyclerView = rootView.findViewById(R.id.earthquake_view);

        earthquakes_list = new ArrayList<>();

        EarthquakeData data = new EarthquakeData(getActivity());

        earthquakes_list = data.getData();

        Log.i("Error","Size: "+earthquakes_list.size());


//        EarthquakeAdapter adapter = new EarthquakeAdapter(getActivity(),earthquakes_list);
//        RecyclerView recyclerView = view.findViewById(R.id.earthquake_view);
//        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
//        manager.setOrientation(RecyclerView.VERTICAL);
//        recyclerView.setLayoutManager(manager);
//        recyclerView.setAdapter(adapter);


    }



}
