package com.sara.proyectofinal.modelo.negocio;
import com.google.gson.GsonBuilder;
import com.sara.proyectofinal.modelo.servicio.GoRestPlantaApiService;
import com.sara.proyectofinal.modelo.servicio.GoRestUsuarioApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GestorPlanta {

    private static GestorPlanta instance = null;
    private GoRestPlantaApiService goRestPlantaApiService = null;

    private GestorPlanta(){

    }

    public static GestorPlanta getInstance(){
        if(instance == null){
            instance = new GestorPlanta();
        }
        return instance;
    }

    public void inicializar(){
        //Tenemos que configurar Retrofit para acceder al servicio
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://segundo-env.eba-ecxd7vmm.us-east-2.elasticbeanstalk.com/")
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder().serializeNulls().create()
                )).build();

        //Establecemos la relacion entre el servicio y Retrofit
        goRestPlantaApiService =
                retrofit.create(GoRestPlantaApiService.class);
    }

    public GoRestPlantaApiService getGoRestUserApiService(){
        return goRestPlantaApiService;
    }
}