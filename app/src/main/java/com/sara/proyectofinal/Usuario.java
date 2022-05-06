package com.sara.proyectofinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Usuario extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        bottomNavigationView = findViewById(R.id.bottomNavigationBar);
        bottomNavigationView.setSelectedItemId(R.id.misPlantas);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                int id = item.getItemId();
                switch(id){
                    case R.id.planta:
                        intent = new Intent( getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.misPlantas:
                        intent = new Intent( getApplicationContext(),MisPlantasActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.calendario:
                        intent = new Intent( getApplicationContext(),Calendario.class);
                        startActivity(intent);
                        return true;
                }
                return true;
            }
        });
    }
}