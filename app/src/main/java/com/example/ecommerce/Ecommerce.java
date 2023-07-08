package com.example.ecommerce;

import static com.example.ecommerce.MainActivity.editor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class Ecommerce extends AppCompatActivity {
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecommerce);
        navigationView=findViewById(R.id.nav_window);
        drawerLayout=findViewById(R.id.Drawer);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle;
        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_drawer,R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home) {
                    addfragment(new home());
                    drawerLayout.closeDrawer(Gravity.LEFT);
                }
                if (item.getItemId() == R.id.userproduct) {
                    addfragment(new user_product());
                    drawerLayout.closeDrawer(Gravity.LEFT);
                }
                if (item.getItemId() == R.id.addproduct)
                {

                }
                if (item.getItemId() == R.id.logout)
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Ecommerce.this);
                    builder.setTitle("Alert...");
                    builder.setMessage("Are You Sure Logout");
                    builder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            editor.putBoolean("islogin",false);
                            editor.commit();
                            Intent intent = new Intent(Ecommerce.this, Login.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });builder.show();
                }

                return true;
            }
        });
    }
    private void addfragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.frame,fragment);
        transaction.commit();
    }
}