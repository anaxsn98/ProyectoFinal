package com.sara.proyectofinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.sara.proyectofinal.adaptador.AdaptadorPlantas;
import com.sara.proyectofinal.modelo.entidad.Planta;
import com.sara.proyectofinal.singleton.ListaPlantaSingleton;

import java.util.List;

public class MisPlantasActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private BottomNavigationView bottomNavigationView;
    public RecyclerView recyclerView;
    public AdaptadorPlantas adaptadorplanta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plant);

        recyclerView = findViewById(R.id.rViewPlantas);

        //Mejora el rendimiento, si el contenido no afecta al tamaño del reciclerView
        recyclerView.setHasFixedSize(true);

        //El recicler va a utilizar un linear layout
        recyclerView.setLayoutManager(
                new GridLayoutManager(
                        this,
                        2));

        //Asociamos un adapter
        //El adaptador hace de puente entre el modelo de datos que es la lista de personajes y
        //entre la vista que es el recicler view
        ListaPlantaSingleton.getInstance().inicializar();
        List<Planta> listaAnimal = ListaPlantaSingleton.getInstance().getListaPlanta();
        //le pasamos la lista al adaptador
        adaptadorplanta = new AdaptadorPlantas(listaAnimal);
        //Le pasamos el adaptador
        recyclerView.setAdapter(adaptadorplanta);

        //controlador = Controlador.getInstance();
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        bottomNavigationView = findViewById(R.id.bottomNavigationBar);
        //controlador.verCarrito();
        //Bundle que en caso de venir de login no estará vacío, por lo que guardará el valor del id en userId.
        setSupportActionBar(toolbar);

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
    /**
     * Recoge el menu que hemos creado en un xml y lo infla en la vista
     * Devuelve verdadero en caasao de que pueda ejecytar el proceso
     *
     * @param menu
     * @return true
     */
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

    public void cargarListaPlantas(int id){

    }
}