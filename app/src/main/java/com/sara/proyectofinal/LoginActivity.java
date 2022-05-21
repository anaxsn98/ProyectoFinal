package com.sara.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sara.proyectofinal.controlador.Controlador;
import com.sara.proyectofinal.modelo.entidad.Usuario;
import com.sara.proyectofinal.modelo.negocio.GestorUsuario;
import com.sara.proyectofinal.modelo.servicio.GoRestUsuarioApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private String userid;
    private Controlador controlador;
    private Button botonEntrar;
    private EditText editEmail, editPassword;
    private TextView tvOlvidasteContrasena, txtRegistrate,loginError;
    private ProgressDialog mDefaultDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //controlador = Controlador.getInstance();
        //Es necesario inicializar el controlador
        setContentView(R.layout.activity_login);

        txtRegistrate = findViewById(R.id.txtRegistrate);
        botonEntrar = findViewById(R.id.buttonEntrar);
        editEmail = findViewById(R.id.email);
        editPassword = findViewById(R.id.password);
        tvOlvidasteContrasena = findViewById(R.id.recuerda);
        loginError = findViewById(R.id.LoginError);

        GestorUsuario.getInstance().inicializar();

        tvOlvidasteContrasena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Intent intent = new Intent(LoginActivity.this, PasswordActivity.class);
                intent.putExtra("correo", editEmail.getText().toString());
                startActivity(intent);
                 */
            }
        });


        /**
         * Comprueba si los datos introducidos son validos para procesar el login, en caso contrario, avisa al usuario.
         */
        botonEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textEmail = editEmail.getText().toString();
                String textPassword = editPassword.getText().toString();

                if(controlDeErorres(textEmail,textPassword)){
                    obtenerUsuarioRest(textEmail,textPassword);
                }

            }
        });

        /**
         * Abre la actividad del registro*/
        txtRegistrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent2);
                finish();
            }
        });
    }

    /**
     * Método que comprueba que el email y la contraseña no esten vacíos o que haya más de 4
     * caracteres
     * @param usu texto con el usuario introducido
     * @param contra texto con la contraseña introducida
     * @return false si hay algún error true en caso de que no haya error
     */
    public boolean controlDeErorres(String usu, String contra){
        if (usu.equals("") || contra.equals("")){
            respuestaError();
            Log.d("Login", "Campos vacios");
            return false;
        }else if (usu.length() < 4 || contra.length() < 4){
            Log.d("Login", "Menos que 4 caracteres");
            respuestaError();
            return false;
        }

        return true;
    }

    /**
     * Método para mostrar un mensaje de error durante 5 segundos
     */
    public void respuestaError(){
        new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {
                loginError.setText("Email o contraseña no válido");

            }

            @Override
            public void onFinish() {
                loginError.setText("");
            }

        }.start();
    }
    /**
     *  Método rest que accede a la base de dator para obtener el usuario a partir del email que se
     *  ha introducido en el edittex
     * @param email email del usuario
     */
    public void obtenerUsuarioRest(String email,String pwd){
        mostrarEspera();

        GoRestUsuarioApiService goRestUsuarioApiService =
                GestorUsuario.getInstance().getGoRestUserApiService();

        Call<Usuario> call = goRestUsuarioApiService.login(email,pwd);

        //Como no sabemos el tiempo que va a tardar en responder el servicio
        //rest, no podemos bloquear la aplicacion movil al usuario.
        //Por ello, debemos de hacer siempre las peticiones a servicios de manera
        //asincrona, es decir, no bloqueamos la pantalla del movil y cuando
        //el servidor conteste, entonces se ejecutara un determinado método.
        //Este método se suele llamar función de callback o retro llamada

        //En este ejemplo de aqui, con el método enqueue estamos haciendo
        //la solicitud al servidio rest
        call.enqueue(new Callback<Usuario>() {
            //Este método se ejecutará cuando el servidor HTTP nos responda
            //satisfactoriamente
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()) {
                    Log.d("Login", "busqueda hecha");
                    //Gracias a Gson, me convierte los json a objetos UsuarioActivity
                    Usuario usuario = response.body();

                    /*Comprobar la pwd*/
                    if(!pwd.equals(usuario.getPwd())){
                        respuestaError();
                        Log.d("Login", "contraseñas no coinciden "+ pwd +" "+usuario.getPwd());
                    }else{/*Inicio de sesión correcto*/
                        Intent mainActivity = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(mainActivity);
                        finish();
                    }
                } else {
                    Log.d("Login", response.code() + " " + response.message());
                    respuestaError();
                }

                cancelarEspera();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("Error", t.toString());
                cancelarEspera();
                respuestaError();
            }
        });
    }

    /**
     * Método que muestra un ProgressDialog
     */
    public void mostrarEspera() {
        mDefaultDialog = new ProgressDialog(this);
        // El valor predeterminado es la forma de círculos pequeños
        mDefaultDialog.setProgressStyle (android.app.ProgressDialog.STYLE_SPINNER);
        mDefaultDialog.setMessage("Solicitando datos ...");
        mDefaultDialog.setCanceledOnTouchOutside(false);// Por defecto true
        mDefaultDialog.show();
    }

    /**
     * Método que termina con el ProgressDialog
     */
    public void cancelarEspera(){
        mDefaultDialog.cancel();
    }
}
/**
 * Comprueba si el email es válido
 *
 * @param email el email a comprobar
 * @return si el correo es valido devuelve true, en caso de que sea invalido devuelve false.

private boolean validarEmail (String tx){
Pattern pattern = Patterns.EMAIL_ADDRESS;
return pattern.matcher(email).matches();
}
 */

