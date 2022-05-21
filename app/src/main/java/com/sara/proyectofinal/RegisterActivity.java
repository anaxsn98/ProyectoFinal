package com.sara.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sara.proyectofinal.modelo.entidad.Usuario;
import com.sara.proyectofinal.modelo.negocio.GestorUsuario;
import com.sara.proyectofinal.modelo.servicio.GoRestUsuarioApiService;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    //private Controlador controlador;
    private Button btnRegistro;
    private TextView txtInicio,registroError;
    private EditText editEmail,editNombreUsuario,editPassword, editPasswordConfirmar,editInvernadero;
    private ProgressDialog mDefaultDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //controlador = Controlador.getInstance();
        btnRegistro = findViewById(R.id.btnRegistro);
        editNombreUsuario = findViewById(R.id.editNombreUsuario);
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        editPasswordConfirmar = findViewById(R.id.editPasswordConfirmar);
        txtInicio = findViewById(R.id.txtInicio);
        registroError = findViewById(R.id.RegistroError);
        editInvernadero = findViewById(R.id.editInvernadero);

//        String correoDeIntent = getIntent().getExtras().getString("correo");

        /**
         * Abre la actividad de inicio de sesion
         */
        txtInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerActivity = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(registerActivity);
                finish();
            }
        });

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = editNombreUsuario.getText().toString();
                String email = editEmail.getText().toString();
                String password = editPassword.getText().toString();
                String password2 = editPasswordConfirmar.getText().toString();
                String codigo_invernadero = editInvernadero.getText().toString();
                if(controlDeErorres( nombre,  email, password, password2,codigo_invernadero)){
                    Usuario user = new Usuario();
                    user.setCorreo(email);
                    user.setNombre(nombre);
                    user.setPwd(password);
                    user.setCodigo_invernadero(codigo_invernadero);
                    obtenerUsuarioRest(user);
                }
            }
        });

    }

    /**
     * Método que comprueba que el email y la contraseña no esten vacíos o que haya más de 4
     * caracteres
     * @param nombre
     * @param email
     * @param pwd
     * @param pwd2
     * @return false si hay algún error true en caso de que no haya error
     */
    public boolean controlDeErorres(String nombre, String email,String pwd,String pwd2,String codigo_invernadero){
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        String mensaje="";
        if (nombre.equals("") || email.equals("") || pwd.equals("") || pwd2.equals("") || codigo_invernadero.equals("")){
            mensaje = "Campos vacios";
            respuestaError(mensaje);
            Log.d("Login", mensaje);
            return false;
        }
        if (nombre.length() < 4 || email.length() < 4|| pwd.length() < 4 || pwd2.length() < 4 || codigo_invernadero.length() < 4){
            mensaje = "Menos que 4 caracteres";
            Log.d("Login", mensaje);
            respuestaError(mensaje);
            return false;
        }
        if(!pattern.matcher(email).matches()){
            mensaje="Email no válido";
            Log.d("Login", mensaje);
            respuestaError(mensaje);
            return false;
        }
        if(!pwd.equals(pwd2)){
            mensaje="Las contraseñas no coinciden";
            Log.d("Login", mensaje);
            respuestaError(mensaje);
            return false;
        }
        return true;
    }

    /**
     * Método para mostrar un mensaje de error durante 5 segundos
     */
    public void respuestaError(String mensaje){
        new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {
                registroError.setText(mensaje);

            }

            @Override
            public void onFinish() {
                registroError.setText("");
            }

        }.start();
    }

    /**
     * Dar de alta el ususario
     * @param u usuario que se quiere dar de alta
     */
    public void obtenerUsuarioRest(Usuario u){
        mostrarEspera();

        GoRestUsuarioApiService goRestUsuarioApiService =
                GestorUsuario.getInstance().getGoRestUserApiService();

        Call<Usuario> call = goRestUsuarioApiService.altaUsuairo(u);

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
                    if(usuario.getId() < 1){
                        Log.d("Login", "usuario no registrado ");
                        respuestaError("usuario no registrado");
                    }else{/*UsuarioActivity registrado ir a la activity login*/
                        Intent mainActivity = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(mainActivity);
                        finish();
                    }
                } else {
                    Log.d("Login", response.code() + " " + response.message());
                    return;
                }

                cancelarEspera();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("Error", t.toString());
                cancelarEspera();
                respuestaError("algo ha fallado");
                respuestaError("usuario o correo duplicado");
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