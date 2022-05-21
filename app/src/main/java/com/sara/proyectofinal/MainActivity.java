package com.sara.proyectofinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
//import com.sara.proyectofinal.controlador.Adaptador;
import com.sara.proyectofinal.controlador.Controlador;

public class MainActivity extends AppCompatActivity {

    private Controlador controlador;
    private TextView nombreUser;
    private Toolbar toolbar;
    private BottomNavigationView bottomNavigationView;
    private ImageView imgVegetable;

    //Declaramos la variable de la progress Bar

    public int CurrentProgressAgua = 0;
    public int CurrentProgressLuz = 0;
    public int CurrentProgressVentilacion = 0;
    public int CurrentProgressAmor = 0;
    public int CurrentProgressHumedad = 0;

    private ProgressBar progressBarAgua, progressBarLuz, progressBarAmor, progressBarVentilacion, progressBarHumedad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //controlador = Controlador.getInstance();
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        bottomNavigationView = findViewById(R.id.bottomNavigationBar);
        //controlador.verCarrito();
        //Bundle que en caso de venir de login no estará vacío, por lo que guardará el valor del id en userId.
        setSupportActionBar(toolbar);
        nombreUser = findViewById(R.id.TextoBienbenida);
        //if(controlador.getUsuario() != null) {
        //    nombreUser.setText("¡Hola " + controlador.getUsuario().getNombre() + "!");
        //}
//Imagen
        imgVegetable = findViewById(R.id.imgVegetable);
        //ProgressBar
        progressBarAgua = findViewById(R.id.porcentajeAgua);
        progressBarLuz = findViewById(R.id.porcentajeLuz);
        progressBarAmor = findViewById(R.id.porcentajeAmor);
        progressBarHumedad = findViewById(R.id.porcentajeHumedad);
        progressBarVentilacion = findViewById(R.id.porcentajeVentilacion);

        //LLamada a los métodos de las progress bar
        getProgressBarAgua(CurrentProgressAgua);
        getProgressBarLuz(CurrentProgressLuz);
        getProgressBarHumedad(CurrentProgressHumedad);
        getProgressBarVentilacion(CurrentProgressVentilacion);

        imgVegetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getProgressBarAmor(CurrentProgressAmor);
            }
        });
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


    /**
     * Método que recoge el estado del agua y muestra el porcentaje del agua
     * en una progressbar
     * @param progress la cantidad de agua en porcentaje
     */
    public void getProgressBarAgua(int progress) {
        CurrentProgressAgua = CurrentProgressAgua + 10;
        progressBarAgua.setProgress(CurrentProgressAgua);
        progressBarAgua.setMax(100);
    }

    /**
     * Método que recoge el estado del agua y muestra el porcentaje del agua
     * en una progressbar
     * @param progress la cantidad de agua en porcentaje
     */
    public void getProgressBarLuz(int progress) {
        CurrentProgressLuz = CurrentProgressLuz + 30;
        progressBarLuz.setProgress(CurrentProgressLuz);
        progressBarLuz.setMax(100);
    }

    /**
     * Método que recoge el estado del agua y muestra el porcentaje del agua
     * en una progressbar
     * @param progress la cantidad de agua en porcentaje
     */
    public void getProgressBarAmor(int progress) {
        CurrentProgressAmor += 5;
        progressBarAmor.setProgress(CurrentProgressAmor);
        progressBarLuz.setMax(100);
    }

    /**
     * Método que recoge el estado del agua y muestra el porcentaje del agua
     * en una progressbar
     * @param progress la cantidad de agua en porcentaje
     */
    public void getProgressBarVentilacion(int progress) {
        CurrentProgressVentilacion = CurrentProgressVentilacion + 100;
        progressBarVentilacion.setProgress(CurrentProgressVentilacion);
        progressBarVentilacion.setMax(100);
    }

    /**
     * Método que recoge el estado del agua y muestra el porcentaje del agua
     * en una progressbar
     * @param progress la cantidad de agua en porcentaje
     */
    public void getProgressBarHumedad(int progress) {
        CurrentProgressHumedad = CurrentProgressHumedad + 100;
        progressBarHumedad.setProgress(CurrentProgressHumedad);
        progressBarHumedad.setMax(100);
    }
    }
