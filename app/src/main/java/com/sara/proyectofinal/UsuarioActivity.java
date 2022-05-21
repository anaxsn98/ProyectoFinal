package com.sara.proyectofinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class UsuarioActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private Toolbar toolbar;
    private EditText editPassword, editPasswordConfirmar,editInvernadero;
    private Button btnModificarUsuairo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        editPassword = findViewById(R.id.editPasswordActivityUsuario);
        editPasswordConfirmar = findViewById(R.id.editPasswordConfirmarActivityUsuario);
        editInvernadero = findViewById(R.id.editInvernaderoActivityUsuario);
        btnModificarUsuairo = findViewById(R.id.btnModificarUsuairo);
        bottomNavigationView = findViewById(R.id.bottomNavigationBar);
        bottomNavigationView.setSelectedItemId(R.id.misPlantas);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

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

        btnModificarUsuairo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }/**
     * Recoge el menu que hemos creado en un xml y lo infla en la vista
     * Devuelve verdadero en caasao de que pueda ejecytar el proceso
     *
     * @param menu
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar, menu);
        //menu.findItem(R.id.UsuarioActivity).setVisible(true);
        return true;
    }

    /**
     * Recoge el id de los items que hemos creado en el menu
     * Si el id corresponde al de carrito se abrirá el Main Activity del carrito
     *
     * @param item recoge los items de las opciones del menu.
     * @return item ,Devuelve el id del item que se haya seleccionado.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.ayuda) {
            Intent intent = new Intent( this,LoginActivity.class);
            startActivity(intent);
        }

        if (id == R.id.politica) {
            Intent intent = new Intent( this,LoginActivity.class);
            startActivity(intent);
        }
        if (id == R.id.menu_usuario) {
            Intent intent = new Intent( this, UsuarioActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}