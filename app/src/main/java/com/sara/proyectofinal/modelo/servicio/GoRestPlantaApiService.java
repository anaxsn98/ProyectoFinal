package com.sara.proyectofinal.modelo.servicio;

import com.sara.proyectofinal.modelo.entidad.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GoRestPlantaApiService {
    @GET("usuarios/{nombre}")
    Call<Usuario> getPersonaByNombre(@Path("nombre") String nombre);

    @POST("usuarios")
    Call<Usuario> altaUsuairo(@Body Usuario usuario);
}
