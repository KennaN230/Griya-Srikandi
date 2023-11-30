package com.example.psrikandi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class FragControl extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag_control);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new Product()).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragmment = null;

                if (item.getItemId() == R.id.navigation_product) {
                    selectedFragmment = new Product();
                } else if (item.getItemId() == R.id.navigation_flash_sale) {
                    selectedFragmment = new FlashSale();
                } else if (item.getItemId() == R.id.navigation_order) {
                    selectedFragmment = new Order();
                } else if (item.getItemId() == R.id.navigation_user) {
                    selectedFragmment = new User();
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, selectedFragmment).commit();
                return true;
            }
        });

    }
}