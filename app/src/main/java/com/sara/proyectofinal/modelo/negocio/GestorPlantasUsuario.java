package com.sara.proyectofinal.modelo.negocio;
import com.google.gson.GsonBuilder;
import com.sara.proyectofinal.modelo.servicio.GoRestPlantasUsuarioApiService;
import com.sara.proyectofinal.modelo.servicio.GoRestUsuarioApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GestorPlantasUsuario {

    private static GestorPlantasUsuario instance = null;
    private GoRestPlantasUsuarioApiService goRestPlantasUsuarioApiService = null;

    private GestorPlantasUsuario(){

    }

    public static GestorPlantasUsuario getInstance(){
        if(instance == null){
            instance = new GestorPlantasUsuario();
        }
        return instance;
    }

    public void inicializar(){
        //Tenemos que configurar Retrofit para acceder al servicio
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.35:8080/")
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder().serializeNulls().create()
                )).build();

        //Establecemos la relacion entre el servicio y Retrofit
        goRestPlantasUsuarioApiService =
                retrofit.create(GoRestPlantasUsuarioApiService.class);
    }

    public GoRestPlantasUsuarioApiService getGoRestUserApiService(){
        return goRestPlantasUsuarioApiService;
    }
}