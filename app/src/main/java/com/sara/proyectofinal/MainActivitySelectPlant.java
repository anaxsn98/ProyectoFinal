package com.sara.proyectofinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivitySelectPlant extends AppCompatActivity {

    private Toolbar toolbar;
    public LinearLayout personalizedPlant, defaultPlant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_select_plant);

        toolbar = findViewById(R.id.toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("");

        personalizedPlant =findViewById(R.id.layoutPlantPersonalized);
        defaultPlant =findViewById(R.id.layouPlantDefault);

        defaultPlant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainActivity = new Intent(MainActivitySelectPlant.this, DetallePlantaPredeterminadaActivity.class);
                startActivity(mainActivity);
                finish();
            }
        });

        personalizedPlant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainActivity = new Intent(MainActivitySelectPlant.this, DetallePlantaPredeterminadaActivity.class);
                startActivity(mainActivity);
                finish();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar, menu);
        //menu.findItem(R.id.Usuario).setVisible(true);
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
            Intent intent = new Intent( this, Usuario.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}