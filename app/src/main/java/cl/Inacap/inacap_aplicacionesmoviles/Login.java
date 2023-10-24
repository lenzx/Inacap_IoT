package cl.Inacap.inacap_aplicacionesmoviles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import cl.Inacap.inacap_aplicacionesmoviles.error.ErrorUsuario;

public class Login extends AppCompatActivity {
    private EditText user,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user = findViewById(R.id.TXT_LOGIN_USUARIO);
        password = findViewById(R.id.TXT_LOGIN_PASSWORD);
    }

    public void ingresarMenu(View view){

        String usuario = user.getText().toString();
        String pass = password.getText().toString();
        if (usuario.equals("leo") && pass.equals("1234")) {
            Intent intento = new Intent(this, Menu.class);
            startActivity(intento);
        } else {
            System.out.println(usuario);
            System.out.println(pass);
            openDialog();
        }
    }

    public void openDialog(){
        ErrorUsuario error = new ErrorUsuario();
        error.show(getSupportFragmentManager(),"Error");
    }
}