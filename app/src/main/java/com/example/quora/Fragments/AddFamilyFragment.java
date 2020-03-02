package com.example.quora.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.quora.Models.Family;
import com.example.quora.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddFamilyFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    View rootView;
    String txt_gender = null;
    String txt_relation = null;
    TextInputLayout txt_fname, txt_lname, txt_contact, txt_email, txt_age;
    String fname, lname, contact, email, age;
    Button add_family;
    FirebaseDatabase db;
    FirebaseAuth auth;
    DatabaseReference reference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_add_family, container,false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Spinner spinner = rootView.findViewById(R.id.gender);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        Spinner relation_spinner = rootView.findViewById(R.id.relation);
        ArrayAdapter<CharSequence> relation_adapter = ArrayAdapter.createFromResource(getActivity(), R.array.relation, android.R.layout.simple_spinner_item);
        relation_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        relation_spinner.setAdapter(relation_adapter);
        relation_spinner.setOnItemSelectedListener(this);


        db = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        reference = db.getReference("family");

        txt_fname = rootView.findViewById(R.id.fname);
        txt_lname = rootView.findViewById(R.id.lname);
        txt_contact = rootView.findViewById(R.id.contact);
        txt_email = rootView.findViewById(R.id.email);
        txt_age = rootView.findViewById(R.id.age);
        add_family = rootView.findViewById(R.id.add_family);
        add_family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( !validateFirstname() | !validateLastname() | !validatePhone() | !validateEmail() | !validateAge())
                {
                    return;
                }
                else {
                    AddFamily(fname, lname, contact, email, age, txt_gender, txt_relation);
                }
            }
        });
    }

    private void AddFamily(String fname, String lname, String contact, String email, String age, String txt_gender, String txt_relation)
    {
        String key = auth.getCurrentUser().getUid();
        Family family = new Family(null, fname, lname, txt_gender, contact, email, txt_relation, age);
        reference.child(key).child(reference.push().getKey()).setValue(family);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        txt_gender = parent.getItemAtPosition(position).toString();
        txt_relation = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        txt_gender = null;
        txt_relation = null;
    }

    private boolean validateFirstname() {
        fname = txt_fname.getEditText().getText().toString().trim();

        if (fname.isEmpty()) {
            txt_fname.setError("Field can't be empty");
            return false;
        } else if (fname.length() > 8) {
            txt_fname.setError("Username too long");
            return false;
        } else {
            txt_fname.setError(null);
            return true;
        }
    }
    private boolean validateLastname() {
        lname = txt_lname.getEditText().getText().toString().trim();

        if (lname.isEmpty()) {
            txt_lname.setError("Field can't be empty");
            return false;
        } else if (lname.length() > 8) {
            txt_lname.setError("Username too long");
            return false;
        } else {
            txt_lname.setError(null);
            return true;
        }
    }
    private boolean validatePhone() {
        contact = txt_contact.getEditText().getText().toString().trim();

        if (contact.isEmpty()) {
            txt_contact.setError("Field can't be empty");
            return false;
        } else {
            txt_contact.setError(null);
            return true;
        }
    }
    private boolean validateEmail() {
        email = txt_email.getEditText().getText().toString().trim();

        if (email.isEmpty()) {
            txt_email.setError("Field can't be empty");
            return false;
        } else {
            txt_email.setError(null);
            return true;
        }
    }
    private boolean validateAge() {
        age = txt_age.getEditText().getText().toString().trim();

        if (age.isEmpty()) {
            txt_age.setError("Field can't be empty");
            return false;
        } else {
            txt_age.setError(null);
            return true;
        }
    }

}
