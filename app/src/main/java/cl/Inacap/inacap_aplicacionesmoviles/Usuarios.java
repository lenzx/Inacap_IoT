package cl.Inacap.inacap_aplicacionesmoviles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import cl.Inacap.inacap_aplicacionesmoviles.adaptador.UsuariosAdapter;
import cl.Inacap.inacap_aplicacionesmoviles.dao.UsuariosDao;
import cl.Inacap.inacap_aplicacionesmoviles.fragmento.DetallerUsuario;
import cl.Inacap.inacap_aplicacionesmoviles.modelo.Usuario;

import java.util.List;

public class Usuarios extends AppCompatActivity {

    private ListView listaView;
    private UsuariosAdapter adaptador;
    private UsuariosDao usuariosDao;
    private static final int AGREGAR_USUARIO_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);

        listaView = findViewById(R.id.LIST_USUARIOS_LISTA);

        findViewById(R.id.botonSecondFloting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Usuarios.this, AgregarUsuario.class);
                startActivityForResult(intent, AGREGAR_USUARIO_REQUEST_CODE);
            }
        });

        obtenerYMostrarUsuarios();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AGREGAR_USUARIO_REQUEST_CODE && resultCode == RESULT_OK) {

            obtenerYMostrarUsuarios();
        }
    }

    private void obtenerYMostrarUsuarios() {
        usuariosDao = new UsuariosDao(FirebaseDatabase.getInstance());
        usuariosDao.getAll2(new UsuariosDao.OnUsuariosDataReceived() {
            @Override
            public void onDataReceived(List<Usuario> usuarios) {
                adaptador = new UsuariosAdapter(Usuarios.this, usuarios);
                listaView.setAdapter(adaptador);
                adaptador.setOnItemClickListener(new UsuariosAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Usuario usuario) {
                        mostrarDetallesUsuario(usuario);
                    }
                });
            }

            @Override
            public void onError(DatabaseError databaseError) {

            }
        });
    }

    private void mostrarDetallesUsuario(Usuario usuario) {

        DetallerUsuario detalleFragment = DetallerUsuario.newInstance(usuario);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, detalleFragment)
                .addToBackStack(null)
                .commit();

    }
}

