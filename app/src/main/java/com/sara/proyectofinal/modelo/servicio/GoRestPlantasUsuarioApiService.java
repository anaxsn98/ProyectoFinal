package com.sara.proyectofinal.modelo.servicio;

import com.sara.proyectofinal.modelo.entidad.Usuario;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GoRestPlantasUsuarioApiService {
    /**
     * Buscar usuario por id
     * @param id
     * @return
     */
    @GET("usuarios/{id}/plantas")
    Call<Usuario> getPlantasPersonaById(@Path("id") int id);

    @DELETE("usuarios/{id}/plantas")
    Call <ResponseBody> borrarPlantasDeUnUsuario(@Path("id") String id);

    @DELETE("usuarios/{id_user}/plantas/{id_plant}")
    Call <ResponseBody> borrarUnaPlantaDeUnUsuario(@Path("id_user") String id_user,@Path("id_plant") String id_plant);

}
