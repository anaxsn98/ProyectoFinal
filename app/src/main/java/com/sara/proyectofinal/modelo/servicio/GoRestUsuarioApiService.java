package com.sara.proyectofinal.modelo.servicio;

import com.sara.proyectofinal.modelo.entidad.Usuario;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface  GoRestUsuarioApiService {
    @GET("usuarios")
    Call<Usuario> login(@Query("nombre") String nombre, @Query("pwd") String pwd);

    @POST("usuarios")
    Call<Usuario> altaUsuairo(@Body Usuario usuario);

    @PUT("usuarios/{id}")
    Call <ResponseBody> modificarUsuairo(@Path("id") String id, @Body Usuario u);
}
