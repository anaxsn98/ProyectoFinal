package com.sara.proyectofinal.modelo.negocio;
import com.google.gson.GsonBuilder;
import com.sara.proyectofinal.modelo.servicio.GoRestUsuarioApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GestorUsuario {

    private static GestorUsuario instance = null;
    private GoRestUsuarioApiService goRestUsuarioApiService = null;

    private GestorUsuario(){

    }

    public static GestorUsuario getInstance(){
        if(instance == null){
            instance = new GestorUsuario();
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
        goRestUsuarioApiService =
                retrofit.create(GoRestUsuarioApiService.class);
    }

    public GoRestUsuarioApiService getGoRestUserApiService(){
        return goRestUsuarioApiService;
    }
}