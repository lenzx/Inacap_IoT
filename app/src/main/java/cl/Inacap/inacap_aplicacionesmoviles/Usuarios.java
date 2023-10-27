package cl.Inacap.inacap_aplicacionesmoviles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

import cl.Inacap.inacap_aplicacionesmoviles.dao.UsuariosDao;
import cl.Inacap.inacap_aplicacionesmoviles.fragmento.CrudUsuario;

public class Usuarios extends AppCompatActivity {

    ListView listaView;
    UsuariosDao usuariosDao = new UsuariosDao(FirebaseDatabase.getInstance()).getById();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);

        listaView = (ListView) findViewById(R.id.LIST_USUARIOS_LISTA);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.botonSecondFloting);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Usuarios.this, AgregarUsuario.class);
                startActivity(intent);
            }
        });
    }
}