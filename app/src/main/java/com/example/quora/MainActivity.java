package com.example.quora;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.quora.Fragments.AddFamilyFragment;
import com.example.quora.Fragments.FamilyFragment;
import com.example.quora.Models.Family;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Toolbar toolbar;
    private DrawerLayout drawer;
    NavigationView navigationView;
    FirebaseAuth auth;
    FirebaseDatabase db;
    ArrayList<Family> families;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializations
        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();

        //Toolbar
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Quora");
        setSupportActionBar(toolbar);

        //DrawerLayout
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }


    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch(id)
        {
            case R.id.add_family:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new AddFamilyFragment()).commit();
                break;
            case R.id.see_family:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FamilyFragment()).commit();
                break;
            case R.id.logout:
                auth.signOut();
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
        return true;
    }

    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main_menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//        switch(id)
//        {
//            case R.id.logout:
//                auth.signOut();
//                startActivity(new Intent(this, LoginActivity.class));
//                break;
//        }
//        return true;
//    }
}
